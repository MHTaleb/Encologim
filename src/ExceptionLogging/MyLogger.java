/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExceptionLogging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author polina
 */
public class MyLogger {

    private static final String FileName = "encologim.txt";

    
    public static void Log_to_local(String MSG){
        Log_to_local(new Exception(MSG));
    }
    
    public static void Log_to_local(Exception e) {
        FileWriter fstream = null;
        try {
            File f = new File(FileName);
            if (!f.exists()) {
                f.createNewFile();
            }

            fstream = new FileWriter(FileName,true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(e.toString()+"\n");
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(MyLogger.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fstream.close();
            } catch (IOException ex) {
                Logger.getLogger(MyLogger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
