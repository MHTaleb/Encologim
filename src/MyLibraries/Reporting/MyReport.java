/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyLibraries.Reporting;

import ExceptionLogging.MyLogger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

/**
 *
 * @author polina
 */
public class MyReport {
    // first step
    public static final void PrepareCompiledDocument(String Document){
         MyLogger.Log_to_local("Compiling Report Design ...");
      try {
          /**
          * Compile the report to a file name same as
          * the JRXML file name
          */
         JasperCompileManager.compileReportToFile(Document);
         MyLogger.Log_to_local("Done compiling!!! ...");
      } catch (JRException e) {
         e.printStackTrace();
      }
    }
}
