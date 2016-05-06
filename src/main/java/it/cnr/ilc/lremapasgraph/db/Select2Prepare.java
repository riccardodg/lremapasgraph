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
    
    // create the list of families (fname lname) 
    public static final String __SELECTLISTOFFAMILIES__ = " SELECT distinct family  as family "
            + "FROM "+__TABFAMILIES__+__ORDERBY1ASC__;
   
}
