/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDS_Package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamed
 */
public class Controller implements Runnable{
    static ArrayList<Vecteur> vecteurs = new ArrayList<Vecteur>();
    static List<Vecteur> list = Collections.synchronizedList(vecteurs);
    static ArrayList<Vecteur> final_list = new ArrayList<Vecteur>();
    static List<Vecteur> final_list2 = Collections.synchronizedList(final_list);
    static String Choosen_approche;
    static PageInfo page_info = new PageInfo();
    static boolean flag = true;
    static int def_id = 1;
    long start;
    Object Lock = new Object();
    public Controller(String m){
        Choosen_approche = m;
    }
    @Override
    public void run() {
        start = System.currentTimeMillis();
        try {
            Class.forName("org.postgresql.Driver");
            Approche approche = new Approche();
            ArrayList<ArrayList<double[]>> train_model= new ArrayList<ArrayList<double[]>>();
            train_model = approche.build_model(Choosen_approche);
            approche.Init_norm();
            // thread number
            int core_count = Runtime.getRuntime().availableProcessors();
            Thread thread_cnn[] = new Thread[core_count];
            for(int i = 0; i < core_count; i++){
                thread_cnn[i] = new Thread(new Thread_for_Approche(new Approche(),train_model),"cnn"+i);
                thread_cnn[i].start();
            }
            
            int count = 0;
            int port = 1112;
            try(ServerSocket serverSocket = new ServerSocket(port)){
                while(flag){
                    Socket socket = serverSocket.accept();
                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    String line;
                    while((line = reader.readLine()) != null){
                        //vecteur class
                        Vecteur temps_vecteur = new Vecteur();
                        String temps_vect_s[] = line.split("#");
                        temps_vecteur.id = def_id; def_id++;//Integer.parseInt(temps_vect_s[0]);
                        temps_vecteur.time = temps_vect_s[1];
                        temps_vecteur.Source_ip = temps_vect_s[2];
                        temps_vecteur.Source_port = temps_vect_s[3];
                        temps_vecteur.Destination_ip = temps_vect_s[4];
                        temps_vecteur.Destination_port = temps_vect_s[5];
                        //temps_vecteur.vecteur_init(temps_vect_s[6]);
                        String vecteur_string[] = temps_vect_s[6].split(",");
                        for(int i = 0; i < vecteur_string.length-1 ; i++){
                            temps_vecteur.vecteur[i] =  approche.Normalisation(vecteur_string[i], i);
                        }
                        temps_vecteur.Label = vecteur_string[17];
                        vecteurs.add(temps_vecteur);
                        
                        synchronized(Lock){
                            Lock.notifyAll();
                        }
                        //======
                        count++;
                    }
                    input.close();
                    socket.close();
                }
            }catch(IOException ex){
                System.out.println("Server exception: " + ex.getMessage());
                ex.printStackTrace();
            }
            for(int i = 0; i < core_count; i++){
                thread_cnn[i].join();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
