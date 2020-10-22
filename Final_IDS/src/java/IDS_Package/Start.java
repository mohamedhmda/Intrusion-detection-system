/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDS_Package;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author mohamed
 */
@WebListener
public class Start implements ServletContextListener  {
    Primary_Frame frame;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            frame = new Primary_Frame();                
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
    
}
