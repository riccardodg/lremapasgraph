/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lremapasgraph.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import it.cnr.ilc.lremapasgraph.db.Vars;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.json.JsonObject;

/**
 *
 * @author Riccardo Del Gratta &lt;riccardo.delgratta@ilc.cnr.it&gt;
 */
public class LreMapAsGraphFileWriter {

    private static String CreateFile(String name, String dir) {
        String complete;
        Path p1 = Paths.get(dir + Vars.__SEP__ + name);
        complete = p1.toString();

        return complete;
    }

    public static boolean CreateAndWriteFile(String filename, List<String> list) throws IOException {
        String completefilename = "";
        completefilename = CreateFile(filename, Vars.__DIR__);
        //System.out.println("path " + completefilename);
        boolean ret = true;
        if (!"".equals(completefilename)) {
            Writer output = new BufferedWriter(new FileWriter(completefilename));
            try {

                for (String s : list) {

                    //use buffering
                    //FileWriter always assumes default encoding is OK!
                    output.write(s + "\n");

                }
            } catch (Exception e) {
                ret = false;

            } finally {
                output.close();
            }
        }

        return ret;
    }

    public static boolean CreateAndWriteFileJson(String filename, JsonObject obj) throws IOException {

        String str = obj.toString();
        String indented = "";
        boolean ret = true;
        ObjectMapper mapper = new ObjectMapper();
        //boolean ret=true;
        String completefilename = "";
        completefilename = CreateFile(filename, Vars.__OUTDIR__);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        if (!"".equals(completefilename)) {
            Writer output = new BufferedWriter(new FileWriter(completefilename));
            try {

                Object json = mapper.readValue(str, Object.class);

                indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
                output.write(indented);
            } catch (Exception e) {
                ret = false;

            } finally {
                output.close();
            }
        }

        return ret;
    }

}
