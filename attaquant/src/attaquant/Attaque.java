/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attaquant;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamed
 */
public class Attaque {
    static ArrayList<String> attaques = new ArrayList<String>();
    String header_select = "logged_in,dst_host_same_srv_rate,dst_host_serror_rate,same_srv_rate,dst_host_srv_serror_rate,dst_host_srv_count,dst_host_srv_rerror_rate,srv_rerror_rate,dst_host_same_src_port_rate,protocol_type,flag,service,count,diff_srv_rate,is_guest_login,root_shell,wrong_fragment,attaque_name";
    String local_ip = "";
    int header_select_lenght = 18;
    int line_number = 0;
    public Attaque(){
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while(e.hasMoreElements())
            {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                if(n.getName().contains("wlan")){
                    Enumeration ee = n.getInetAddresses();
                    while (ee.hasMoreElements())
                    {                        
                        InetAddress i = (InetAddress) ee.nextElement();                        
                        if(i.isSiteLocalAddress())
                            local_ip = i.getHostAddress();                        
                    }
                }
            }
        } catch (SocketException ex) {            
            Logger.getLogger(Attaque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void start(String ip, String PORT, String LIMIT, String S_IP,String S_PORT) {
        int lm = 0;
        int port = Integer.parseInt(PORT);
        int limit = Integer.parseInt(LIMIT);
        int s_port = Integer.parseInt(S_PORT);
        try (Socket socket = new Socket(S_IP,s_port)) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedInputStream reader = new BufferedInputStream(socket.getInputStream());
            socket.setReuseAddress(true);
            for(int i = 0; i < limit ; i++){
                lm++;
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:sss");  
                Date date = new Date();
                String id = ""+lm;
                String time = formatter.format(date);
                String Source_ip = local_ip;
                String Destination_ip = ip;
                String Source_port = "1110";
                String Destination_port = ""+port;
                String final_paquet = id+"#"+time+"#"+Source_ip+"#"+Source_port+"#"+Destination_ip+"#"+Destination_port+"#"+attaques.get(i)+"\n";
                writer.write(final_paquet);
                writer.flush(); 
            }
            socket.close();
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) { 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
    public void read_from_file(String File) throws FileNotFoundException{
        File file = new File(File);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
           line_number++;
           String s= sc.nextLine();
           String[] paquet;
           paquet = s.split(",");
           attaques.add(s);
           Attaquant.f.tableModel.insertRow(Attaquant.f.tableModel.getRowCount(), paquet);
        }
        attaquant.Frame.Limit_slider.setMaximum(line_number);
    }
}
