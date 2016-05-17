/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lremapasgraph.managedbeans;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import it.cnr.ilc.lremapasgraph.db.DbConnect;
import it.cnr.ilc.lremapasgraph.db.Vars;
import it.cnr.ilc.lremapasgraph.io.LreMapAsGraphFileWriter;
import it.cnr.ilc.lremapasgraph.io.LreMapAsGraphJsonizer;
import it.cnr.ilc.lremapasgraph.io.LreMapAsGraphStructureManager;
import it.cnr.ilc.lremapasgraph.services.LremapasgraphService;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.json.JsonObject;

/**
 *
 * @author Riccardo Del Gratta &lt;riccardo.delgratta@ilc.cnr.it&gt;
 */
@ManagedBean(name = "lremapasgraphManagedBeanView")
@ViewScoped
public class LremapasgraphManagedBeanView {

    // a list of properties both as string and as List<String>
    private List<String> names = new ArrayList<String>();
    private List<String> types = new ArrayList<String>();
    private List<String> families = new ArrayList<String>();
    private List<String> authors = new ArrayList<String>();
    private List<String> years = new ArrayList<String>();
    private List<String> resourcenames = new ArrayList<String>();
    private List<String> confs = new ArrayList<String>();
    private List<String> confs_years = new ArrayList<String>();

    //files
    private List<String> file_distinct_authors_arg1 = new ArrayList<String>();
    private List<String> file_distinct_affiliations_arg2 = new ArrayList<String>();
    private List<String> file_size_affiliations_arg3 = new ArrayList<String>();
    private List<String> file_distinct_resources_arg4 = new ArrayList<String>();
    private List<String> file_distinct_a2avr_arg5 = new ArrayList<String>();
    private List<String> file_shared_resources_arg6 = new ArrayList<String>();

    private List<String> file_distinct_r2rva_arg1 = new ArrayList<String>();

    private String name;
    private String type;
    private String family;
    private String author;
    private String year = "";
    private String conf = "";
    private String conf_year = "";
    private String vis = "a2avr";
    private String theLog = "";
    private String theLog4File = "";
    private String theLog4Structure = "";

    // connection
    Connection conn;
    DbConnect dbconnect = new DbConnect();

    @ManagedProperty("#{lremapasgraphService}")
    private LremapasgraphService service;

    @PostConstruct
    public void init() {
        if (conn == null) {
            conn = dbconnect.db_connect();
        }
        service.setConn(conn);
        setAuthors(service.getAuthors());
        setFamilies(service.getFamilies());
        setTypes(service.getTypes());
        setConfs_years(service.getConfs_years());
        setResourcenames(service.getResourcenames());

    }

    public void filterAndSearch() {
        LreMapAsGraphStructureManager manager = new LreMapAsGraphStructureManager();
        LreMapAsGraphJsonizer json = new LreMapAsGraphJsonizer();
        boolean retfile = false;
        String ret = "";
        JsonObject obj = null;
        // clear log
        theLog = "";
        theLog4File = "";
        theLog4Structure = "";

        // set year and conf
        //String year = "", conf = "";
        String confYear = getConf_year();

        confYear = confYear.replace(" (", "-").replace(")", "");
        //
        if (!"".equals(confYear)) {
            String[] temp = confYear.split("-");
            if (temp.length > 0) {
                conf = temp[0].trim();
                year = temp[1].trim();
            }
            //file_distinct_authors_arg1 = service.getFile_arg1(year);
        } else {
            year = "";
            conf = "";
        }

        setYear(year);
        setConf(conf);
        System.err.println("confYear " + confYear);
        if ("a2avr".equals(vis)) {

//            file_distinct_authors_arg1 = getFile_distinct_authors_arg1();
//            theLog = getTheLog() + "\nfilterAndSearch: getFile_distinct_authors_arg1() with year -" + getYear() + "- (" + file_distinct_authors_arg1.size() + ")";
//
//            file_distinct_affiliations_arg2 = getFile_distinct_affiliations_arg2();
//            theLog = getTheLog() + "\nfilterAndSearch: getFile_distinct_affiliations_arg2() with year -" + getYear() + "- (" + file_distinct_affiliations_arg2.size() + ")";
//
//            file_size_affiliations_arg3 = getFile_size_affiliations_arg3();
//            theLog = getTheLog() + "\nfilterAndSearch: getFile_size_affiliations_arg3() with year -" + getYear() + "- (" + file_size_affiliations_arg3.size() + ")";
            file_distinct_resources_arg4 = getFile_distinct_resources_arg4();
            theLog = getTheLog() + "\nfilterAndSearch: getFile_distinct_resources_arg4() with year -" + getYear() + "- (" + file_distinct_resources_arg4.size() + ")";

            file_distinct_a2avr_arg5 = getFile_distinct_a2avr_arg5();
            theLog = getTheLog() + "\nfilterAndSearch: getFile_distinct_a2avr_arg5() with year -" + getYear() + "- (" + file_distinct_a2avr_arg5.size() + ")";

            file_shared_resources_arg6 = getFile_shared_resources_arg6();
            theLog = getTheLog() + "\nfilterAndSearch: getFile_shared_resources_arg6() with year -" + getYear() + "- (" + file_shared_resources_arg6.size() + ")";

            //theLog = getTheLog() + "\n--\n";
            //setTheLog(theLog);
//            try {
//                retfile = CreateAndWriteFile(Vars.__FILE_NAME_ARG1__, file_distinct_authors_arg1);
//                theLog4File = getTheLog4File() + "\nfilterAndSearch: CreateAndWriteFile -" + Vars.__FILE_NAME_ARG1__ + "- " + retfile;
//
//            } catch (IOException ioe) {
//                theLog4File = getTheLog4File() + "\n*****FATAL**** filterAndSearch: CreateAndWriteFile -" + Vars.__FILE_NAME_ARG1__ + " -" + ioe.getMessage() + "- " + retfile;
//
//            }
//            try {
//                retfile = CreateAndWriteFile(Vars.__FILE_NAME_ARG2__, file_distinct_affiliations_arg2);
//                theLog4File = getTheLog4File() + "\nfilterAndSearch: CreateAndWriteFile -" + Vars.__FILE_NAME_ARG2__ + "- " + retfile;
//
//            } catch (IOException ioe) {
//                theLog4File = getTheLog4File() + "\n*****FATAL**** filterAndSearch: CreateAndWriteFile -" + Vars.__FILE_NAME_ARG2__ + " -" + ioe.getMessage() + "- " + retfile;
//
//            }
//            try {
//                retfile = CreateAndWriteFile(Vars.__FILE_NAME_ARG3__, file_size_affiliations_arg3);
//                theLog4File = getTheLog4File() + "\nfilterAndSearch: CreateAndWriteFile -" + Vars.__FILE_NAME_ARG3__ + "- " + retfile;
//
//            } catch (IOException ioe) {
//                theLog4File = getTheLog4File() + "\n*****FATAL**** filterAndSearch: CreateAndWriteFile -" + Vars.__FILE_NAME_ARG3__ + " -" + ioe.getMessage() + "- " + retfile;
//
//            }
//            try {
//                retfile = CreateAndWriteFile(Vars.__FILE_NAME_ARG4__, file_distinct_resources_arg4);
//                theLog4File = getTheLog4File() + "\nfilterAndSearch: CreateAndWriteFile -" + Vars.__FILE_NAME_ARG4__ + "- " + retfile;
//
//            } catch (IOException ioe) {
//                theLog4File = getTheLog4File() + "\n*****FATAL**** filterAndSearch: CreateAndWriteFile -" + Vars.__FILE_NAME_ARG4__ + " -" + ioe.getMessage() + "- " + retfile;
//
//            }
            try {
                retfile = CreateAndWriteFile(Vars.__FILE_NAME_ARG5__, file_distinct_a2avr_arg5);
                theLog4File = getTheLog4File() + "\nfilterAndSearch: CreateAndWriteFile -" + Vars.__FILE_NAME_ARG5__ + "- " + retfile;

            } catch (IOException ioe) {
                theLog4File = getTheLog4File() + "\n*****FATAL**** filterAndSearch: CreateAndWriteFile -" + Vars.__FILE_NAME_ARG5__ + " -" + ioe.getMessage() + "- " + retfile;

            }

            try {
                retfile = CreateAndWriteFile(Vars.__FILE_NAME_ARG6__, file_shared_resources_arg6);
                theLog4File = getTheLog4File() + "\nfilterAndSearch: CreateAndWriteFile -" + Vars.__FILE_NAME_ARG6__ + "- " + retfile;

            } catch (IOException ioe) {
                theLog4File = getTheLog4File() + "\n*****FATAL**** filterAndSearch: CreateAndWriteFile -" + Vars.__FILE_NAME_ARG6__ + " -" + ioe.getMessage() + "- " + retfile;

            }

//            ret = manager.readIstiFileAndPrepareStructure(file_distinct_affiliations_arg2);
//            theLog4Structure = getTheLog4Structure() + "\nfilterAndSearch: readIstiFileAndPrepareStructure() on file " + Vars.__FILE_NAME_ARG2__ + " -" + ret + "- ";
            ret = manager.readResourceAndARFilesAndPrepareStructure(file_distinct_resources_arg4, file_distinct_a2avr_arg5, file_shared_resources_arg6);
            theLog4Structure = getTheLog4Structure() + "\nfilterAndSearch: readResourceAndARFilesAndPrepareStructure() on files " + Vars.__FILE_NAME_ARG4__ + " and " + Vars.__FILE_NAME_ARG5__ + " -" + ret + "- ";

            //json.setManager(manager);
            obj = json.readResourceAndARFilesAndPrepareJsonStructure(manager, file_distinct_a2avr_arg5);
            try {
                retfile = LreMapAsGraphFileWriter.CreateAndWriteFileJson(Vars.__FILE_OUT_JSON__, obj);
                theLog4Structure = getTheLog4File() + "\nfilterAndSearch: LreMapAsGraphFileWriter.CreateAndWriteFileJson -" + Vars.__FILE_OUT_JSON__ + "- " + retfile;

            } catch (IOException ioe) {
                theLog4Structure = getTheLog4File() + "\n*****FATAL**** filterAndSearch: LreMapAsGraphFileWriter.CreateAndWriteFileJson -" + Vars.__FILE_OUT_JSON__ + " -" + ioe.getMessage() + "- " + retfile;

            }

        }
        if ("r2rva".equals(vis)) {
            file_distinct_r2rva_arg1 = getFile_distinct_r2rva_arg1();
            theLog = getTheLog() + "\nfilterAndSearch: getFile_distinct_r2rva_arg1() with year -" + getYear() + "- (" + file_distinct_r2rva_arg1.size() + ")";
            try {
                retfile = CreateAndWriteFile(Vars.__FILE_NAME_R2RVA_ARG1__, file_distinct_r2rva_arg1);
                theLog4File = getTheLog4File() + "\nfilterAndSearch: CreateAndWriteFile -" + Vars.__FILE_NAME_R2RVA_ARG1__ + "- " + retfile;

            } catch (IOException ioe) {
                theLog4File = getTheLog4File() + "\n*****FATAL**** filterAndSearch: CreateAndWriteFile -" + Vars.__FILE_NAME_R2RVA_ARG1__ + " -" + ioe.getMessage() + "- " + retfile;

            }

            // structures
            ret = manager.readResourceR2RFileAndPrepareStructure(file_distinct_r2rva_arg1);
            theLog4Structure = getTheLog4Structure() + "\nfilterAndSearch: readResourceR2RFileAndPrepareStructure() on file " + Vars.__FILE_NAME_R2RVA_ARG1__ + " -" + ret + "- ";
            
            //json.setManager(manager);
            obj = json.readResourceR2RFilesAndPrepareJsonStructure(manager, file_distinct_r2rva_arg1);
            try {
                retfile = LreMapAsGraphFileWriter.CreateAndWriteFileJson(Vars.__FILE_OUT_JSON__, obj);
                theLog4Structure = getTheLog4File() + "\nfilterAndSearch: LreMapAsGraphFileWriter.CreateAndWriteFileJson -" + Vars.__FILE_OUT_JSON__ + "- " + retfile;

            } catch (IOException ioe) {
                theLog4Structure = getTheLog4File() + "\n*****FATAL**** filterAndSearch: LreMapAsGraphFileWriter.CreateAndWriteFileJson -" + Vars.__FILE_OUT_JSON__ + " -" + ioe.getMessage() + "- " + retfile;

            }

        }
    }

    public boolean CreateAndWriteFile(String filename, List<String> list) throws IOException {
        boolean completefilename = true;

        completefilename = LreMapAsGraphFileWriter.CreateAndWriteFile(filename, list);

        return completefilename;
    }

    public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            results.add(query + i);
        }

        return results;
    }

    public List<String> completeAuthors(String query) {
        List<String> results = Lists.newArrayList(Collections2.filter(
                getAuthors(), Predicates.containsPattern(query)));

        return results;
    }

    public List<String> completeNames(String query) {
        List<String> results = Lists.newArrayList(Collections2.filter(
                getResourcenames(), Predicates.containsPattern(query)));

        return results;
    }

    /**
     * @return the names
     */
    public List<String> getNames() {
        return names;
    }

    /**
     * @param names the names to set
     */
    public void setNames(List<String> names) {
        this.names = names;
    }

    /**
     * @return the types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * @param types the types to set
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    /**
     * @return the families
     */
    public List<String> getFamilies() {
        return families;
    }

    /**
     * @param families the families to set
     */
    public void setFamilies(List<String> families) {
        this.families = families;
    }

    /**
     * @return the authors
     */
    public List<String> getAuthors() {
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    /**
     * @return the years
     */
    public List<String> getYears() {
        return years;
    }

    /**
     * @param years the years to set
     */
    public void setYears(List<String> years) {
        this.years = years;
    }

    /**
     * @return the confs
     */
    public List<String> getConfs() {
        return confs;
    }

    /**
     * @param confs the confs to set
     */
    public void setConfs(List<String> confs) {
        this.confs = confs;
    }

    /**
     * @return the confs_years
     */
    public List<String> getConfs_years() {
        return confs_years;
    }

    /**
     * @param confs_years the confs_years to set
     */
    public void setConfs_years(List<String> confs_years) {
        this.confs_years = confs_years;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the family
     */
    public String getFamily() {
        return family;
    }

    /**
     * @param family the family to set
     */
    public void setFamily(String family) {
        this.family = family;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the conf
     */
    public String getConf() {
        return conf;
    }

    /**
     * @param conf the conf to set
     */
    public void setConf(String conf) {
        this.conf = conf;
    }

    /**
     * @return the conf_year
     */
    public String getConf_year() {
        return conf_year;
    }

    /**
     * @param conf_year the conf_year to set
     */
    public void setConf_year(String conf_year) {
        this.conf_year = conf_year;
    }

    /**
     * @param service the service to set
     */
    public void setService(LremapasgraphService service) {
        this.service = service;
    }

    /**
     * @return the vis
     */
    public String getVis() {
        return vis;
    }

    /**
     * @param vis the vis to set
     */
    public void setVis(String vis) {
        this.vis = vis;
    }

    /**
     * @return the file_arg1
     */
    public List<String> getFile_distinct_authors_arg1() {
        String year = "", conf = "";
        String confYear = getConf_year();

        confYear = confYear.replace(" (", "-").replace(")", "");
        //
        if (!"".equals(confYear)) {
            String[] temp = confYear.split("-");
            if (temp.length > 0) {
                conf = temp[0].trim();
                year = temp[1].trim();
            }
            //file_distinct_authors_arg1 = service.getFile_arg1(year);
        }
        file_distinct_authors_arg1 = service.getFile_arg1(year, author, type, family, name);

        setYear(year);
        setConf(conf);

        return file_distinct_authors_arg1;
    }

    /**
     * @param file_distinct_authors_arg1 the file_arg1 to set
     */
    public void setFile_distinct_authors_arg1(List<String> file_distinct_authors_arg1) {
        this.file_distinct_authors_arg1 = file_distinct_authors_arg1;
    }

    /**
     * @return the theLog
     */
    public String getTheLog() {

        return theLog;
    }

    /**
     * @param theLog the theLog to set
     */
    public void setTheLog(String theLog) {

        String oldLog = getTheLog();
        theLog = oldLog + "\n" + theLog;
        this.theLog = theLog;
        System.err.println("theLog " + theLog);

    }

    /**
     * @return the file_distinct_affiliations_arg2
     */
    public List<String> getFile_distinct_affiliations_arg2() {
        String year = "", conf = "";
        year = getYear();
        conf = getConf();
        file_distinct_affiliations_arg2 = service.getFile_arg2(year);

        return file_distinct_affiliations_arg2;
    }

    /**
     * @param file_distinct_affiliations_arg2 the
     * file_distinct_affiliations_arg2 to set
     */
    public void setFile_distinct_affiliations_arg2(List<String> file_distinct_affiliations_arg2) {
        this.file_distinct_affiliations_arg2 = file_distinct_affiliations_arg2;
    }

    /**
     * @return the file_size_affiliations_arg3
     */
    public List<String> getFile_size_affiliations_arg3() {
        String year = "", conf = "";
        year = getYear();
        conf = getConf();
        file_size_affiliations_arg3 = service.getFile_arg3(year, author, type, family, name);

        return file_size_affiliations_arg3;
    }

    /**
     * @param file_size_affiliations_arg3 the file_size_affiliations_arg3 to set
     */
    public void setFile_size_affiliations_arg3(List<String> file_size_affiliations_arg3) {
        this.file_size_affiliations_arg3 = file_size_affiliations_arg3;
    }

    /**
     * @return the file_distinct_resources_arg4
     */
    public List<String> getFile_distinct_resources_arg4() {
        //String year = "", conf = "";
        //year = getYear();
        conf = getConf();
        file_distinct_resources_arg4 = service.getFile_arg4(year, author, type, family, name);

        return file_distinct_resources_arg4;
    }

    /**
     * @param file_distinct_resources_arg4 the file_distinct_resources_arg4 to
     * set
     */
    public void setFile_distinct_resources_arg4(List<String> file_distinct_resources_arg4) {
        this.file_distinct_resources_arg4 = file_distinct_resources_arg4;
    }

    /**
     * @return the file_distinct_resources_arg5
     */
    public List<String> getFile_distinct_a2avr_arg5() {
        String year = "", conf = "";
        year = getYear();
        conf = getConf();
        file_distinct_a2avr_arg5 = service.getFile_arg5(year, author, type, family, name);
        return file_distinct_a2avr_arg5;
    }

    /**
     * @param file_distinct_a2avr_arg5 the file_distinct_resources_arg5 to set
     */
    public void setFile_distinct_a2avr_arg5(List<String> file_distinct_a2avr_arg5) {
        this.file_distinct_a2avr_arg5 = file_distinct_a2avr_arg5;
    }

    /**
     * @return the file_shared_resources_arg6
     */
    public List<String> getFile_shared_resources_arg6() {
        String year = "", conf = "";
        year = getYear();
        conf = getConf();
        file_shared_resources_arg6 = service.getFile_arg6(year, author, type, family, name);
        return file_shared_resources_arg6;
    }

    /**
     * @param file_shared_resources_arg6 the file_shared_resources_arg6 to set
     */
    public void setFile_shared_resources_arg6(List<String> file_shared_resources_arg6) {
        this.file_shared_resources_arg6 = file_shared_resources_arg6;
    }

    /**
     * @return the resourcenames
     */
    public List<String> getResourcenames() {
        return resourcenames;
    }

    /**
     * @param resourcenames the resourcenames to set
     */
    public void setResourcenames(List<String> resourcenames) {
        this.resourcenames = resourcenames;
    }

    /**
     * @return the theLog4File
     */
    public String getTheLog4File() {
        return theLog4File;
    }

    /**
     * @param theLog4File the theLog4File to set
     */
    public void setTheLog4File(String theLog4File) {
        this.theLog4File = theLog4File;
    }

    /**
     * @return the theLog4Structure
     */
    public String getTheLog4Structure() {
        return theLog4Structure;
    }

    /**
     * @param theLog4Structure the theLog4Structure to set
     */
    public void setTheLog4Structure(String theLog4Structure) {
        this.theLog4Structure = theLog4Structure;
    }

    /**
     * @return the file_distinct_r2rva_arg1
     */
    public List<String> getFile_distinct_r2rva_arg1() {
        conf = getConf();
        file_distinct_r2rva_arg1 = service.getFile_r2rva_arg1(year, author, type, family, name);

        return file_distinct_r2rva_arg1;
    }

    /**
     * @param file_distinct_r2rva_arg1 the file_distinct_r2rva_arg1 to set
     */
    public void setFile_distinct_r2rva_arg1(List<String> file_distinct_r2rva_arg1) {
        this.file_distinct_r2rva_arg1 = file_distinct_r2rva_arg1;
    }

}
