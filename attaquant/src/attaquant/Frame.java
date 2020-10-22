/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attaquant;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mohamed
 */
public class Frame extends JFrame implements ActionListener{
    
    Attaque attaque = new Attaque();
    JPanel panel = new JPanel();
    JButton Charger,fileC,BAttaque;
    JTextField ip,s_ip,s_port,port,limit_PR,limit_NB,File_name;
    JTextField txtAddress;
    JFileChooser fc;
    int returnVal;
    int MIN_VALUE = 0, MAX_VALUE = 0;
    JTable j;
    static JSlider Limit_slider;
    DefaultTableModel tableModel = new DefaultTableModel();
    
    public Frame() throws ParseException{
        Init();
    }

    private void Init() throws ParseException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        panel.setLayout(null);
        
        /**
         * parametre
         */
        JLabel label = new JLabel("Settings");  
        label.setBounds(10, 10, 150, 20);
        panel.add(label);
        
        // ip
        JLabel s_ip_label = new JLabel("Destination IP (server)");  
        s_ip_label.setBounds(10, 30, 150, 20);
        panel.add(s_ip_label);
        s_ip = new JTextField("");
        s_ip.setBounds(10, 50, 150, 20);
        panel.add(s_ip);
        
        // port
        JLabel s_port_label = new JLabel("Port (server)");  
        s_port_label.setBounds(10, 70, 150, 20);
        panel.add(s_port_label);
        s_port = new JTextField("1112");
        s_port.setBounds(10, 90, 150, 20);
        panel.add(s_port);
        
        /**
         * client
         */
        
        // ip
        JLabel ip_label = new JLabel("Destination IP (client)");  
        ip_label.setBounds(10, 110, 150, 20);
        panel.add(ip_label);
        ip = new JTextField("");
        ip.setBounds(10, 130, 150, 20);
        panel.add(ip);
        
        // port
        JLabel port_label = new JLabel("Port (client)");  
        port_label.setBounds(10, 150, 150, 20);
        panel.add(port_label);
        port = new JTextField("");
        port.setBounds(10, 170, 150, 20);
        panel.add(port);
                
        // limit
        JLabel limit_label = new JLabel("packets Number");  
        limit_label.setBounds(10, 200, 150, 20);
        panel.add(limit_label);
        
        JLabel limit_label_PR = new JLabel("%");  
        limit_label_PR.setBounds(80, 220, 70, 20);
        panel.add(limit_label_PR);                
        limit_PR = new JTextField("");
        limit_PR.setBounds(10, 220, 60, 20);
        limit_PR.addActionListener(this);
        panel.add(limit_PR);
        
        JLabel limit_label_NB = new JLabel("packets");  
        limit_label_NB.setBounds(80, 250, 70, 20);
        panel.add(limit_label_NB);
        limit_NB = new JTextField("");
        limit_NB.setBounds(10, 250, 60, 20);
        limit_NB.addActionListener(this);
        panel.add(limit_NB);
        //*******************************************//
        Limit_slider = new JSlider(MIN_VALUE, MAX_VALUE, 0);
        Limit_slider.setPaintTrack(true); 
        Limit_slider.setPaintTicks(true); 
        Limit_slider.setPaintLabels(true);
        
        
        Limit_slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                limit_NB.setText(""+Limit_slider.getValue());
                double pourcentage;
                if(Limit_slider.getMaximum() > 0){
                    double value = Limit_slider.getValue();
                    double max = Limit_slider.getMaximum();
                    pourcentage = (value/max)*100;
                }else{
                    pourcentage = 0;
                }
                limit_PR.setText(String.format("%.2f", pourcentage));
            }
        });
        Limit_slider.setBounds(10, 280, 150, 20);
        panel.add(Limit_slider);
        
        //*******************************************//
        // button charger
        Charger = new JButton();
        Charger.setText("Load database");
        Charger.setBounds(800, 10, 150, 20);
        panel.add(Charger);      
        Charger.addActionListener(this);
        
        // button start
        BAttaque = new JButton();
        BAttaque.setText("start attack");
        BAttaque.setBounds(10, 320, 150, 20);
        panel.add(BAttaque);      
        BAttaque.addActionListener(this);
        
        //Create a file chooser
        //button
        fileC = new JButton();
        fileC.setText("Choose file");
        fileC.setBounds(640, 10, 150, 20);
        panel.add(fileC);      
        fileC.addActionListener(this);
        
        File_name = new JTextField("");
        File_name.setBounds(200, 10, 430, 20);
        File_name.setEditable(false);
        File_name.setBackground(Color.WHITE);
        panel.add(File_name);
        
        // table de vecteur
        j = new JTable(tableModel);
        
        // Column Names
        String[] columnNames = {
            "logged_in","dst_host_same_srv_rate","dst_host_serror_rate","same_srv_rate","dst_host_srv_serror_rate","dst_host_srv_count","dst_host_srv_rerror_rate","srv_rerror_rate","dst_host_same_src_port_rate","protocol_type","flag","service","count","diff_srv_rate","is_guest_login","root_shell","wrong_fragment","Class"
        };
        for (String columnName : columnNames) {
            tableModel.addColumn(columnName);
        }
        
        
        // adding it to JScrollPane 
        JScrollPane sp = new JScrollPane(j);
        sp.setBounds(200, 40, 750, 300);
        panel.add(sp);
        
        add(panel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("...Attacker...");
        setSize(1000, 400); 
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(Charger)){
            if(!File_name.getText().isEmpty()){ //!ip.getText().isEmpty() && !port.getText().isEmpty() && !limit.getText().isEmpty() && 
                try {
                    attaque.read_from_file(File_name.getText());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if(e.getSource().equals(BAttaque)){
            if(!s_ip.getText().isEmpty() && !s_port.getText().isEmpty() && !ip.getText().isEmpty() && !port.getText().isEmpty() && !limit_NB.getText().isEmpty() && !File_name.getText().isEmpty()){
                attaque.start(ip.getText(),port.getText(),limit_NB.getText(),s_ip.getText(),s_port.getText());
            }
        }
        if(e.getSource().equals(fileC)){
            //chooser
            fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            fc.setFileFilter(filter);
            returnVal = fc.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File_name.setText(fc.getSelectedFile().getAbsolutePath());
            }
        }
        if(e.getSource().equals(limit_NB)){
            Limit_slider.setValue(Integer.parseInt(limit_NB.getText()));
        }
        if(e.getSource().equals(limit_PR)){
            double pr = Double.parseDouble(limit_PR.getText());
            double slider_set = (pr/100)*Limit_slider.getMaximum();
            Limit_slider.setValue((int) slider_set);
        }
    }
}