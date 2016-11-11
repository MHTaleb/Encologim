/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryWrapper;

import java.io.Serializable;
import java.util.Hashtable;

/**
 *
 * @author polina
 */
public class Query_Wrapper implements Serializable{
    /**
     * a query wrapper that will contain all input data of a query
     */
    public static Hashtable<String,String> QueryWrapper; 
    
    
    /**
     * Strings set that enumerate all Key words in an sql input prepared Query
     */
    static private String[] Keys = {"[TABLE]","[VALUES]","[JOIN]","[JOIN1]","[JOIN2]","[JOIN3]","[JOIN4]","[JOIN5]"}; 
    
    /**
     * group of named indexes of keys
     */

    public static enum Keys_Index  {TABLE,VALUES,JOIN,JOIN1,JOIN2,JOIN3,JOIN4,JOIN5};
    
    public static void Format() {
        QueryWrapper = new Hashtable<>();
        for (String Key : Keys) {
            QueryWrapper.put(Key, "");
        }
        
    }
    
    public static void make(int[] indexes , String[] Values){
        Format();
        int i = 0;
        for (int index : indexes) {
            QueryWrapper.put(Keys[index], Values[i++]);
        }
    }
    
    public static void main(String[] args) {
        make(new int[]{Keys_Index.TABLE.ordinal(),Keys_Index.VALUES.ordinal()},new String[]{"GBS","5"});
        System.out.println(QueryWrapper);
    }
}
