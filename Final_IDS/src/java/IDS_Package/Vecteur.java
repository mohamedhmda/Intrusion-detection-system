/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDS_Package;

/**
 *
 * @author mohamed
 */
public class Vecteur {
    /**
     * 
     * [0] : id
     * [1] : time
     * [2] : Source_ip
     * [3] : Source_port
     * [4] : Destination_ip
     * [5] : Destination_port
     * [6] : vecteur
     * [7] : prediction
     */
    int id;
    String time;
    String Source_ip;
    String Destination_ip;
    String Source_port;
    String Destination_port;
    double vecteur[] = new double[17];
    String Label;
    String prediction;
    String prediction_lvl2;
        
}
