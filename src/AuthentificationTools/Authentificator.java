/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthentificationTools;

import ExceptionLogging.MyLogger;
import MyLibraries.SQL.SQLTools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author polina
 */
public class Authentificator {

    private static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String DoAuthentificate(String User, String Pass) {

        try {
            SQLTools.Setup();

            String Query = "SELECT * FROM users s WHERE s.USERNAME ='" + User + "' AND s.PASSWORD = '" + MD5(Pass) + "'  ;";
            System.out.println(Query);
            ResultSet s = SQLTools.ExecuteQuery(Query);

            if (s.next()) {
                if ((int)s.getObject("STATE")==1){
                return "true";    
                }else{
                    return "inactive";
                }
                
            }

        } catch (SQLException ex) {
            MyLogger.Log_to_local(ex);
        }
        return "false";
    }

    public static boolean DoRegister(String User, String Pass) {
        String Query = "INSERT INTO users(USERNAME, PASSWORD,PROFILE,STATE) VALUES("
                + "\"" + User
                + "\", MD5(\"" + Pass
                + "\"),1,0);";
        SQLTools.ExecuteQuery(Query);
        return true;
    }

}
