/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lremapasgraph.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import it.cnr.ilc.lremapasgraph.db.Vars;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author Riccardo Del Gratta &lt;riccardo.delgratta@ilc.cnr.it&gt;
 */
public class LreMapAsGraphJsonizer {

    private LreMapAsGraphStructureManager manager;

    public JsonObject readResourceAndARFilesAndPrepareJsonStructure(LreMapAsGraphStructureManager manager, List<String> distinct_a2avr_arg5) {
        List<String> check = new ArrayList<String>();
        String already = "";
        int i = 0;
        int j = manager.getAuth().size();
        int bond = 1;
        String result = Vars.__OK__;
        String a0 = "", a1 = "", affi = "", year = "", r = "";
        HashMap<String, Integer> temp_resource2Idx = new HashMap<String, Integer>();
        HashMap<String, Integer> temp_author2Idx = new HashMap<String, Integer>();

        HashMap<String, String> temp_author2normaffiliation = new HashMap<String, String>();
        HashMap<String, String> temp_resource2year = new HashMap<String, String>();

        temp_resource2Idx = manager.getResource2Idx(); //D=self.get_d()
        temp_author2Idx = manager.getAuthor2Idx(); // self.get_dn()
        temp_author2normaffiliation = manager.getAuthor2normaffiliation(); //self.get_auth_2affi()
        temp_resource2year = manager.getResources2year();

        // create links
        JsonObjectBuilder graphBuilder = Json.createObjectBuilder();

        JsonArrayBuilder linksArrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder linkObjBuilder;//= Json.createArrayBuilder();

        JsonArrayBuilder nodesArrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder nodeObjBuilder;//= Json.createArrayBuilder();

        JsonObject obj = null;

        try {
            for (String a : manager.getAuth()) {
                affi = temp_author2normaffiliation.get(a);
                //System.err.println("XXXXXXX "+a+ " - "+affi);
                nodeObjBuilder = Json.createObjectBuilder();

                nodeObjBuilder.add("atom", a);
                nodeObjBuilder.add("size", 10);
                nodeObjBuilder.add("affi", affi);
                nodeObjBuilder.add("group", 0);
                nodeObjBuilder.add("source", temp_author2Idx.get(a));
                nodesArrayBuilder.add(nodeObjBuilder);

            }

            for (String res : manager.getResources()) {
                year = temp_resource2year.get(res);
                System.err.println("XXXXXXX " + res + " - " + year);
                nodeObjBuilder = Json.createObjectBuilder();

                nodeObjBuilder.add("atom", res);
                nodeObjBuilder.add("size", 50);
                nodeObjBuilder.add("affi", year);
                nodeObjBuilder.add("group", 1);
                nodeObjBuilder.add("source", temp_resource2Idx.get(res) + j);
                nodesArrayBuilder.add(nodeObjBuilder);

            }
            for (String s : distinct_a2avr_arg5) {

                a0 = s.split(Vars.__SEPTAB__)[0].trim(); // author_1 
                a0 = cleaner(a0);

                r = s.split(Vars.__SEPTAB__)[2].trim(); // affi_1
                r = cleaner(r);

                a1 = s.split(Vars.__SEPTAB__)[3].trim(); // author_1 
                a1 = cleaner(a1);

                linkObjBuilder = Json.createObjectBuilder();

                already = temp_author2Idx.get(a0) + Vars.__SEPHASHTAG__ + (temp_resource2Idx.get(r) + j) + Vars.__SEPHASHTAG__ + 1;
                if (!check.contains(already)) {
                    linkObjBuilder.add("source", temp_author2Idx.get(a0));
                    linkObjBuilder.add("bond", bond);
                    linkObjBuilder.add("target", temp_resource2Idx.get(r) + j);
                    linksArrayBuilder.add(linkObjBuilder);
                    check.add(already);
                } else {
                    //System.err.println("skipped " + already);
                }
            }
            graphBuilder.add("links", linksArrayBuilder);
            graphBuilder.add("nodes", nodesArrayBuilder);
            obj = graphBuilder.build();
            //printJson(obj);
        } catch (Exception e) {
            result = Vars.__KO__;
        }

        return obj;
    }
    
    public JsonObject readResourceR2RFilesAndPrepareJsonStructure(LreMapAsGraphStructureManager manager, List<String> distinct_r2rva_arg1) {
        List<String> check = new ArrayList<String>();
        String already = "";
        int i = manager.getResources().size();
        int j = manager.getAuth().size();
        int bond = 1;
        String result = Vars.__OK__;
        String r0 = "", r1 = "", affi = "", year0 = "", author = "",year1 = "",year="";
        HashMap<String, Integer> temp_resource2Idx = new HashMap<String, Integer>();
        HashMap<String, Integer> temp_author2Idx = new HashMap<String, Integer>();

        HashMap<String, String> temp_author2normaffiliation = new HashMap<String, String>();
        HashMap<String, String> temp_resource2year = new HashMap<String, String>();

        temp_resource2Idx = manager.getResource2Idx(); //D=self.get_d()
        temp_author2Idx = manager.getAuthor2Idx(); // self.get_dn()
        temp_author2normaffiliation = manager.getAuthor2normaffiliation(); //self.get_auth_2affi()
        temp_resource2year = manager.getResources2year();

        // create links
        JsonObjectBuilder graphBuilder = Json.createObjectBuilder();

        JsonArrayBuilder linksArrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder linkObjBuilder;//= Json.createArrayBuilder();

        JsonArrayBuilder nodesArrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder nodeObjBuilder;//= Json.createArrayBuilder();

        JsonObject obj = null;

        try {
            
            
            for (String a : manager.getAuth()) {
                affi = temp_author2normaffiliation.get(a);
                System.err.println("AUTHOR "+a+ " - "+affi+ " "+temp_author2Idx.get(a));
                nodeObjBuilder = Json.createObjectBuilder();

                nodeObjBuilder.add("atom", a);
                nodeObjBuilder.add("size", 10);
                nodeObjBuilder.add("affi", affi);
                nodeObjBuilder.add("group", 0);
                nodeObjBuilder.add("source", temp_author2Idx.get(a));
                nodesArrayBuilder.add(nodeObjBuilder);

            }

            for (String res : manager.getResources()) {
                year = temp_resource2year.get(res);
                System.err.println("XXXXXXX " + res + " - " + year+ " "+(temp_resource2Idx.get(res) + j));
                nodeObjBuilder = Json.createObjectBuilder();

                nodeObjBuilder.add("atom", res);
                nodeObjBuilder.add("size", 50);
                nodeObjBuilder.add("affi", year);
                nodeObjBuilder.add("group", 1);
                nodeObjBuilder.add("source", temp_resource2Idx.get(res) + i);
                nodesArrayBuilder.add(nodeObjBuilder);

            }
            for (String s : distinct_r2rva_arg1) {

                r0 = s.split(Vars.__SEPTAB__)[0].trim(); // r_1 
                r0 = cleaner(r0);
                year0 = s.split(Vars.__SEPTAB__)[1].trim(); // r_1 

                author = s.split(Vars.__SEPTAB__)[2].trim(); // author
                affi = s.split(Vars.__SEPTAB__)[3].trim(); // author

                r1 = s.split(Vars.__SEPTAB__)[4].trim(); // r_2 
                r1 = cleaner(r1);
                
                year1 = s.split(Vars.__SEPTAB__)[5].trim(); // r_1 

                linkObjBuilder = Json.createObjectBuilder();

//                already = (temp_resource2Idx.get(r0)+i-1) + Vars.__SEPHASHTAG__ + (temp_author2Idx.get(author)) + Vars.__SEPHASHTAG__ + 1;
//                if (!check.contains(already)) {
//                    linkObjBuilder.add("source", (temp_resource2Idx.get(r0)+i-1));
//                    linkObjBuilder.add("bond", bond);
//                    linkObjBuilder.add("target", temp_author2Idx.get(author));
//                    linksArrayBuilder.add(linkObjBuilder);
//                    check.add(already);
//                } else {
//                    System.err.println("skipped r0 -" + already);
//                }
//                
//                already = (temp_resource2Idx.get(r1)+i-1) + Vars.__SEPHASHTAG__ + (temp_author2Idx.get(author)) + Vars.__SEPHASHTAG__ + 1;
//                if (!check.contains(already)) {
//                    linkObjBuilder.add("source", (temp_resource2Idx.get(r1)+i-1));
//                    linkObjBuilder.add("bond", bond);
//                    linkObjBuilder.add("target", temp_author2Idx.get(author));
//                    linksArrayBuilder.add(linkObjBuilder);
//                    check.add(already);
//                } else {
//                    System.err.println("skipped r1 -" + already);
//                }
                
                 linkObjBuilder.add("source", (temp_resource2Idx.get(r0)+j));
                    linkObjBuilder.add("bond", bond);
                    linkObjBuilder.add("target", temp_author2Idx.get(author));
                    linksArrayBuilder.add(linkObjBuilder);
                    
                     linkObjBuilder.add("source", (temp_resource2Idx.get(r1)+j));
                    linkObjBuilder.add("bond", bond);
                    linkObjBuilder.add("target", temp_author2Idx.get(author));
                    linksArrayBuilder.add(linkObjBuilder);
            }
            
            
            graphBuilder.add("links", linksArrayBuilder);
            graphBuilder.add("nodes", nodesArrayBuilder);
            obj = graphBuilder.build();
            //printJson(obj);
        } catch (Exception e) {
            result = Vars.__KO__;
        }

        return obj;
    }

    private String cleaner(String str2clean) {
        String ret = "";
        str2clean = str2clean.replace("\\", "");

        str2clean = str2clean.replace(",", "");
        str2clean = str2clean.replace("/", " ");
        str2clean = str2clean.replace(" ", "_");
        ret = str2clean;
        return ret;
    }

    /**
     * @return the manager
     */
    public LreMapAsGraphStructureManager getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(LreMapAsGraphStructureManager manager) {
        this.manager = manager;
    }

}
