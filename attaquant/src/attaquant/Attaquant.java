/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attaquant;

import java.io.IOException;
import java.text.ParseException;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author mohamed
 */
public class Attaquant {

    /**
     * @param args the command line arguments
     */
    static String header_select = "logged_in,dst_host_same_srv_rate,dst_host_serror_rate,same_srv_rate,dst_host_srv_serror_rate,dst_host_srv_count,dst_host_srv_rerror_rate,srv_rerror_rate,dst_host_same_src_port_rate,protocol_type,flag,service,count,diff_srv_rate,is_guest_login,root_shell,wrong_fragment,attaque_name";
    static int header_select_lenght = 18;
    int port = 1234;
    static Frame f;
    
    
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, ParseException {
        f = new Frame();
    }
    
}
