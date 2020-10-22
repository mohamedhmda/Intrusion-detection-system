/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author mohamed
 */
public class FrameC extends JFrame implements ActionListener{
    
    JPanel panel = new JPanel();
    JButton start;
    JTextField port,total;
    JTable j;
    DefaultTableModel tableModel = new DefaultTableModel();
    Recepteur recepteur;
    
    public FrameC(){
        init();
    }    
    
    private void init() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FrameC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FrameC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FrameC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        panel.setLayout(null);
        
        // port
        JLabel port_label = new JLabel("Port");  
        port_label.setBounds(130, 10, 50, 20);
        panel.add(port_label);
        port = new JTextField("");
        port.setBounds(180, 10, 150, 20);
        panel.add(port);
        
        // button start
        start = new JButton();
        start.setText("start reception");
        start.setBounds(350, 10, 150, 20);
        panel.add(start);      
        start.addActionListener(this);
        
        // total 
        JLabel total_lab = new JLabel("Total packets received"); 
        total_lab.setBounds(520, 10, 120, 20);
        panel.add(total_lab);
        total = new JTextField("");
        total.setBounds(650, 10, 50, 20);
        total.setEditable(false);
        total.setBackground(Color.WHITE);
        panel.add(total);
        
        // table de vecteur
        j = new JTable(tableModel);
        
        // Column Names
        /*String[] columnNames = {
            "logged_in","dst_host_same_srv_rate","dst_host_serror_rate","same_srv_rate","dst_host_srv_serror_rate","dst_host_srv_count","dst_host_srv_rerror_rate","srv_rerror_rate","dst_host_same_src_port_rate","protocol_type","flag","service","count","diff_srv_rate","is_guest_login","root_shell","wrong_fragment"
        };*/
        String[] columnNames = {
            "id","Source_IP","Source_PORT","packet"
        };
        for (String columnName : columnNames) {
            tableModel.addColumn(columnName);
        }
        
        
        // adding it to JScrollPane 
        JScrollPane sp = new JScrollPane(j);
        sp.setBounds(10, 40, 800, 500);
        TableColumnModel columnModel = j.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(560);
        panel.add(sp);
        
        add(panel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("...Client...");
        setSize(840, 600); 
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(start)){
            if(!port.getText().isEmpty()){
                recepteur = new Recepteur(port.getText());
                new Thread(recepteur).start();
                start.setEnabled(false);
            }
        }
    }
}
