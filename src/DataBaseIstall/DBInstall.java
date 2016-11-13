/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseIstall;

import ExceptionLogging.MyLogger;
import MyLibraries.SQL.SQLTools;
import java.util.ArrayList;

/**
 *
 * @author polina
 */
public class DBInstall {

    public static final boolean InstallDatabase(){
        try {
            System.out.println("Database installation started .....");
            installUsersTables();
             System.out.println("Database installation finished .");
            return true;
        } catch (Exception e) {
            MyLogger.Log_to_local(e);
             System.out.println("Database installation Failed .");
            return false;
        }
    }
    
    private static final void installUsersTables() {
        
        String Query
                = "CREATE DATABASE IF NOT EXISTS `encologim`;";
        
        String Query2
                = "CREATE TABLE IF NOT EXISTS `profiles` ("
                + "  `ID` int(11) NOT NULL AUTO_INCREMENT,"
                + "  `TITRE` char(50) COLLATE utf8_bin DEFAULT '0',"
                + "  PRIMARY KEY (`ID`)"
                + ") ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;";
        String Query3
                = "DELETE FROM `profiles`;";
        String Query4
                = "INSERT INTO `profiles` (`ID`, `TITRE`) VALUES"
                + "	(0, 'Super Administrateur'),"
                + "	(1, 'Médecin');";
         String Query41
                ="UPDATE `encologim`.`profiles` SET `ID`=0 WHERE  `ID`=3 OR TITRE ='Super Administrateur';";
        String Query5
                = "CREATE TABLE IF NOT EXISTS `users` ("
                + "  `ID` int(11) NOT NULL AUTO_INCREMENT,"
                + "  `STATE` int(1) NOT NULL DEFAULT '0',"
                + "  `USERNAME` char(50) COLLATE utf8_bin DEFAULT NULL,"
                + "  `PASSWORD` char(50) COLLATE utf8_bin DEFAULT NULL,"
                + "  `PROFILE` int(2) DEFAULT NULL,"
                + "  PRIMARY KEY (`ID`)"
                + ") ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;";
        String Query6
                = "DELETE FROM `users`;";
        String Query7
                = "INSERT INTO `users` (`ID`, `STATE`, `USERNAME`, `PASSWORD`, `PROFILE`) VALUES"
                + "	(1, 1, 'talcorp', '25f9e794323b453885f5181f1b624d0b', 0),"
                + "	(2, 0, 'test', '098f6bcd4621d373cade4e832627b4f6', 1),"
                + "	(3, 0, 'taleb', '202cb962ac59075b964b07152d234b70', 1),"
                + "	(4, 0, 'Charef Amine', 'c9ee6a13c915dbf0ec04f32293f026ae', 1);";
        String Query8
                = "CREATE TABLE IF NOT EXISTS `usersstate` ("
                + "  `ID` int(11) NOT NULL AUTO_INCREMENT,"
                + "  `TITRE` char(50) COLLATE utf8_bin DEFAULT NULL,"
                + "  PRIMARY KEY (`ID`)"
                + ") ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;";
        String Query9
                = "DELETE FROM `usersstate`;";
        String Query10
                = "INSERT INTO `usersstate` (`ID`, `TITRE`) VALUES"
                + "	(0, 'inactive'),"
                + "	(1, 'active');";
        String Query101 ="UPDATE `encologim`.`usersstate` SET `ID`=0 WHERE  `ID`=2 OR TITRE='inactive';";
       

        SQLTools.Setup("jdbc:mysql://localhost/mysql","root","");
        
        System.out.println("installing Users Table .");
        SQLTools.ExecuteQuery(Query);
        System.out.println("database creation done .... Encologim");
       
        SQLTools.Setup("jdbc:mysql://localhost/encologim","root","");
        System.out.println("using encologim");
        
        System.out.println("Creating table PROFILES");
        SQLTools.ExecuteQuery(Query2);
        
        System.out.println("formating PROFILES");
        SQLTools.ExecuteQuery(Query3);
        
        System.out.println("Inserting Profiles data");
        SQLTools.ExecuteQuery(Query4);
        SQLTools.ExecuteQuery(Query41);
        
        System.out.println("Creating table users");
        SQLTools.ExecuteQuery(Query5);
        
        System.out.println("Formating users");
        SQLTools.ExecuteQuery(Query6);
        
        System.out.println("Inserting users Data");
        SQLTools.ExecuteQuery(Query7);
        
        System.out.println("Creating table UsersStates");
        SQLTools.ExecuteQuery(Query8);
        
        System.out.println("formating UsersState");
        SQLTools.ExecuteQuery(Query9);
        
        System.out.println("Inserting UsersState data");
        SQLTools.ExecuteQuery(Query10);
        SQLTools.ExecuteQuery(Query101);
        
        
         System.out.println("Users Table Istall success .");
       
    }

}
