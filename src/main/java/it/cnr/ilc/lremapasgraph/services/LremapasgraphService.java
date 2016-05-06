/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lremapasgraph.services;

import it.cnr.ilc.lremapasgraph.db.DbConnect;
import it.cnr.ilc.lremapasgraph.db.Select2Prepare;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Riccardo Del Gratta &lt;riccardo.delgratta@ilc.cnr.it&gt;
 */
@ManagedBean(name = "lremapasgraphService")
@ApplicationScoped
public class LremapasgraphService implements Serializable{

    private List<String> authors = new ArrayList<String>();
    private List<String> families = new ArrayList<String>();
    private List<String> types = new ArrayList<String>();
    private List<String> confs_years = new ArrayList<String>();
    private Connection conn;

    /**
     * @return the authors
     */
    public List<String> getAuthors() {
        ResultSet res;
        if (authors.isEmpty()) {
            // fill the authors
            try {
                res = DbConnect.execStmAndGetResultSet(getConn(), Select2Prepare.__SELECTLISTOFAUTHORS__);
                authors = loopOverRS(res);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(authors);
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * @param conn the conn to set
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    private List<String> loopOverRS(ResultSet rs) throws SQLException {
        List<String> list = new ArrayList<String>();
        String field = "";
       
        int l = 0;
        while (rs.next()) {
            field = rs.getString(1);

            list.add(field);
            l++;
        }

        return list;
    }

    /**
     * @return the families
     */
    public List<String> getFamilies() {
        ResultSet res;
        if (families.isEmpty()) {
            // fill the authors
            try {
                res = DbConnect.execStmAndGetResultSet(getConn(), Select2Prepare.__SELECTLISTOFFAMILIES__);
                families = loopOverRS(res);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return families;
    }

    /**
     * @param families the families to set
     */
    public void setFamilies(List<String> families) {
        this.families = families;
    }

    /**
     * @return the types
     */
    public List<String> getTypes() {
        ResultSet res;
        if (types.isEmpty()) {
            // fill the authors
            try {
                res = DbConnect.execStmAndGetResultSet(getConn(), Select2Prepare.__SELECTLISTOFTYPES__);
                types = loopOverRS(res);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return types;
    }

    /**
     * @param types the types to set
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    /**
     * @return the confs_years
     */
    public List<String> getConfs_years() {
        ResultSet res;
        if (confs_years.isEmpty()) {
            // fill the authors
            try {
                res = DbConnect.execStmAndGetResultSet(getConn(), Select2Prepare.__SELECTLISTOFCONFS_YEARS__);
                confs_years = loopOverRS(res);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return confs_years;
    }

    /**
     * @param confs_years the confs_years to set
     */
    public void setConfs_years(List<String> confs_years) {
        this.confs_years = confs_years;
    }

}
