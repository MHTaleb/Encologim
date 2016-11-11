/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyLibraries.JtableTools;

import ExceptionLogging.MyLogger;
import MyLibraries.SQL.SQLTools;
import Properties.Properties_Bundel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Hashtable;
import javax.swing.Action;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author Housseyn in thsi file i ll manage tables using sql tool
 *
 */
public class JTableSQLTool {

    private static Hashtable<JTable, ArrayList<Action>> MyAllListeners;

    public static ResultSet FillTableDataFromQuery(String Query, JTable table) throws SQLException {
        try {

            SQLTools.Setup();
            ResultSet rs = SQLTools.ExecuteQuery(Query);
            DefaultTableModel defaultTableModel = new DefaultTableModel();
            /**
             * Author housseyn Building Table header
             *
             */

            Properties_Bundel.setPropertiesBundelRessource(Properties_Bundel.HeadsCaption);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] heads = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                heads[i] = metaData.getColumnLabel(i + 1);
//            System.out.print("column i=" + i + "Name =" + heads[i] + "  -------]]"
//                    + " getString(" + metaData.getTableName(i + 1) + "_" + metaData.getColumnLabel(i + 1) + ")");
                System.out.println("hehooooooo    " + metaData.getTableName(i + 1).split("_")[0]);
                heads[i] = Properties_Bundel.getString(metaData.getTableName(i + 1).split("_")[0] + "_" + metaData.getColumnLabel(i + 1));
                System.out.println("      --  >>> after resolution : " + heads[i]);

            }
            defaultTableModel.setColumnCount(columnCount);
            defaultTableModel.setColumnIdentifiers(heads);

            //-----------------------------------------------------------    
            int CurrentRow = 0;
            while (rs.next()) {
                defaultTableModel.addRow(new Object[columnCount]);
                for (int i = 0; i < columnCount; i++) {
                    System.out.print(metaData.getColumnName(i + 1) + " --> " + rs.getObject(i + 1) + "    ;    ");
                    defaultTableModel.setValueAt(rs.getObject(i + 1), CurrentRow, i);
                }
                CurrentRow++;
                System.out.println("");
                System.out.println("------------------------------------------------------");

            }

            table.setModel(defaultTableModel);
//            for (int i = 0; i < table.getColumnCount(); i++) {
//                table.getColumn(table.getColumnName(i)).setIdentifier(heads[i]);
//
//            }
            return rs;
        } catch (Exception e) {
            MyLogger.Log_to_local(e);
            e.printStackTrace();

        }
        return null;
    }

    private static void HideColumn(JTable table, String ColumnName) {
        Enumeration<TableColumn> columns = table.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn CurrColumn = columns.nextElement();
            if (CurrColumn.getIdentifier().toString().toLowerCase().equals(ColumnName.toLowerCase())) {

                CurrColumn.setWidth(0);
                CurrColumn.setMinWidth(0);
                CurrColumn.setMaxWidth(0);

                System.out.println("hide done for :[" + ColumnName + "]");
            }
        }
    }

    private static void HideColumn(JTable table, int ColumnName) {
        TableColumn CurrColumn = table.getColumnModel().getColumn(ColumnName);

        CurrColumn.setWidth(0);
        CurrColumn.setMinWidth(0);
        CurrColumn.setMaxWidth(0);

        System.out.println("hide done for :[" + ColumnName + "]");

    }

    public static final void HideColumns(JTable table, String[] Columns) {
        for (String Column : Columns) {
            HideColumn(table, Column);
        }
    }

    public static final void HideColumns(JTable table, int[] Columns) {
        for (int Column : Columns) {
            HideColumn(table, Column);
        }
    }

    public static final Hashtable RetrieveSelectedData(JTable table) {
        try {
            Hashtable<String, Object> retreivedData = new Hashtable<String, Object>();
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow >= 0) {
                        String msg = "";
                        int columnCount = table.getColumnModel().getColumnCount();
                        for (int i = 0; i < columnCount; i++) {
                            retreivedData.put(table.getColumnName(i), table.getValueAt(selectedRow, i));
                            msg += "\n " + table.getColumnName(i) + "-->" + table.getValueAt(selectedRow, i);
                        }

                        System.out.println(msg);
                    }
                }

            });
            return retreivedData;
        } catch (Exception e) {
            MyLogger.Log_to_local(e);
        }
        return null;
    }

    private static final String GetCorrespondingColumn(JTable table, String UserBindValue) {
        if (!UserBindValue.isEmpty()) {
            for (int i = 0; i < table.getColumnCount(); i++) {
                System.out.println(table.getColumnName(i) + "-------" + Properties_Bundel.getString(UserBindValue));
                if (table.getColumnName(i).equals(Properties_Bundel.getString(UserBindValue))) {

                    System.out.println("found   column" + i);
                    return "column" + i;
                }
            }
        }

        return "";
    }

    public static final void BindTableToFields(JTable Table, Object[] Fields) {

        for (Object Field : Fields) {

            BeanProperty<JTable, Object> tableBeanProperty;

            String DesignCallID;
            String PropertyName;

            if (Field instanceof JTextField) {
                //<editor-fold>
                BeanProperty<JTextField, String> BindingFieldProperty;
                Binding<JTable, Object, JTextField, String> binding;
                JTextField jTextField = (JTextField) Field;
                DesignCallID = jTextField.getText();
                PropertyName = "selectedElement." + GetCorrespondingColumn(Table, DesignCallID);
                jTextField.setText("");
                System.out.println("property name =" + PropertyName.trim());

                tableBeanProperty = BeanProperty.create(PropertyName);
                BindingFieldProperty = BeanProperty.create("text");
                binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
                        Table,
                        tableBeanProperty,
                        jTextField,
                        BindingFieldProperty);
                Converter<Object, String> old = binding.getConverter();

                Converter<Object, String> converter = new Converter<Object, String>() {
                    @Override
                    public String convertForward(Object value) {
                        try {

                            if (value instanceof HashMap) {
                                HashMap hashMap = (HashMap) value;
                                System.out.println(hashMap);
                                // Object get = hashMap.get(GetCorrespondingColumn(Table, DesignCallID));
                                return (String) hashMap.get(GetCorrespondingColumn(Table, DesignCallID));

                            } else if (value instanceof String) {
                                return (String) value;
                            } else if (value instanceof Integer) {
                                Integer integer = (Integer) value;
                                return integer.toString();
                            } else if (value instanceof Float) {
                                Float float1 = (Float) value;
                                return float1.toString();
                            } else if (value instanceof Double) {
                                Double double1 = (Double) value;
                                return double1.toString();
                            } else if (value instanceof Date) {
                                Date date = (Date) value;
                                return date.toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
                            }
                            MyLogger.Log_to_local(new Exception("Binding Class Not Handled" + value.getClass().getName()));
                            return "";

                        } catch (Exception e) {
                            e.printStackTrace();
                            MyLogger.Log_to_local(e);

                        }
                        return "impossible";
                    }

                    @Override
                    public Object convertReverse(String value) {
                        try {
                            return value;

                        } catch (Exception e) {

                            e.printStackTrace();

                        }
                        return null;
                    }

                };
                binding.setConverter(converter);
                binding.bind();
                //</editor-fold>
            } else if (Field instanceof JPasswordField) {
                //<editor-fold>
                BeanProperty<JPasswordField, String> BindingFieldProperty;
                Binding<JTable, Object, JPasswordField, String> binding;
                JPasswordField jPasswordField = (JPasswordField) Field;
                DesignCallID = jPasswordField.getText();
                PropertyName = "selectedElement." + GetCorrespondingColumn(Table, DesignCallID);
                jPasswordField.setText("");

                System.out.println("property name =" + PropertyName.trim());

                tableBeanProperty = BeanProperty.create(PropertyName);
                BindingFieldProperty = BeanProperty.create("text");
                binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
                        Table,
                        tableBeanProperty,
                        jPasswordField,
                        BindingFieldProperty);
                Converter<Object, String> old = binding.getConverter();

                Converter<Object, String> converter = new Converter<Object, String>() {
                    @Override
                    public String convertForward(Object value) {
                        try {

                            if (value instanceof HashMap) {
                                HashMap hashMap = (HashMap) value;
                                System.out.println(hashMap);
                                // Object get = hashMap.get(GetCorrespondingColumn(Table, DesignCallID));
                                return (String) hashMap.get(GetCorrespondingColumn(Table, DesignCallID));

                            } else if (value instanceof String) {
                                return (String) value;
                            } else if (value instanceof Integer) {
                                Integer integer = (Integer) value;
                                return integer.toString();
                            } else if (value instanceof Float) {
                                Float float1 = (Float) value;
                                return float1.toString();
                            } else if (value instanceof Double) {
                                Double double1 = (Double) value;
                                return double1.toString();
                            } else if (value instanceof Date) {
                                Date date = (Date) value;
                                return date.toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
                            }
                            MyLogger.Log_to_local(new Exception("Binding Class Not Handled" + value.getClass().getName()));
                            return "";

                        } catch (Exception e) {
                            e.printStackTrace();
                            MyLogger.Log_to_local(e);
                            JOptionPane.showMessageDialog(null, "Veuillez contacter votre Fournisseur BUG ISSUE N°78952");

                        }
                        return "impossible";
                    }

                    @Override
                    public Object convertReverse(String value) {
                        try {
                            return value;

                        } catch (Exception e) {

                            e.printStackTrace();

                        }
                        return null;
                    }

                };
                binding.setConverter(converter);
                binding.bind();
                //</editor-fold>
            } else if (Field instanceof JComboBox) {
                //<editor-fold>
//                        System.out.println("binding ");
//                        JComboBox jComboBox = (JComboBox) Field;
//
//                        BeanProperty<JComboBox, String> BindingFieldProperty;
//                        Binding<JTable, Object, JComboBox, String> binding;
//                        DesignCallID = jComboBox.getName();
//                        PropertyName = "selectedElement." + GetCorrespondingColumn(Table, DesignCallID);
//                        System.out.println("property name =" + PropertyName.trim());
//
//                        tableBeanProperty = BeanProperty.create(PropertyName);
//                        BindingFieldProperty = BeanProperty.create("selectedItem");
//                        binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
//                                Table,
//                                tableBeanProperty,
//                                jComboBox,
//                                BindingFieldProperty);
//                        Converter<Object, String> old = binding.getConverter();
//
//                        Converter<Object, String> converter = new Converter<Object, String>() {
//                            @Override
//                            public String convertForward(Object value) {
//                                try {
//
//                                    if (value instanceof HashMap) {
//                                        HashMap hashMap = (HashMap) value;
//                                        System.out.println(hashMap);
//                                        // Object get = hashMap.get(GetCorrespondingColumn(Table, DesignCallID));
//                                        return (String) hashMap.get(GetCorrespondingColumn(Table, DesignCallID));
//
//                                    } else if (value instanceof String) {
//                                        return (String) value;
//                                    } else if (value instanceof Integer) {
//                                        Integer integer = (Integer) value;
//                                        return integer.toString();
//                                    } else if (value instanceof Float) {
//                                        Float float1 = (Float) value;
//                                        return float1.toString();
//                                    } else if (value instanceof Double) {
//                                        Double double1 = (Double) value;
//                                        return double1.toString();
//                                    } else if (value instanceof Date) {
//                                        Date date = (Date) value;
//                                        return date.toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
//                                    }
//                                    MyLogger.Log_to_local(new Exception("\nBinding Class Not Handled" + value.getClass().getName()));
//                                    
//                                    return "";
//
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                    MyLogger.Log_to_local(e);
//
//                                }
//                                return "impossible";
//                            }
//
//                            @Override
//                            public Object convertReverse(String value) {
//                                try {
//                                    return value;
//
//                                } catch (Exception e) {
//
//                                    e.printStackTrace();
//
//                                }
//                                return null;
//                            }
//
//                        };
//                        binding.setConverter(converter);
//                        binding.bind();
                //</editor-fold> // bindings.java code attempt
                //<editor-fold>
                try {
                    JComboBox jcb = (JComboBox) Field;
                    Table.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent me) {
                            System.out.println("event started for " + jcb.getName());
                            int selectedRow = Table.getSelectedRow();
                            System.out.println("table selected row" + selectedRow);
                            Object valueAt = Table.getValueAt(selectedRow, Integer.parseInt("" + GetCorrespondingColumn(Table,
                                    jcb.getName()).charAt(GetCorrespondingColumn(Table, jcb.getName()).length() - 1)));
                            System.out.println("Table Object :" + valueAt);
                            try {
                                jcb.setSelectedIndex((int) valueAt);

                            } catch (Exception e) {
                                try {
                                    jcb.setSelectedItem(valueAt);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                            }

                        }

                        @Override
                        public void mousePressed(MouseEvent me) {
                            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public void mouseReleased(MouseEvent me) {
                            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public void mouseEntered(MouseEvent me) {
                            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public void mouseExited(MouseEvent me) {
                            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    MyLogger.Log_to_local("Retreiving list of listeners");
                    MyLogger.Log_to_local(e);
                }
                //</editor-fold> // jtable mouse listener
            } else if (Field instanceof JCheckBox) {
                JCheckBox jCheckBox = (JCheckBox) Field;
                
            }
        }

        return;
    }

}
