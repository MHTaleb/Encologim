/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyLibraries.JPanelTools;

import MyLibraries.JtableTools.JTableSQLTool;
import MyLibraries.SQL.SQLTools;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.netbeans.lib.awtextra.AbsoluteLayout;

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

//        target.getX(),
//                target.getY(),
//                target.getWidth(),
//                target.getHeight(),
//                0, 0,
//                GridBagConstraints.ABOVE_BASELINE,
//                0,
//                new Insets(5, 5, 5, 5),
//                0, 0
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;

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
    private static ArrayList<Component> myAllComponents ;

    private static final void getAllComponents(final JPanel myPanel) {

        Component[] components = myPanel.getComponents();
        for (Component component : components) {
            if (!(component instanceof JPanel)) {
                myAllComponents.add(component);
            }else{
                getAllComponents((JPanel)component);
            }
        }

    }
    private static final Component[] SynchronizedGetAllComponents(final JPanel parentPanel){
        myAllComponents = new ArrayList<Component>();
        getAllComponents(parentPanel);
        
        Component[] allComponents = new Component[myAllComponents.size()];
        Iterator<Component> iterator = myAllComponents.iterator();
        int i =0;
        while (iterator.hasNext()) {
            Component next = iterator.next();
            allComponents[i] = next ;
            i++;
        }
        return allComponents;
    }

    
    
    // jpaneltools est la class moteur : la fonction qui gere la recherche est dessou
    public static final void linkSearchPanelToTable(final JPanel mySearchPanel, final JTable mySearchTable, final String[] HideColumns, final ResultSet myJtableResultSet, final String[] whereSelectionValues) {
        Component[] components =  SynchronizedGetAllComponents(mySearchPanel);
        JTextField searchingField = null;
        JComboBox searchFilter = null;
        for (Component component : components) {
            if (component instanceof JTextField) {
                searchingField = (JTextField) component;
            } else {
                if (component instanceof JComboBox) {
                    searchFilter = (JComboBox) component;

                } else {
                    if (component instanceof JPanel) {
                        JPanel jPanel = (JPanel) component;

                    }
                }
            }
        }
        final JTextField EventField = searchingField;
        final JComboBox EventFilter = searchFilter;

        searchingField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                boolean empty = EventField.getText().isEmpty();
                String query = SQLTools.getQuery(myJtableResultSet);
                if (!empty) {
                    String text = EventField.getText();
                    int Choice = 0;
                    try {
                        if (!(text.charAt(0) == '0')) {

                            float parseFloat = Float.parseFloat(text);
                            Choice = 1;
                        }

                    } catch (Exception e) {
                        // e.printStackTrace();
                    }
                    query = query.replace(";", "");
                    query += " WHERE " + whereSelectionValues[EventFilter.getSelectedIndex()];
                    query += (Choice == 0) ? " LIKE \"%" + text + "%\";" : "=" + text + ";";
                    System.out.println("Query is :" + query);
                }
                try {
                    JTableSQLTool.FillTableDataFromQuery(query, mySearchTable);
                    JTableSQLTool.HideColumns(mySearchTable, HideColumns);
                } catch (SQLException ex) {
                    Logger.getLogger(JPanelTools.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
    }
}
