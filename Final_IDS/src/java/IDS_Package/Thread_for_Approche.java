/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDS_Package;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamed
 */
public class Thread_for_Approche implements Runnable{
    Approche approche;
    private static final ThreadLocal<Vecteur> att_from_main = new ThreadLocal<Vecteur>();
    String Client;
    
    Thread_for_Approche(Approche APPROCHE, ArrayList<ArrayList<double[]>> train_model){
        approche = APPROCHE;
        approche.atk_class = train_model;
    }

    @Override
    public void run() {
        boolean flag = true;
        while(flag){
            synchronized(Primary_Frame.controller.Lock){
                try {
                    Primary_Frame.controller.Lock.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Thread_for_Approche.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            while(!Primary_Frame.controller.list.isEmpty()){
                synchronized(Primary_Frame.controller.list){
                    if(!Primary_Frame.controller.list.isEmpty())
                        att_from_main.set(Primary_Frame.controller.list.remove(0));
                }
                if(att_from_main.get() != null)
                    approche.get_test_data(att_from_main.get());
                att_from_main.set(null);
            }
            flag = Primary_Frame.controller.flag;
        }
    }
}
