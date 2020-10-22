/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDS_Package;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author mohamed
 */
public class Primary_Frame extends JFrame implements ActionListener{
    
    JPanel panel = new JPanel();
    JButton BOpen,BStart;
    JRadioButton R_KNN,R_CNN;
    static Controller controller;
    ButtonGroup methods;
    
    public Primary_Frame() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
        Init();
    }
    
    public void Init() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        panel.setLayout(null);
        //Choose method
        JLabel method_label = new JLabel("choose detection method",SwingConstants.CENTER);
        method_label.setBounds(125, 30, 150, 20);
        panel.add(method_label);
        // Radio button
        R_KNN = new JRadioButton("KNN");
        R_CNN = new JRadioButton("CNN");
        R_CNN.setSelected(true);
        R_KNN.setActionCommand("train_normalised");
        R_CNN.setActionCommand("train_cnn_normalised");
        methods = new ButtonGroup();
        methods.add(R_KNN);
        methods.add(R_CNN); 
        R_KNN.setBounds(110, 60, 150, 20);
        R_KNN.setHorizontalAlignment(SwingConstants.CENTER);        
        R_CNN.setBounds(110, 80, 150, 20);
        R_CNN.setHorizontalAlignment(SwingConstants.CENTER);
        R_KNN.addActionListener(this);
        R_CNN.addActionListener(this);
        panel.add(R_KNN);
        panel.add(R_CNN);
        
        // Open Button
        BOpen = new JButton("Open dashboard");
        BOpen.setBounds(200, 140, 180, 20);
        panel.add(BOpen);
        BOpen.addActionListener(this);
        
        // Start Button
        BStart = new JButton("start server");
        BStart.setBounds(10, 140, 180, 20);
        panel.add(BStart);
        BStart.addActionListener(this);
        
        
        //Frame 
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("IDS");
        setSize(400, 220);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(BStart)){
            String m = methods.getSelection().getActionCommand();
            controller = new Controller(m);
            Thread Thread_Controller = new Thread(controller);
            Thread_Controller.start();
            BStart.setEnabled(false);
        }
        if(e.getSource().equals(BOpen)){
            Desktop desktop = java.awt.Desktop.getDesktop();
            try {
                URI oURL = new URI("http://localhost:8082/Final_IDS/newjsp.jsp");
			desktop.browse(oURL);
            } catch (URISyntaxException ex) {
                Logger.getLogger(Primary_Frame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Primary_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }    
}
