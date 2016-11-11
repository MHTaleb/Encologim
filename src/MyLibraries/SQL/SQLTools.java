/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyLibraries.SQL;

import MyLibraries.JDBCConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
        if (pool != null) {
            return;
        }
        pool = new JDBCConnectionPool(JDBC_DRIVER, DB_URL, USER, PASS);
    }

    
    // void n est pas un mot clé qui site une fonction qui nas pas de retour
    // mais en realité c est un type de retour qui représente un etat vide
    // c pourcela kayen return
    public static void Setup(String DB_URL,String USER,String PASS) {
        // Si la connexion est deja faite alors en termine la methode
        if (pool != null) {
            return;// ici si j ai deja creer un pool alors pas besoin d un autre je termine l execution
        }
        
        pool = new JDBCConnectionPool(JDBC_DRIVER, DB_URL, USER, PASS); // sinon je creer un pool de connexion
    }
    
    
    public static ResultSet ExecuteQuery(String Query) {

        try {

            // je recupere l une de mes connexion disponible
            Connection con = pool.checkOut();

            // depuis cette connexion je prepare mon SQL Statement ( disant que c ets un mechanisme
            // qui est intermédiaire entre moteur mysql et mon appli
            PreparedStatement ps = con.prepareStatement(Query,  ResultSet.TYPE_FORWARD_ONLY,
                                  ResultSet.CONCUR_UPDATABLE);//concur updatable sert a mettre des mise a jour
            // si je modifie le resultat de ma requete depui mon appli il change direct dans la BDD mysql
            
            if (Query.toLowerCase().contains("insert")) { // si la requete insert
                ps.executeUpdate(); // j execute avec update c est un mecanisme de l api jdbc
            } else {
                ResultSet rs = ps.executeQuery(); //sinon c ets un SELECT alors ta3o c est execute
                 pool.checkIn(con); // je me rend compte que je l ai oublié lol lazem nzido hna 
                 //en cas que c est une requete select une fois fini il faut que je rend la connexion prise
                return rs;
            }

            pool.checkIn(con); // rendre la ressource occupé
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
