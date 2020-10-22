/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author mohamed
 */
public class Recepteur implements Runnable{
    int port;
    
    public Recepteur(String port_n){
        port = Integer.parseInt(port_n);
    }

    public void start() {
        int count = 0;
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Client is listening on port " + port);
            while(true){
                Socket socket = serverSocket.accept();
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String line = reader.readLine();
                count++;
                String[] recieved = line.split("#");
                String[] paquet = new String[4];
                paquet[0] = recieved[0];
                paquet[1] = recieved[1];
                paquet[2] = recieved[2];
                paquet[3] = recieved[3].substring(1,recieved[3].length()-1);
                Client.frame.total.setText(""+count);
                Client.frame.tableModel.insertRow(Client.frame.tableModel.getRowCount(), paquet);
                socket.close();
            }
        }catch(IOException ex){
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        start();
    }
}
