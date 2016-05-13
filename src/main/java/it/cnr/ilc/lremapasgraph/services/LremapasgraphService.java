/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lremapasgraph.services;

import it.cnr.ilc.lremapasgraph.db.DbConnect;
import it.cnr.ilc.lremapasgraph.db.Select2Exec;
import it.cnr.ilc.lremapasgraph.db.Select2Prepare;
import it.cnr.ilc.lremapasgraph.db.Vars;
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
public class LremapasgraphService implements Serializable {

    private List<String> authors = new ArrayList<String>();
    private List<String> families = new ArrayList<String>();
    private List<String> types = new ArrayList<String>();
    private List<String> confs_years = new ArrayList<String>();
    private List<String> resourcenames = new ArrayList<String>();

    private List<String> file_arg1 = new ArrayList<String>();
    private List<String> file_arg2 = new ArrayList<String>();
    private List<String> file_arg3 = new ArrayList<String>();
    private List<String> file_arg4 = new ArrayList<String>();
    private List<String> file_arg5 = new ArrayList<String>();
    private List<String> file_arg6 = new ArrayList<String>();

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
                authors = loopOverRSPrepareFilter(res);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

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

    /**
     * @return the families
     */
    public List<String> getFamilies() {
        ResultSet res;
        if (families.isEmpty()) {
            // fill the authors
            try {
                res = DbConnect.execStmAndGetResultSet(getConn(), Select2Prepare.__SELECTLISTOFFAMILIES__);
                families = loopOverRSPrepareFilter(res);
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
                types = loopOverRSPrepareFilter(res);
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
                confs_years = loopOverRSPrepareFilter(res);
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

    /**
     * @return the resourcenames
     */
    public List<String> getResourcenames() {
        ResultSet res;
        System.err.println("XXX " + Select2Prepare.__SELECTLISTOFRESOURCES__);
        if (resourcenames.isEmpty()) {
            // fill the authors
            try {
                res = DbConnect.execStmAndGetResultSet(getConn(), Select2Prepare.__SELECTLISTOFRESOURCES__);
                resourcenames = loopOverRSPrepareFilter(res);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resourcenames;
    }

    /**
     * @param resourcenames the resourcenames to set
     */
    public void setResourcenames(List<String> resourcenames) {
        this.resourcenames = resourcenames;
    }

    /**
     * @return the file_arg1
     */
    /**
     * Return arg1 dataset
     *
     * @param year the year to filter
     * @param author the author to filter
     * @param type the resource type
     * @param family the family to filter
     * @param name the resname to filter
     * @return the list of data
     */
    public List<String> getFile_arg1(String year, String author, String type, String family, String name) {
        ResultSet res;
        String execp1 = "";
        String execp2 = "";
        String exec = "";
        getFile_arg1().clear();
        if ("".equals(family)) {
            execp1 = Select2Exec.__SELECTARG1_DISTAUTH_PART1_BASE__;
            execp2 = Select2Exec.__SELECTARG1_DISTAUTH_PART2_BASE__;
        } else {
            execp1 = Select2Exec.__SELECTARG1_DISTAUTH_PART1_FAM__;
            execp1 = execp1 + " AND C.family=\"%s\"";
            execp1 = String.format(execp1, family);

            execp2 = Select2Exec.__SELECTARG1_DISTAUTH_PART2_FAM__;
            execp2 = execp2 + " AND C.family=\"%s\"";
            execp2 = String.format(execp2, family);
        }
        if (!"".equals(author)) {
            execp1 = execp1 + " AND A.author_1=\"%s\"";
            execp1 = String.format(execp1, author);
            execp2 = execp2 + " AND A.author_2=\"%s\"";
            execp2 = String.format(execp2, author);
        }
        if (!"".equals(year)) {
            execp1 = execp1 + " AND A.YEAR_1=\"%s\"";
            execp1 = String.format(execp1, year);

            execp2 = execp2 + " AND A.YEAR_2=\"%s\"";
            execp2 = String.format(execp2, year);
        }

        if (!"".equals(type)) {
            execp1 = execp1 + " AND A.resourcetype=\"%s\"";
            execp1 = String.format(execp1, type);

            execp2 = execp2 + " AND A.resourcetype=\"%s\"";
            execp2 = String.format(execp2, type);
        }

        if (!"".equals(name)) {
            execp1 = execp1 + " AND A.resourcename=\"%s\"";
            execp1 = String.format(execp1, name);

            execp2 = execp2 + " AND A.resourcename=\"%s\"";
            execp2 = String.format(execp2, name);
        }
        exec = execp1 + Vars.__UNION__ + execp2 + Vars.__ORDERBYAFFI__;
        System.err.println("ARG1 - " + exec);
        try {
            res = DbConnect.execStmAndGetResultSet(getConn(), exec);
            setFile_arg1(loopOverRSArg1(res));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getFile_arg1();
    }

    /**
     * Return arg2 dataset
     *
     * @param year the year to filter
     * @return the list of data
     */
    public List<String> getFile_arg2(String year) {
        ResultSet res;
        getFile_arg2().clear();
        String exec = Select2Exec.__SELECTARG2_DISTAFFI__;
        if (!"".equals(year)) {
            exec = exec + " WHERE YEAR=\"%s\"";
            exec = String.format(exec, year);
        }
        System.err.println("ARG2 - " + exec);
        try {
            res = DbConnect.execStmAndGetResultSet(getConn(), exec);
            setFile_arg2(loopOverRSArg2(res));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getFile_arg2();
    }

    /**
     * Return arg3 dataset
     *
     * @param year the year to filter
     * @param author the author to filter
     * @param type the resource type
     * @param family the family to filter
     * @param name the resname to filter
     * @return the list of data
     */
    public List<String> getFile_arg3(String year, String author, String type, String family, String name) {
        ResultSet res;
        String execp1 = "";
        String execp2 = "";
        String exec = "";
        getFile_arg3().clear();
        if ("".equals(family)) {
            execp1 = Select2Exec.__SELECTARG3_SIZE_AFFI_PART1_BASE__;
            execp2 = Select2Exec.__SELECTARG3_SIZE_AFFI_PART2_BASE__;
        } else {
            execp1 = Select2Exec.__SELECTARG3_SIZE_AFFI_PART1_FAM__;
            execp1 = execp1 + " AND C.family=\"%s\"";
            execp1 = String.format(execp1, family);

            execp2 = Select2Exec.__SELECTARG3_SIZE_AFFI_PART2_FAM__;
            execp2 = execp2 + " AND C.family=\"%s\"";
            execp2 = String.format(execp2, family);
        }
        if (!"".equals(author)) {
            execp1 = execp1 + " AND A.author_1=\"%s\"";
            execp1 = String.format(execp1, author);
            execp2 = execp2 + " AND A.author_2=\"%s\"";
            execp2 = String.format(execp2, author);
        }
        if (!"".equals(year)) {
            execp1 = execp1 + " AND A.YEAR_1=\"%s\"";
            execp1 = String.format(execp1, year);

            execp2 = execp2 + " AND A.YEAR_2=\"%s\"";
            execp2 = String.format(execp2, year);
        }

        if (!"".equals(type)) {
            execp1 = execp1 + " AND A.resourcetype=\"%s\"";
            execp1 = String.format(execp1, type);

            execp2 = execp2 + " AND A.resourcetype=\"%s\"";
            execp2 = String.format(execp2, type);
        }
        if (!"".equals(name)) {
            execp1 = execp1 + " AND A.resourcename=\"%s\"";
            execp1 = String.format(execp1, name);

            execp2 = execp2 + " AND A.resourcename=\"%s\"";
            execp2 = String.format(execp2, name);
        }
        exec = execp1 + Vars.__UNION__ + execp2 + Vars.__ORDERBYCOUNTARG3__;
        System.err.println("ARG3 - " + exec);
        try {
            res = DbConnect.execStmAndGetResultSet(getConn(), exec);
            setFile_arg3(loopOverRSArg3(res));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getFile_arg3();
    }

    /**
     * Return arg4 dataset
     *
     * @param year the year to filter
     * @param author the author to filter
     * @param type the resource type
     * @param family the family to filter
     * @param name the resname to filter
     * @return the list of data
     */
    public List<String> getFile_arg4(String year, String author, String type, String family, String name) {
        ResultSet res;
        String execp1 = "";
        String execp2 = "";
        String exec = "";
        getFile_arg4().clear();
        if ("".equals(family)) {
            execp1 = Select2Exec.__SELECTARG4_DISTINCTRES_BASE_P1__;
            execp2 = Select2Exec.__SELECTARG4_DISTINCTRES_BASE_P2__;
        } else {
            execp1 = Select2Exec.__SELECTARG4_DISTINCTRES_FAM_P1__;
            execp1 = execp1 + " AND C.family=\"%s\"";
            execp1 = String.format(execp1, family);

            execp2 = Select2Exec.__SELECTARG4_DISTINCTRES_FAM_P2__;
            execp2 = execp2 + " AND C.family=\"%s\"";
            execp2 = String.format(execp2, family);
        }
        if (!"".equals(author)) {
            execp1 = execp1 + " AND A.author_1=\"%s\"";
            execp1 = String.format(execp1, author);
            execp2 = execp2 + " AND A.author_2=\"%s\"";
            execp2 = String.format(execp2, author);
        }
        if (!"".equals(year)) {
            execp1 = execp1 + " AND A.YEAR_1=\"%s\"";
            execp1 = String.format(execp1, year);

            execp2 = execp2 + " AND A.YEAR_2=\"%s\"";
            execp2 = String.format(execp2, year);
        }

        if (!"".equals(type)) {
            execp1 = execp1 + " AND A.resourcetype=\"%s\"";
            execp1 = String.format(execp1, type);

            execp2 = execp2 + " AND A.resourcetype=\"%s\"";
            execp2 = String.format(execp2, type);
        }
        if (!"".equals(name)) {
            execp1 = execp1 + " AND A.resourcename=\"%s\"";
            execp1 = String.format(execp1, name);

            execp2 = execp2 + " AND A.resourcename=\"%s\"";
            execp2 = String.format(execp2, name);
        }
        exec = execp1 + Vars.__UNION__ + execp2 + Vars.__ORDERBYARG4__;

        System.err.println("ARG4 -" + exec);
        try {
            res = DbConnect.execStmAndGetResultSet(getConn(), exec);
            setFile_arg4(loopOverRSArg4(res));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getFile_arg4();
    }

    /**
     * Return arg5 dataset
     *
     * @param year the year to filter
     * @param author the author to filter
     * @param type the resource type
     * @param family the family to filter
     * @param name the resname to filter
     * @return the list of data
     */
    public List<String> getFile_arg5(String year, String author, String type, String family, String name) {
        ResultSet res;
        String execp1 = "";
        String execp2 = "";
        String exec = "";
        getFile_arg5().clear();
        if ("".equals(family)) {
            execp1 = Select2Exec.__SELECTARG5_A2AVR_BASE_PART1__;
            execp2 = Select2Exec.__SELECTARG5_A2AVR_BASE_PART2__;
        } else {
            execp1 = Select2Exec.__SELECTARG5_A2AVR_FAM_PART1__;
            execp1 = execp1 + " AND C.family=\"%s\"";
            execp1 = String.format(execp1, family);

            execp2 = Select2Exec.__SELECTARG5_A2AVR_FAM_PART2__;
            execp2 = execp2 + " AND C.family=\"%s\"";
            execp2 = String.format(execp2, family);
        }
        if (!"".equals(author)) {
            execp1 = execp1 + " AND A.author_1=\"%s\"";
            execp1 = String.format(execp1, author);
            execp2 = execp2 + " AND A.author_2=\"%s\"";
            execp2 = String.format(execp2, author);
        }
        if (!"".equals(year)) {
            execp1 = execp1 + " AND A.YEAR_1=\"%s\"";
            execp1 = String.format(execp1, year);

            execp2 = execp2 + " AND A.YEAR_2=\"%s\"";
            execp2 = String.format(execp2, year);
        }

        if (!"".equals(type)) {
            execp1 = execp1 + " AND A.resourcetype=\"%s\"";
            execp1 = String.format(execp1, type);

            execp2 = execp2 + " AND A.resourcetype=\"%s\"";
            execp2 = String.format(execp2, type);
        }

        if (!"".equals(name)) {
            execp1 = execp1 + " AND A.resourcename=\"%s\"";
            execp1 = String.format(execp1, name);

            execp2 = execp2 + " AND A.resourcename=\"%s\"";
            execp2 = String.format(execp2, name);
        }
        exec = execp1 + Vars.__UNION__ + execp2;
        System.err.println("ARG5 - " + exec);
        try {
            res = DbConnect.execStmAndGetResultSet(getConn(), exec);
            setFile_arg5(loopOverRSArg5(res));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getFile_arg5();
    }

    /**
     * Return arg6 dataset
     *
     * @param year the year to filter
     * @param author the author to filter
     * @param type the resource type
     * @param family the family to filter
     * @param name the resname to filter
     * @return the list of data
     */
    public List<String> getFile_arg6(String year, String author, String type, String family, String name) {
        ResultSet res;
        String execp1 = "";
        String execp2 = "";
        String exec = "";
        getFile_arg6().clear();
        if ("".equals(family)) {
            execp1 = Select2Exec.__SELECTARG6_SHARED_BASE_PART1__;
            execp2 = Select2Exec.__SELECTARG6_SHARED_BASE_PART2__;
        } else {
            execp1 = Select2Exec.__SELECTARG6_SHARED_FAM_PART1__;
            execp1 = execp1 + " AND C.family=\"%s\"";
            execp1 = String.format(execp1, family);

            execp2 = Select2Exec.__SELECTARG6_SHARED_FAM_PART2__;
            execp2 = execp2 + " AND C.family=\"%s\"";
            execp2 = String.format(execp2, family);
        }
        if (!"".equals(author)) {
            execp1 = execp1 + " AND A.author_1=\"%s\"";
            execp1 = String.format(execp1, author);
            execp2 = execp2 + " AND A.author_2=\"%s\"";
            execp2 = String.format(execp2, author);
        }
        if (!"".equals(year)) {
            execp1 = execp1 + " AND A.YEAR_1=\"%s\"";
            execp1 = String.format(execp1, year);

            execp2 = execp2 + " AND A.YEAR_2=\"%s\"";
            execp2 = String.format(execp2, year);
        }

        if (!"".equals(type)) {
            execp1 = execp1 + " AND A.resourcetype=\"%s\"";
            execp1 = String.format(execp1, type);

            execp2 = execp2 + " AND A.resourcetype=\"%s\"";
            execp2 = String.format(execp2, type);
        }

        if (!"".equals(name)) {
            execp1 = execp1 + " AND A.resourcename=\"%s\"";
            execp1 = String.format(execp1, name);

            execp2 = execp2 + " AND A.resourcename=\"%s\"";
            execp2 = String.format(execp2, name);
        }
        exec = execp1 + Vars.__UNION__ + execp2 + Vars.__ORDERBYARG6__;
        System.err.println("ARG6 - " + exec);
        try {
            res = DbConnect.execStmAndGetResultSet(getConn(), exec);
            setFile_arg6(loopOverRSArg6(res));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getFile_arg6();
    }

    /**
     * loop over the result set of filter data. 1 columns
     *
     * @param rs the result set
     * @return the resultset as string
     * @throws SQLException
     */
    private List<String> loopOverRSPrepareFilter(ResultSet rs) throws SQLException {
        List<String> list = new ArrayList<String>();
        String field = "";

        while (rs.next()) {
            field = rs.getString(1);

            list.add(field);

        }

        return list;
    }

    /**
     * loop over the result set of arg1 data. 4 columns
     *
     * @param rs the result set
     * @return the resultset as string tab-separated
     * @throws SQLException
     */
    private List<String> loopOverRSArg1(ResultSet rs) throws SQLException {
        List<String> list = new ArrayList<String>();
        String fname = "", lname = "", wikiname = "", affiliation = "";
        String out = "";
        int l = 0;
        while (rs.next()) {
            fname = rs.getString(1);
            lname = rs.getString(2);
            wikiname = rs.getString(3);
            affiliation = rs.getString(4);
            out = fname + Vars.__SEPTAB__ + lname + Vars.__SEPTAB__ + wikiname + Vars.__SEPTAB__ + affiliation;
            list.add(out);
            l++;
        }

        return list;
    }

    /**
     * loop over the result set of arg2 data. 2 columns
     *
     * @param rs the result set
     * @return the resultset as string tab-separated
     * @throws SQLException
     */
    private List<String> loopOverRSArg2(ResultSet rs) throws SQLException {
        List<String> list = new ArrayList<String>();
        String affiliation = "", norm_ffiliation = "";
        String out = "";
        int l = 0;
        while (rs.next()) {
            affiliation = rs.getString(1);
            norm_ffiliation = rs.getString(2);

            out = affiliation + Vars.__SEPTAB__ + norm_ffiliation;
            //System.err.println("line " + out);
            list.add(out);
            l++;
        }

        return list;
    }

    /**
     * loop over the result set of arg3 data. 4 columns
     *
     * @param rs the result set
     * @return the resultset as string tab-separated
     * @throws SQLException
     */
    private List<String> loopOverRSArg3(ResultSet rs) throws SQLException {
        List<String> list = new ArrayList<String>();
        String country = "", affiliation = "", wikiname = "", tot = "";
        String out = "";
        int l = 0;
        while (rs.next()) {
            country = rs.getString(1);
            affiliation = rs.getString(2);
            wikiname = rs.getString(3);
            tot = rs.getString(4);
            out = country + Vars.__SEPTAB__ + affiliation + Vars.__SEPTAB__ + wikiname + Vars.__SEPTAB__ + tot;
            //System.err.println("line " + out);
            list.add(out);
            l++;
        }

        return list;
    }

    /**
     * loop over the result set of arg4 data. 1 columns
     *
     * @param rs the result set
     * @return the resultset as string
     * @throws SQLException
     */
    private List<String> loopOverRSArg4(ResultSet rs) throws SQLException {
        List<String> list = new ArrayList<String>();
        String field = "";

        while (rs.next()) {
            field = rs.getString(1);

            list.add(field);

        }

        return list;
    }

    /**
     * loop over the result set of arg1 data. 5 columns
     *
     * @param rs the result set
     * @return the resultset as string tab-separated
     * @throws SQLException
     */
    private List<String> loopOverRSArg5(ResultSet rs) throws SQLException {
        List<String> list = new ArrayList<String>();
        String wiki_1 = "", affi_1 = "", wiki_2 = "", resname = "", affi_2 = "";
        String out = "";
        int l = 0;
        while (rs.next()) {
            wiki_1 = rs.getString(1);
            affi_1 = rs.getString(2);
            resname = rs.getString(3);
            wiki_2 = rs.getString(4);
            affi_2 = rs.getString(5);
            out = wiki_1 + Vars.__SEPTAB__ + affi_1 + Vars.__SEPTAB__ + resname + Vars.__SEPTAB__ + wiki_2 + Vars.__SEPTAB__ + affi_2;
            //System.err.println("line " + out);
            list.add(out);
            l++;
        }

        return list;
    }

    /**
     * loop over the result set of arg1 data. 2 columns
     *
     * @param rs the result set
     * @return the resultset as string tab-separated
     * @throws SQLException
     */
    private List<String> loopOverRSArg6(ResultSet rs) throws SQLException {
        List<String> list = new ArrayList<String>();
        String resource = "", conf = "";
        String out = "";
        int l = 0;
        while (rs.next()) {
            resource = rs.getString(1);
            conf = rs.getString(2);

            out = resource + Vars.__SEPTAB__ + conf;
            //System.err.println("line " + out);
            list.add(out);
            l++;
        }

        return list;
    }

    /**
     * @return the file_arg1
     */
    public List<String> getFile_arg1() {
        return file_arg1;
    }

    /**
     * @param file_arg1 the file_arg1 to set
     */
    public void setFile_arg1(List<String> file_arg1) {
        this.file_arg1 = file_arg1;
    }

    /**
     * @return the file_arg2
     */
    public List<String> getFile_arg2() {
        return file_arg2;
    }

    /**
     * @param file_arg2 the file_arg2 to set
     */
    public void setFile_arg2(List<String> file_arg2) {
        this.file_arg2 = file_arg2;
    }

    /**
     * @return the file_arg3
     */
    public List<String> getFile_arg3() {
        return file_arg3;
    }

    /**
     * @param file_arg3 the file_arg3 to set
     */
    public void setFile_arg3(List<String> file_arg3) {
        this.file_arg3 = file_arg3;
    }

    /**
     * @return the file_arg4
     */
    public List<String> getFile_arg4() {
        return file_arg4;
    }

    /**
     * @param file_arg4 the file_arg4 to set
     */
    public void setFile_arg4(List<String> file_arg4) {
        this.file_arg4 = file_arg4;
    }

    /**
     * @return the file_arg5
     */
    public List<String> getFile_arg5() {
        return file_arg5;
    }

    /**
     * @param file_arg5 the file_arg5 to set
     */
    public void setFile_arg5(List<String> file_arg5) {
        this.file_arg5 = file_arg5;
    }

    /**
     * @return the file_arg6
     */
    public List<String> getFile_arg6() {
        return file_arg6;
    }

    /**
     * @param file_arg6 the file_arg6 to set
     */
    public void setFile_arg6(List<String> file_arg6) {
        this.file_arg6 = file_arg6;
    }

}
