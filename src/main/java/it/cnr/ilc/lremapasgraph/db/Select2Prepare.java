/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lremapasgraph.db;

/**
 *
 * @author Riccardo Del Gratta &lt;riccardo.delgratta@ilc.cnr.it&gt;
 */
public class Select2Prepare {

    // tables
    public static final String __TAB_R_A__ = "resources_authors ";
    public static final String __TABA2AVR__ = "a2avr A ";
    public static final String __TABFAMILIES__ = "families ";
    public static final String __TABAFFI1__ = "affiliation AF1 ";
    public static final String __TABAFFI2__ = "affiliation AF2 ";
    public static final String __UNION__ = " UNION ";
    public static final String __ORDERBY1ASC__ = " ORDER BY 1 ASC; ";
    //

    // SELECTS
    // create the list of authors (fname lname) 
    public static final String __SELECTLISTOFAUTHORS__ = " SELECT  distinct "
            + "concat (replace(firstname,'\\\\n',' '), ' ', replace(lastname,'\\\\n',' '))  as author "
            + "FROM "+__TAB_R_A__+__ORDERBY1ASC__;

    
    
    // create the list of types (fname lname) 
    public static final String __SELECTLISTOFTYPES__ = " SELECT distinct type  as type "
            + "FROM "+__TAB_R_A__+__ORDERBY1ASC__;
    
    // create the list of families 
    public static final String __SELECTLISTOFFAMILIES__ = " SELECT distinct family  as family "
            + "FROM "+__TABFAMILIES__+__ORDERBY1ASC__;
    
    
    // create the list of years 
    public static final String __SELECTLISTOFYEARS__ = " SELECT distinct year  as year "
            + "FROM "+__TAB_R_A__+__ORDERBY1ASC__;
   
    // create the list of confs 
    public static final String __SELECTLISTOFCONFS__ = " SELECT distinct conf  as year "
            + "FROM "+__TAB_R_A__+__ORDERBY1ASC__;
    
    
    // create the list of confs and years (conf (year))
    public static final String __SELECTLISTOFCONFS_YEARS__ = " SELECT distinct concat(conf,' (',year,')')  as conf_year "
            + "FROM "+__TAB_R_A__+__ORDERBY1ASC__;
    
    // create a list of resourcenames
//    public static final String __SELECTLISTOFRESOURCES__ = " SELECT distinct A.name as resname "
//            + "FROM "+__TAB_R_A__+" A "+__ORDERBY1ASC__;
    
    public static final String __SELECTLISTOFRESOURCES__ = " SELECT distinct A.resourcename as resname "
            + "FROM "+__TABA2AVR__+__ORDERBY1ASC__;
   
   
}
