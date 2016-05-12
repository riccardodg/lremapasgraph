/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lremapasgraph.io;


import it.cnr.ilc.lremapasgraph.db.Vars;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Riccardo Del Gratta &lt;riccardo.delgratta@ilc.cnr.it&gt;
 */
public class LreMapAsGraphFileWriter {

    
    

    private static String CreateFile(String name) {
        String complete;
        Path p1 = Paths.get(Vars.__DIR__+Vars.__SEP__+name);
        complete=p1.toString();
        
        
        return complete;
    }
    
    public static boolean CreateAndWriteFile(String filename, List<String> list) throws IOException {
        String completefilename = "";
        completefilename = CreateFile(filename);
        System.out.println("path " + completefilename);
        boolean ret=true;
        if (!"".equals(completefilename)) {
            Writer output = new BufferedWriter(new FileWriter(completefilename));
            try {
           

                for (String s : list) {

                    //use buffering
                    //FileWriter always assumes default encoding is OK!
                    output.write(s + "\n");

                }
            } catch (Exception e) {
                ret=false;
                
            } finally {
                output.close();
            }
        }

        return ret;
    }

}
