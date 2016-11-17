/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial;

import FrameDecorator.MyDecorator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author polina
 */
public class Launcher {
    public static void main(String[] args) {
        try {
            MyDecorator.Decorate(NewJFrame.class, null);
        } catch (InstantiationException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
