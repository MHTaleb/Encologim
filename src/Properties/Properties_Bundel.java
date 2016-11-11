/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Properties;

import java.util.Hashtable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author polina
 */
public class Properties_Bundel {

/**
 * my app strings 
 */

    public final static String AppConfMode = "Properties.AppConfMode";
    
    public final static String Devlopper_Mode = "Devlopper_Mode";
    public final static String DataBase = "DataBase";
    
    public final static String HeadsCaption = "Properties.HeadsCaption";
    // Table Users
    public final static String HeadsCaption_users_ID = "users_ID";
    public final static String HeadsCaption_users_PASSWORD = "users_PASSWORD";
    public final static String HeadsCaption_users_STATE = "users_STATE";
    public final static String HeadsCaption_users_USERNAME = "users_USERNAME";
    public final static String HeadsCaption_users_PROFILE = "users_PROFILE";
    
    // Table profiles
    public final static String HeadsCaption_profiles_ID = "profiles_ID";
    public final static String HeadsCaption_profiles_TITRE = "profiles_TITRE";
    public final static String HeadsCaption_profiles_PROFILE = "profiles_PROFILE";
    
    // Table UsersState
    public final static String HeadsCaption_usersstate_ID = "usersstate_ID";
    public final static String HeadsCaption_usersstate_STATE = "usersstate_STATE";
    public final static String HeadsCaption_usersstate_TITRE = "usersstate_TITRE";
    
    public final static String Strings = "Properties.Strings";
    public final static String Strings_Fill_Username = "Fill username";
    public final static String Strings_Confirm_Fail = "Confirm_Fail";
    public final static String Strings_Register_Success = "Register_Success";
    public final static String Strings_Authentification_Failed="Authentification_Failed";
    public final static String Strings_Inactive_Account="Inactive_Account";

    /**
     * the property containing all my preformated sql queries
     */
    public final static String SQL_QUERIES = "Properties.SQL_QUERIES";
    /**
     * Preformated mysql describe query
     * 
     * @param table specify the object table to describe
     * 
     */
    public final static String SQL_QUERIES__MYSQL_DESCRIBE = "MYSQL_DESCRIBE";
    
    private static ResourceBundle mainBundle;
 
    public static void setPropertiesBundelRessource(String Apath){
        
        mainBundle = ResourceBundle.getBundle(Apath);
    }
    
    
    /**
     * prepare query statement from a prepared stored query format
     * 
     * @param QueryKey key for the desired stored query ex : SQL_QUERIES__MYSQL_DESCRIBE 
     */
    public static String PrepareQuery(final String QueryKey, final Hashtable entries){
        
        String BruteQuery = getString(QueryKey);
        
        
        return "";
    }
    
    
    /**
     * Get translation for given key
     *
     * @param key  translation key
     * @return     translated text
     */
    public static boolean getState(final String key){
     
        
        return getString(key).equals("active") || getString(key).equals("true");
    }
    
    public static String getString(final String key) {
        try {
            System.out.println("properties_bundle ("+key+")");
            return mainBundle.getString(key);
        }
        catch (Exception mre) {
            mre.printStackTrace();
            System.out.println("searched Key error : key = "+key);
        }

        return "Not Found";
    }
    
    public static String getString(final String key,final String Ressource) {
        try {
            Properties_Bundel.setPropertiesBundelRessource(Ressource);
            System.out.println("properties_bundle ("+key+")");
            return mainBundle.getString(key);
        }
        catch (Exception mre) {
            mre.printStackTrace();
            System.out.println("searched Key error : key = "+key);
        }

        return "Not Found";
    }
    

}
