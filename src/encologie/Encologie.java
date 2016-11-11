/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encologie;

import ExceptionLogging.MyLogger;
import FrameDecorator.MyDecorator;
import MyLibraries.SQL.SQLTools;
import Properties.Properties_Bundel;
import UI.Authentificate;
import UI.Main;

/**
 *
 * @author polina
 */
public class Encologie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        // TODO code application logic here
        
        {
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    System.out.println(info.getName());
                    if ("Windows".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(MyDecorator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(MyDecorator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(MyDecorator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(MyDecorator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        
        try {
            
            SQLTools.Setup(); // setup the sql engine to handle queries

            
            Properties_Bundel.setPropertiesBundelRessource(Properties_Bundel.AppConfMode);
            
            if (Properties_Bundel.getState(Properties_Bundel.Devlopper_Mode)) {
                MyDecorator.Decorate(Main.class, null);
            } else {
                MyDecorator.Decorate(Authentificate.class, null);
            }
            
        } catch (Exception e) {
            MyLogger.Log_to_local(e);
        }
    }
    
}
