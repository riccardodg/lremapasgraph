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
import it.cnr.ilc.lremapasgraph.services.LremapasgraphService;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

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
    private List<String> confs = new ArrayList<String>();
    private List<String> confs_years = new ArrayList<String>();
    
    private String name;
    private String type;
    private String family;
    private String author;
    private String year;
    private String conf;
    private String conf_year;
    
    // connection
    Connection conn;
    DbConnect dbconnect=new DbConnect();
    
    @ManagedProperty("#{lremapasgraphService}")
    private LremapasgraphService service;
    
    @PostConstruct
    public void init() {
        if (conn == null)
            conn=dbconnect.db_connect();
        //setResNorm(service.getListOfNormResources());
        service.setConn(conn);
        setAuthors(service.getAuthors());
        setFamilies(service.getFamilies());
        setTypes(service.getTypes());
        setConfs_years(service.getConfs_years());
        
        //System.out.println("A "+authors);
        

    }
    
    public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }
         
        return results;
    }
    
    public List<String> completeAuthors(String query) {
        List<String> results = Lists.newArrayList(Collections2.filter(
                getAuthors(), Predicates.containsPattern(query)));
        
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

}
