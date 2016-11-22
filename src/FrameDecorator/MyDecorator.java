/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameDecorator;

import Properties.Properties_Bundel;
import java.awt.Color;
import java.awt.Component;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author polina
 */
public class MyDecorator {

    private static HashMap<Object, JFrame> Modality = new HashMap<>();

  
    private static void DecorateFrame(Component[] components) {
        for (Component compenent : components) {
            System.out.println("search started");

            if (compenent instanceof JViewport) {
                JViewport Viewport = (JViewport) compenent;
                System.out.println("done decorating Viewport of jtable");
                DecorateFrame(Viewport.getComponents());
            } else {
                if (compenent instanceof JScrollBar) {
                    JScrollBar ScrollBar = (JScrollBar) compenent;
                    System.out.println("done decorating ScrollBar of jtable");
                    DecorateFrame(ScrollBar.getComponents());
                } else /**
                 * Housseyn ****************** style du background du tableau
                 * spécifié dans le scroll pane valeur mise vers le blan théme
                 * white
                 */
                {
                    if (compenent instanceof JScrollPane) {
                        JScrollPane jScrollPane = (JScrollPane) compenent;
                        jScrollPane.getViewport().setBackground(Color.WHITE);
                        System.out.println("done decorating scrollpane of jtable");
                        DecorateFrame(jScrollPane.getComponents());
                    } else /**
                     * Housseyn ****************** découverte de tout element
                     * dans un panel théme white
                     */
                    {
                        if (compenent instanceof JPanel) {
                            JPanel panel = (JPanel) compenent;
                            System.out.println("Discovering Panel Components");
                            DecorateFrame(panel.getComponents());
                        } else /**
                         * Housseyn ****************** Découverte d'élements
                         * dans un tabbedPane théme white
                         */
                        {
                            if (compenent instanceof JTabbedPane) {
                                JTabbedPane tabbedpane = (JTabbedPane) compenent;
                                System.out.println("Discovering TabbedPane Components");
                                DecorateFrame(tabbedpane.getComponents());
                            } else /**
                             * Housseyn ****************** Découverte d'élements
                             * dans un tabbedPane théme white
                             */
                            {
                                if (compenent instanceof JRootPane) {
                                    JRootPane rootPane = (JRootPane) compenent;
                                    System.out.println("Discovering rootpane Components");
                                    DecorateFrame(rootPane.getComponents());
                                } else /**
                                 * Housseyn ****************** Découverte
                                 * d'élements dans un JLayeredPane théme white
                                 */
                                {
                                    if (compenent instanceof JLayeredPane) {
                                        JLayeredPane LayeredPane = (JLayeredPane) compenent;
                                        System.out.println("Discovering LayeredPane Components");
                                        DecorateFrame(LayeredPane.getComponents());
                                    } else /**
                                     * Housseyn ****************** Découverte
                                     * d'élements dans un JLayeredPane théme
                                     * white
                                     */
                                    {
                                        if (compenent instanceof JTable) {
                                            JTable Table = (JTable) compenent;
                                            System.out.println("Applying JTable Style");

                                            DefaultTableCellRenderer DTCR = new DefaultTableCellRenderer() {

                                                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                                                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                                                    setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
                                                    return this;
                                                }

                                            };
                                            Table.setDefaultRenderer(Object.class, DTCR);
                                            
                                            // adding the table count for position
                                            

                                            DecorateFrame(Table.getComponents());
                                        } else {
                                            System.out.println(compenent.getClass().getName());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void undecorate(JFrame j) {
        
        JFrame parent =Modality.remove(j);
        parent.setEnabled(true);
        parent.setVisible(true);
        parent.setAlwaysOnTop(true);
        parent.show();
        
        Field[] declaredFields = j.getClass().getDeclaredFields();
        System.out.println("all fields :");
        for (Field declaredField : declaredFields) {
            System.out.println("Field : "+declaredField.getName()+" ---  "+declaredField.getGenericType().getTypeName());
        }
        j.dispose();
    }

    public static void Decorate(JFrame j, JFrame Caller) {
        j.setLocationRelativeTo(null);
        j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        j.setAlwaysOnTop(true);
        
        j.addWindowListener(
           new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                if (Caller != null) {
                    undecorate(j);
                } else {
                    j.dispose();
                }
                
            }
           }
        );

        Properties_Bundel.setPropertiesBundelRessource(Properties_Bundel.Strings);
        // j.setTitle(Properties_Bundel.getString("AppName") + " - " + Properties_Bundel.getString(j.getClass().getSimpleName()));
        
      
        
        j.setVisible(true);
        Component[] compenents = j.getComponents();
        System.out.println(compenents.length);
        DecorateFrame(compenents);
        Modality.put(j, Caller);
        if (Caller != null) {
            
            Caller.setEnabled(false);
            Caller.setAlwaysOnTop(false);
        }
        j.pack();
          Method[] declaredMethods = j.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("class "+j.getName()+" method :"+declaredMethod.getName());
            if ("InitFrame".equals(declaredMethod.getName())) {
                try {
                    declaredMethod.invoke(j, null);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(MyDecorator.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(MyDecorator.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(MyDecorator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void Decorate(JDialog j) {
        j.setLocationRelativeTo(null);
        j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Properties_Bundel.setPropertiesBundelRessource(Properties_Bundel.Strings);
        // j.setTitle(Properties_Bundel.getString("AppName") + " - " + Properties_Bundel.getString(j.getClass().getSimpleName()));
        j.setVisible(true);
        Component[] compenents = j.getComponents();
        System.out.println(compenents.length);
        DecorateFrame(compenents);
        j.setModal(true);
        j.pack();
    }

    public static void Decorate(Class j, JFrame Caller) throws InstantiationException, IllegalAccessException {
        System.out.println("handling started ...");
        if (j.getSuperclass().toString().equals(JFrame.class.toString())) {
            System.out.println("Décorating frame : " + j);
            Decorate((JFrame) j.newInstance(), Caller);
        } else if (j.getSuperclass().toString().equals(JDialog.class.toString())) {
            System.out.println("Décorating frame : " + j);
            Decorate((JDialog) j.newInstance());
        }

    }

}
