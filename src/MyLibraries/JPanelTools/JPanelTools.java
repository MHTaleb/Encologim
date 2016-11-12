/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyLibraries.JPanelTools;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author polina
 */
public class JPanelTools {

    public static final void ShowPanel(JPanel target, JPanel object) {
        target.removeAll();

        Dimension size = object.getSize();
        size.setSize(size.width, target.getHeight());
        target.setSize(object.getSize());

        GridBagLayout gridBagLayout = new GridBagLayout();

        target.setLayout(gridBagLayout);

        GridBagConstraints gbc = new GridBagConstraints(target.getX(),
                target.getY(),
                target.getWidth(),
                target.getHeight(),
                0, 0,
                GridBagConstraints.ABOVE_BASELINE,
                0,
                new Insets(5, 5, 5, 5),
                0, 0);

        target.add(object, gbc);
        target.invalidate();
        target.revalidate();
        target.validate();
        target.repaint();
        target.show();
        object.validate();
        object.repaint();
        object.show();

        Container Frame = target.getParent();
        Container Current = target.getParent();
        while ((Current != null)) {
            System.out.println("current =" + Current.getClass().getName());
            Frame = Current;
            Current = Current.getParent();
        }
        System.out.println("frame " + Frame.getClass().getName());
        if (Frame != null) {
            System.out.println("pack");
            JFrame MyFrame = (JFrame) Frame;
            int extendedState = MyFrame.getExtendedState();
            if (extendedState != JFrame.MAXIMIZED_BOTH) {

                MyFrame.pack();
                MyFrame.setExtendedState(extendedState);
            }
        }
    }
}
