/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDS_Package;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 *
 * @author mohamed
 */


public class Sender implements Runnable{
    static Object pauseLock = new Object();
    String vecteur_to_send;
    String client_ip;
    int client_port; 
    Vecteur vecteur;
    /*public Sender(double[] vecteur, String ip,String port){
            vecteur_to_send = vecteur;
            client_ip = ip;
            client_port = Integer.parseInt(port);
    }*/
    public Sender(Vecteur v){
        vecteur = v;
    }
    
    @Override
    public void run() {
        boolean flag = true;
        int count = 0;
            synchronized(pauseLock){
                client_port = Integer.parseInt(vecteur.Destination_port);
                    try (Socket socket = new Socket(vecteur.Destination_ip,client_port)){
                        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                        BufferedInputStream reader = new BufferedInputStream(socket.getInputStream());
                        //On envoie la commande au serveur
                        count ++;
                        vecteur_to_send = ""+vecteur.id+"#";
                        vecteur_to_send = vecteur_to_send+vecteur.Source_ip+"#";
                        vecteur_to_send = vecteur_to_send+vecteur.Source_port+"#";
                        vecteur_to_send = vecteur_to_send+Arrays.toString(vecteur.vecteur);
                        writer.write(vecteur_to_send);
                        //TOUJOURS UTILISER flush() POUR ENVOYER RÉELLEMENT DES INFOS AU SERVEUR
                        writer.flush();  
                        socket.close();
                    } catch (UnknownHostException ex) {
                        System.out.println("Server not found: " + ex.getMessage());
                    } catch (IOException ex) {
                        System.out.println("I/O error: " + ex.getMessage());
                    }
            }
    }
}
