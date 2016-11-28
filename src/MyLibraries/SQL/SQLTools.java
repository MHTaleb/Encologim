/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyLibraries.SQL;

import DataBaseIstall.DBInstall;
import MyLibraries.JDBCConnectionPool;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author polina
 */
public class SQLTools {

    private static JDBCConnectionPool pool;

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/encologim";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public SQLTools() {
    }

    public static void Setup() {
        if (Queries == null) {

            Queries = new Hashtable<>();
        }
        if (pool == null) {
           
            pool = new JDBCConnectionPool(JDBC_DRIVER, DB_URL, USER, PASS);
        }
    }

    // void n est pas un mot clé qui site une fonction qui nas pas de retour
    // mais en realité c est un type de retour qui représente un etat vide
    // c pourcela kayen return
    public static void Setup(String DB_URL, String USER, String PASS) {
        // Si la connexion est deja faite alors en termine la methode
        if (pool != null) {
            String GetConnexionInfo = pool.GetConnexionInfo();
            if (!GetConnexionInfo.toLowerCase().equals((DB_URL + "-" + USER + "-" + PASS).toLowerCase())) {
                pool = null;
                pool = new JDBCConnectionPool(JDBC_DRIVER, DB_URL, USER, PASS);
            }
            return;// ici si j ai deja creer un pool alors pas besoin d un autre je termine l execution
        }

        pool = new JDBCConnectionPool(JDBC_DRIVER, DB_URL, USER, PASS); // sinon je creer un pool de connexion
    }
    private static Hashtable<ResultSet, String> Queries;

    public static String getQuery(ResultSet myResultSet) {
        return Queries.getOrDefault(myResultSet, "No Query Found");
    }

    public static String removeQuery(ResultSet myResultSet) {
        return Queries.remove(myResultSet);
    }

    public static ResultSet ExecuteQuery(String Query) {

        try {

            // je recupere l une de mes connexion disponible
            Connection con;
            try {

                con = pool.checkOut();

            } catch (Exception e) {
                //e.printStackTrace();
                try {
                    JOptionPane.showConfirmDialog(null, "please before Database Installation check that you have "
                            + "\nWamp server installaed or Mysql ");
                    DBInstall.InstallDatabase();

                    con = pool.checkOut();

                } catch (Exception ex) {
                    // ex.printStackTrace();
                    return null;
                }

            }

            // depuis cette connexion je prepare mon SQL Statement ( disant que c ets un mechanisme
            // qui est intermédiaire entre moteur mysql et mon appli
            PreparedStatement ps = con.prepareStatement(Query, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_UPDATABLE);//concur updatable sert a mettre des mise a jour
            // si je modifie le resultat de ma requete depui mon appli il change direct dans la BDD mysql

            if (Query.toLowerCase().contains("INSERT".toLowerCase())
                    || Query.toLowerCase().contains("CREATE".toLowerCase())
                    || Query.toLowerCase().contains("UPDATE".toLowerCase())
                    || Query.toLowerCase().contains("DELETE FROM".toLowerCase())) { // si la requete insert ou create database
                ps.executeUpdate(); // j execute avec update c est un mecanisme de l api jdbc
            } else {
                System.out.println(Query);
                ResultSet rs = ps.executeQuery(); //sinon c ets un SELECT alors ta3o c est execute
                pool.checkIn(con); // je me rend compte que je l ai oublié lol lazem nzido hna 
                //en cas que c est une requete select une fois fini il faut que je rend la connexion prise
                Queries.put(rs, Query);
                return rs;
            }

            pool.checkIn(con); // rendre la ressource occupé

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
    
    public static void doInsertQuery(Component[] mySelectedQueryComponents,String mySqlTableName){
        
        String query = "INSERT INTO "+mySqlTableName+"";
        String fields = "(";
        String fieldsClosure=") ";
        String values = " VALUES(";
        String valuesClosure = ");";
        
        for (Component myComponent : mySelectedQueryComponents) {
             String name = myComponent.getName();
             String sqlFieldName=name.replace(mySqlTableName+"_","");
             fields+=sqlFieldName+",";
            if (myComponent instanceof JTextField) {
                JTextField jTextField = (JTextField) myComponent;
                values+="\""+jTextField.getText()+"\",";                
            }
            else
            if (myComponent instanceof JPasswordField) {
                JPasswordField jPasswordField = (JPasswordField) myComponent;
                values+="MD5(\""+jPasswordField.getText()+"\"),";                
            }
            else
            if (myComponent instanceof JComboBox) {
                JComboBox jComboBox = (JComboBox) myComponent;
                values+=""+jComboBox.getSelectedItem()+",";                
            }
            else
            if (myComponent instanceof JCheckBox) {
                JCheckBox jCheckBox = (JCheckBox) myComponent;
                int val = (jCheckBox.isSelected())?1:0;
                values+=""+val+",";                
            }
            
            
        }
            // end of processing
            values+=valuesClosure;
            values=values.replace(",)", ")");
            fields+=fieldsClosure;
            fields=fields.replace(",)", ")");
            
            System.out.println(query+fields+values);
        
    }

}
