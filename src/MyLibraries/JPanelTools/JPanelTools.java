/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyLibraries.JPanelTools;

import java.awt.GridBagConstraints;
import javax.swing.JPanel;

/**
 *
 * @author polina
 */
public class JPanelTools {
    public static final void ShowPanel(JPanel target, JPanel object){
       
        target.add(object, new GridBagConstraints());
        target.invalidate();
        target.revalidate();
        target.validate();
        target.repaint();
        target.show();
        object.validate();
        object.repaint();
        object.show();
        
    }
}
