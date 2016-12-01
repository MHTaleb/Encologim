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
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import org.jdesktop.swingx.combobox.ListComboBoxModel;
import org.jdesktop.swingx.combobox.MapComboBoxModel;

/**
 *
 * @author polina
 */
public class JComboBoxTools {

    public static void setupID_TITLE_ComboBox(JComboBox jComboBox) throws SQLException {

        jComboBox = null;

        String name = jComboBox.getName();
        String tableName = name.split("_")[1];

        String query = "SELECT ID,TITRE FROM " + tableName + ";";

        ResultSet myJComboResultSet = SQLTools.ExecuteQuery(query);
        ArrayList visualItems = new ArrayList();
        ArrayList IDItems = new ArrayList();

        while (myJComboResultSet.next()) {
            visualItems.add(myJComboResultSet.getObject("TITRE"));
            IDItems.add(myJComboResultSet.getObject("ID"));
        }
        jComboBox = new JComboBox(visualItems.toArray()) {
            ArrayList values = IDItems;

            @Override
            public Object getSelectedItem() {
                return this.values.get(this.getSelectedIndex()); //To change body of generated methods, choose Tools | Templates.
            }

        };
    }

    public static void setupID_TITLE_ComboBox(JComboBox jComboBox, String tableName) throws SQLException {

        String query = "SELECT ID,TITRE FROM " + tableName + ";";
        ResultSet myJComboResultSet = SQLTools.ExecuteQuery(query);

        HashMap myMap = new HashMap();
        while (myJComboResultSet.next()) {  
            myMap.put(myJComboResultSet.getObject("TITRE"), myJComboResultSet.getObject("ID"));
        }

        jComboBox.setModel(new MapComboBoxModel(myMap) {
            
            @Override
            public Object getValue(Object selectedItem) {
                return map_data.get(selectedItem); //To change body of generated methods, choose Tools | Templates.
            }

        });
        

    }
}
