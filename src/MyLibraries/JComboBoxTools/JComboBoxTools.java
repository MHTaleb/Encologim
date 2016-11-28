/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyLibraries.JComboBoxTools;

import MyLibraries.SQL.SQLTools;
import java.awt.AWTEventMulticaster;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author polina
 */
public class JComboBoxTools {
    public static void setupID_TITLE_ComboBox(JComboBox jComboBox) throws SQLException{
        String name = jComboBox.getName();
        String tableName = name.split("_")[1];
        
        String query = "SELECT ID,TITRE FROM "+tableName+";";
        
        ResultSet myJComboResultSet = SQLTools.ExecuteQuery(query);
        ArrayList visualItems = new ArrayList();
        ArrayList IDItems = new ArrayList();
        
        while (myJComboResultSet.next()) {
            visualItems.add(myJComboResultSet.getObject("TITRE"));
            IDItems.add(myJComboResultSet.getObject("ID"));
        }
        jComboBox = new JComboBox(visualItems.toArray()){
            ArrayList values = IDItems;
            @Override
            public Object getSelectedItem() {
                return values.get( super.getSelectedIndex()); //To change body of generated methods, choose Tools | Templates.
            }
         
        };
    }
    
    
    public static void setupID_TITLE_ComboBox(JComboBox jComboBox, String tableName) throws SQLException {

        String query = "SELECT ID,TITRE FROM " + tableName + ";";
        ResultSet myJComboResultSet = SQLTools.ExecuteQuery(query);
        
        ArrayList visualItems = new ArrayList();
        ArrayList idItems = new ArrayList();

        while (myJComboResultSet.next()) {
            visualItems.add(myJComboResultSet.getObject("TITRE"));
            idItems.add(myJComboResultSet.getObject("ID"));
        }
        
        System.out.println("IDItems=" + idItems);
        System.out.println("visualItems=" + visualItems);
        
        jComboBox = new JComboBox(visualItems.toArray()) {
            ArrayList values = idItems;

            @Override
            public Object getSelectedItem() {
                Object get = values.get(super.getSelectedIndex());
                System.out.println("get = " + get);
                return get; //To change body of generated methods, choose Tools | Templates.
            }

        };

    }
}
