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
public class Vars {
    public static String __DIR__ = "/tmp";
    public static String __SEP__ = "/";
    public static String __SEPTAB__ = "\t";
    public static String __FILE_NAME_ARG1__="distinct_authors.csv";
    public static String __FILE_NAME_ARG2__="affiliation_normalized.csv";
    public static String __FILE_NAME_ARG3__="size_affiliation.csv";
    public static String __FILE_NAME_ARG4__="distinct_resources.csv";
    public static String __FILE_NAME_ARG5__="distinct_a2avr.csv";
    public static String __FILE_NAME_ARG6__="shared_resources.csv";
    
    // vars for select 
    public static final String __ORDERBYARG4__ = " ORDER BY 1; ";
    public static final String __ORDERBYARG6__ = " ORDER BY 1; ";
    public static final String __ORDERBYAFFI__ = " ORDER BY affiliation; ";
    public static final String __UNION__ = " UNION ";
    public static final String __ORDERBYCOUNTARG3__ = " ORDER BY 4 DESC; ";
    
}
