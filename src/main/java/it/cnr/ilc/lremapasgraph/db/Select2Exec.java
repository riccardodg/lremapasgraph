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
public class Select2Exec {

    // tables
    public static final String __TABA2AVR__ = "a2avr ";
    public static final String __TABAFFI__ = "affiliation ";
    public static final String __TABAFFI1__ = "affiliation AF1 ";
    public static final String __TABAFFI2__ = "affiliation AF2 ";
    public static final String __UNION__ = " UNION ";
    public static final String __ORDERBYAFFI__ = " ORDER BY affiliation; ";

    // SELECTS
    // create the first argument authors distinct_authors_<ALLYEAR>.csv
    public static final String __SELECTARG1_DISTAUTH_PART1__ = " SELECT distinct replace(firstname_1,'\\n',' '), "
            + "replace(lastname_1,'\\n',' '), "
            + "concat(replace(firstname_1,'\\n',' '),'_', replace(lastname_1,'\\n',' ')) as wikiname, "
            + "lower(affiliation_1) as affiliation FROM " + __TABA2AVR__;

    public static final String __SELECTARG1_DISTAUTH_PART2__ = " SELECT distinct replace(firstname_2,'\\n',' '), "
            + "replace(lastname_2,'\\n',' '), "
            + "concat(replace(firstname_2,'\\n',' '),'_', replace(lastname_2,'\\n',' ')) as wikiname, "
            + "lower(affiliation_2) as affiliation FROM " + __TABA2AVR__ + " order by affiliation;";

    public static final String __SELECTARG1_DISTAUTH__ = __SELECTARG1_DISTAUTH_PART1__ + __UNION__ + __SELECTARG1_DISTAUTH_PART2__ + __ORDERBYAFFI__;

    // same select but with year as parameter
    public static final String __SELECTARG1_DISTAUTH_PART1_WY__ = " SELECT distinct replace(firstname_1,'\\n',' '), "
            + "replace(lastname_1,'\\n',' '), "
            + "concat(replace(firstname_1,'\\n',' '),'_', replace(lastname_1,'\\n',' ')) as wikiname, "
            + "lower(affiliation_1) as affiliation FROM " + __TABA2AVR__ + " WHERE YEAR_1='%s'";

    public static final String __SELECTARG1_DISTAUTH_PART2_WY__ = " SELECT distinct replace(firstname_2,'\\n',' '), "
            + "replace(lastname_2,'\\n',' '), "
            + "concat(replace(firstname_2,'\\n',' '),'_', replace(lastname_2,'\\n',' ')) as wikiname, "
            + "lower(affiliation_2) as affiliation FROM " + __TABA2AVR__ + " WHERE YEAR_2='%s' ";

    public static final String __SELECTARG1_DISTAUTH_WY__ = __SELECTARG1_DISTAUTH_PART1_WY__ + __UNION__ + __SELECTARG1_DISTAUTH_PART2_WY__ + __ORDERBYAFFI__;
}
