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
    public static final String __TABR2RVA__ = "r2rva ";
    public static final String __TABAFFI__ = "affiliations ";
    public static final String __TABFAMILY__ = "families C ";
    public static final String __TABAFFI1__ = "affiliations AF1 ";
    public static final String __TABAFFI2__ = "affiliations AF2 ";

    // SELECTS
    // create the first argument authors distinct_authors_<ALLYEAR>.csv
    /*
    arg1 argument authors distinct_authors.csv base (no family involved) part 1
     */
    public static final String __SELECTARG1_DISTAUTH_PART1_BASE__ = " SELECT "
            + "distinct replace(firstname_1,'\\n',' ') as firstname, "
            + "replace(lastname_1,'\\n',' ') as lastname, "
            + "concat(replace(firstname_1,'\\n',' '),'_', replace(lastname_1,'\\n',' ')) as wikiname, "
            + "lower(affiliation_1) as affiliation "
            + "FROM     " + __TABA2AVR__ + " A, "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE  YEAR_1 = YEAR_2 and CONF_1=CONF_2 "
            + "AND LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year";

    /*
    arg1 argument authors distinct_authors.csv base (no family involved) part 2
     */
    public static final String __SELECTARG1_DISTAUTH_PART2_BASE__ = " SELECT "
            + "distinct replace(firstname_2,'\\n',' '), "
            + "replace(lastname_2,'\\n',' '), "
            + "concat(replace(firstname_2,'\\n',' '),'_', replace(lastname_2,'\\n',' ')) as wikiname, "
            + "lower(affiliation_2) as affiliation "
            + "FROM     " + __TABA2AVR__ + " A, "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE  YEAR_1 = YEAR_2 and CONF_1=CONF_2 "
            + "AND LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year";

    /*
    arg1 argument authors distinct_authors.csv family  part 1
     */
    public static final String __SELECTARG1_DISTAUTH_PART1_FAM__ = " SELECT distinct replace(firstname_1,'\\n',' '), "
            + "replace(lastname_1,'\\n',' '), "
            + "concat(replace(firstname_1,'\\n',' '),'_', replace(lastname_1,'\\n',' ')) as wikiname, "
            + "lower(affiliation_1) as affiliation "
            + "FROM     " + __TABA2AVR__ + " A, "
            + __TABFAMILY__ + ", "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE  YEAR_1 = YEAR_2 and CONF_1=CONF_2 "
            + "AND LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year  "
            + "AND lower(C.name)=lower(A.resourcename) "
            + "AND C.CONF=A.CONF_1 and C.YEAR=A.YEAR_1 ";

    /*
    arg1 argument authors distinct_authors.csv family  part 2
     */
    public static final String __SELECTARG1_DISTAUTH_PART2_FAM__ = " SELECT distinct replace(firstname_2,'\\n',' '), "
            + "replace(lastname_2,'\\n',' '), "
            + "concat(replace(firstname_2,'\\n',' '),'_', replace(lastname_2,'\\n',' ')) as wikiname, "
            + "lower(affiliation_2) as affiliation "
            + "FROM     " + __TABA2AVR__ + " A, "
            + __TABFAMILY__ + ", "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE  YEAR_1 = YEAR_2 and CONF_1=CONF_2 "
            + "AND LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year  "
            + "AND lower(C.name)=lower(A.resourcename) "
            + "AND C.CONF=A.CONF_1 and C.YEAR=A.YEAR_1 ";

    /*
    arg2 affiliation affiliation_normalized_<YEAR>.csv
     */
    public static final String __SELECTARG2_DISTAFFI__ = "SELECT DISTINCT affiliation,norm_affiliation FROM " + __TABAFFI__;

    /*
    arg3 size_affiliation.csv base (no family involved) part 1
     */
    public static final String __SELECTARG3_SIZE_AFFI_PART1_BASE__ = "SELECT COUNTRY_1,"
            + "lower(affiliation_1) as affiliation,"
            + "concat(replace(firstname_1,'\n',' '),'_', replace(lastname_1,'\n',' ')) as fullname, "
            + "count(*) "
            + "FROM     " + __TABA2AVR__ + " A, "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE  YEAR_1 = YEAR_2 and CONF_1=CONF_2 "
            + "AND LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year "
            + " group by lower(replace(affiliation_1, '\n', ' ')) ,concat(replace(firstname_1,'\n',' '),'_', replace(lastname_1,'\n',' '))";

    /*
    arg3 size_affiliation.csv base (no family involved) part 2
     */
    public static final String __SELECTARG3_SIZE_AFFI_PART2_BASE__ = "SELECT COUNTRY_2,"
            + "lower(affiliation_2) as affiliation,"
            + "concat(replace(firstname_2,'\n',' '),'_', replace(lastname_2,'\n',' ')) as fullname, "
            + "count(*) "
            + "FROM     " + __TABA2AVR__ + " A, "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE  YEAR_1 = YEAR_2 and CONF_1=CONF_2 "
            + "AND LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year "
            + " group by lower(replace(affiliation_2, '\n', ' ')) ,concat(replace(firstname_2,'\n',' '),'_', replace(lastname_2,'\n',' '))";

    /*
    arg3 size_affiliation.csv family  part 1
     */
    public static final String __SELECTARG3_SIZE_AFFI_PART1_FAM__ = "SELECT COUNTRY_1,"
            + "lower(affiliation_1) as affiliation,"
            + "concat(replace(firstname_1,'\n',' '),'_', replace(lastname_1,'\n',' ')) as fullname, "
            + "count(*) "
            + "FROM     " + __TABA2AVR__ + " A, "
            + __TABFAMILY__ + ", "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE  YEAR_1 = YEAR_2 and CONF_1=CONF_2 "
            + "AND LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year  "
            + "AND lower(C.name)=lower(A.resourcename) "
            + "AND C.CONF=A.CONF_1 and C.YEAR=A.YEAR_1 "
            + " group by lower(replace(affiliation_1, '\n', ' ')) ,concat(replace(firstname_1,'\n',' '),'_', replace(lastname_1,'\n',' '))";

    /*
    arg3 size_affiliation.csv family  part 2
     */
    public static final String __SELECTARG3_SIZE_AFFI_PART2_FAM__ = "SELECT COUNTRY_2,"
            + "lower(affiliation_2) as affiliation,"
            + "concat(replace(firstname_2,'\n',' '),'_', replace(lastname_2,'\n',' ')) as fullname, "
            + "count(*) "
            + "FROM     " + __TABA2AVR__ + " A, "
            + __TABFAMILY__ + ", "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE  YEAR_1 = YEAR_2 and CONF_1=CONF_2 "
            + "AND LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year  "
            + "AND lower(C.name)=lower(A.resourcename) "
            + "AND C.CONF=A.CONF_1 and C.YEAR=A.YEAR_1 "
            + " group by lower(replace(affiliation_2, '\n', ' ')) ,concat(replace(firstname_2,'\n',' '),'_', replace(lastname_2,'\n',' '))";

    /*
    arg4 distinct_resources.csv base (no family involved) part 1
     */
    public static final String __SELECTARG4_DISTINCTRES_BASE_P1__ = "SELECT "
            + "distinct "
            //+ "lower(replace(A.resourcename,'\n',' ')) as resname "
            + " A.resourcename as resname "
            + "FROM " + __TABA2AVR__ + " A, "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE YEAR_1 = YEAR_2 and "
            + "CONF_1=CONF_2 AND "
            + "LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year ";

    /*
    arg4 distinct_resources.csv base (no family involved) part 2
     */
    public static final String __SELECTARG4_DISTINCTRES_BASE_P2__ = "SELECT "
            + "distinct "
            //+ "lower(replace(A.resourcename,'\n',' ')) as resname "
            + " A.resourcename as resname "
            + "FROM " + __TABA2AVR__ + " A, "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE YEAR_1 = YEAR_2 and "
            + "CONF_1=CONF_2 AND "
            + "LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year ";

    /*
    arg4 distinct_resources.csv family  part 1
     */
    public static final String __SELECTARG4_DISTINCTRES_FAM_P1__ = "SELECT "
            + "distinct "
            //+ "lower(replace(A.resourcename,'\n',' ')) as resname "
            + " A.resourcename as resname "
            + "FROM " + __TABA2AVR__ + " A, "
            + __TABFAMILY__ + " ,"
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE YEAR_1 = YEAR_2 and "
            + "CONF_1=CONF_2 AND "
            + "LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year "
            + "AND lower(C.name)=lower(A.resourcename) "
            + "AND C.CONF=A.CONF_1 and C.YEAR=A.YEAR_1 ";

    /*
    arg4 distinct_resources.csv family part 2
     */
    public static final String __SELECTARG4_DISTINCTRES_FAM_P2__ = "SELECT "
            + "distinct "
            //+ "lower(replace(A.resourcename,'\n',' ')) as resname "
            + " A.resourcename as resname "
            + "FROM " + __TABA2AVR__ + " A, "
            + __TABFAMILY__ + " ,"
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE YEAR_1 = YEAR_2 and "
            + "CONF_1=CONF_2 AND "
            + "LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year "
            + "AND lower(C.name)=lower(A.resourcename) "
            + "AND C.CONF=A.CONF_1 and C.YEAR=A.YEAR_1 ";

    // arg5 distinct_a2avr_lrec2010.csv  args[5]
    public static final String __SELECTARG5_A2AVR_BASE_PART1__ = "SELECT distinct "
            + "concat(replace(A.firstname_1,'\n',' '),'_', replace(A.lastname_1,'\n',' ')) as wikiname_1, "
            + "AF1.norm_affiliation as affi_1,"
            //+ "trim(lower(replace(A.resourcename,'\n',' '))) as resname,"
            + "A.resourcename as resname,"
            + "concat(replace(A.firstname_2,'\n',' '),'_', replace(A.lastname_2,'\n',' ')) as wikiname_2, "
            + "AF2.norm_affiliation as affi2 "
            + "FROM     " + __TABA2AVR__ + " A, "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE  YEAR_1 = YEAR_2 and CONF_1=CONF_2 "
            + "AND LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year";

    public static final String __SELECTARG5_A2AVR_BASE_PART2__ = "SELECT distinct "
            + "concat(replace(A.firstname_1,'\n',' '),'_', replace(A.lastname_1,'\n',' ')) as wikiname_1, "
            + "AF1.norm_affiliation as affi_1,"
            //+ "trim(lower(replace(A.resourcename,'\n',' '))) as resname,"
            + "A.resourcename as resname,"
            + "concat(replace(A.firstname_2,'\n',' '),'_', replace(A.lastname_2,'\n',' ')) as wikiname_2, "
            + "AF2.norm_affiliation as affi2 "
            + "FROM     " + __TABA2AVR__ + " A, "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE  YEAR_1 = YEAR_2 and CONF_1=CONF_2 "
            + "AND LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year";

    public static final String __SELECTARG5_A2AVR_FAM_PART1__ = "SELECT distinct "
            + "concat(replace(A.firstname_1,'\n',' '),'_', replace(A.lastname_1,'\n',' ')) as wikiname_1, "
            + "AF1.norm_affiliation as affi_1,"
            //+ "trim(lower(replace(A.resourcename,'\n',' '))) as resname,"
            + "A.resourcename as resname,"
            + "concat(replace(A.firstname_2,'\n',' '),'_', replace(A.lastname_2,'\n',' ')) as wikiname_2, "
            + "AF2.norm_affiliation as affi2 "
            + "FROM     " + __TABA2AVR__ + " A, "
            + __TABFAMILY__ + ", "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE  YEAR_1 = YEAR_2 and CONF_1=CONF_2 "
            + "AND LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year  "
            + "AND lower(C.name)=lower(A.resourcename) "
            + "AND C.CONF=A.CONF_1 and C.YEAR=A.YEAR_1 ";

    /*
    arg5 distinct_a2avr.csv family part 2
     */
    public static final String __SELECTARG5_A2AVR_FAM_PART2__ = "SELECT distinct "
            + "concat(replace(A.firstname_1,'\n',' '),'_', replace(A.lastname_1,'\n',' ')) as wikiname_1, "
            + "AF1.norm_affiliation as affi_1,"
            //+ "trim(lower(replace(A.resourcename,'\n',' '))) as resname,"
            + "A.resourcename as resname,"
            + "concat(replace(A.firstname_2,'\n',' '),'_', replace(A.lastname_2,'\n',' ')) as wikiname_2, "
            + "AF2.norm_affiliation as affi2 "
            + "FROM     " + __TABA2AVR__ + " A, "
            + __TABFAMILY__ + ", "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE  YEAR_1 = YEAR_2 and CONF_1=CONF_2 "
            + "AND LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year  "
            + "AND lower(C.name)=lower(A.resourcename) "
            + "AND C.CONF=A.CONF_1 and C.YEAR=A.YEAR_1 ";

    /*
    arg6 shared_resources_2010.csv base (no family involved) part 1
     */
    public static final String __SELECTARG6_SHARED_BASE_PART1__ = "SELECT distinct "
            //+ "trim(lower(replace(A.resourcename,'\n',' '))) as resname, "
            + "A.resourcename as resname,"
            + "CONF_1 "
            + "FROM  " + __TABA2AVR__ + " A, "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE    YEAR_1 = YEAR_2 "
            + "AND CONF_1=CONF_2 "
            + "AND LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year ";

    /*
    arg6 shared_resources_2010.csv base (no family involved) part 2
     */
    public static final String __SELECTARG6_SHARED_BASE_PART2__ = "SELECT distinct "
            //+ "trim(lower(replace(A.resourcename,'\n',' '))) as resname, "
            + "A.resourcename as resname,"
            + "CONF_1 "
            + "FROM  " + __TABA2AVR__ + " A, "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE    YEAR_1 = YEAR_2 "
            + "AND CONF_1=CONF_2 "
            + "AND LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year ";

    /*
    arg6 shared_resources_2010.csv family  part 1
     */
    public static final String __SELECTARG6_SHARED_FAM_PART1__ = "SELECT distinct "
            //+ "trim(lower(replace(A.resourcename,'\n',' '))) as resname, "
            + "A.resourcename as resname,"
            + "CONF_1 "
            + "FROM     " + __TABA2AVR__ + " A, "
            + __TABFAMILY__ + ", "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE  YEAR_1 = YEAR_2 and CONF_1=CONF_2 "
            + "AND LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year  "
            + "AND lower(C.name)=lower(A.resourcename) "
            + "AND C.CONF=A.CONF_1 and C.YEAR=A.YEAR_1 ";

    /*
    arg6 shared_resources_2010.csv family  part 2
     */
    public static final String __SELECTARG6_SHARED_FAM_PART2__ = "SELECT distinct "
            //+ "trim(lower(replace(A.resourcename,'\n',' '))) as resname, "
            + "A.resourcename as resname,"
            + "CONF_1 "
            + "FROM     " + __TABA2AVR__ + " A, "
            + __TABFAMILY__ + ", "
            + __TABAFFI1__ + ", "
            + __TABAFFI2__
            + "WHERE  YEAR_1 = YEAR_2 and CONF_1=CONF_2 "
            + "AND LOWER(A.affiliation_1) = AF1.affiliation "
            + "AND A.YEAR_1 = AF1.year "
            + "AND LOWER(A.affiliation_2) = AF2.affiliation "
            + "AND A.YEAR_2 = AF2.year  "
            + "AND lower(C.name)=lower(A.resourcename) "
            + "AND C.CONF=A.CONF_1 and C.YEAR=A.YEAR_1 ";

    // arg1 r2rva visualization
    public static final String __SELECTARG1_R2R_PART1__ = "SELECT distinct A.resourcename_1, "
            + "concat(A.CONF_1,' ', A.YEAR_1),  A.author, "
            + "AF.norm_affiliation, "
            + "A.resourcename_2, "
            + "concat(A.CONF_2,' ', A.YEAR_2)"
            + "FROM " + __TABR2RVA__ + " A, " + __TABAFFI__ + " AF  where LOWER(A.affiliation_1) = AF.affiliation";

    public static final String __SELECTARG1_R2R_PART2__ = "SELECT distinct A.resourcename_1, "
            + "concat(A.CONF_1,' ', A.YEAR_1),  A.author, "
            + "AF.norm_affiliation, "
            + "A.resourcename_2, "
            + "concat(A.CONF_2,' ', A.YEAR_2)"
            + "FROM " + __TABR2RVA__ + " A, " + __TABAFFI__ + " AF  where LOWER(A.affiliation_1) = AF.affiliation";

    public static final String __SELECTARG1_R2R_FAM_PART1__ = "SELECT distinct A.resourcename_1, "
            + "concat(A.CONF_1,' ', A.YEAR_1),  A.author, "
            + "AF.norm_affiliation, "
            + "A.resourcename_2, "
            + "concat(A.CONF_2,' ', A.YEAR_2)"
            + "FROM " + __TABR2RVA__ + " A, " + __TABAFFI__ + " AF  ," + __TABFAMILY__
            + "where LOWER(A.affiliation_1) = AF.affiliation "
            + "AND lower(C.name)=lower(A.resourcename_1) "
            + "AND C.CONF=A.CONF_1 and C.YEAR=A.YEAR_1 ";

    public static final String __SELECTARG1_R2R_FAM_PART2__ = "SELECT distinct A.resourcename_1, "
            + "concat(A.CONF_1,' ', A.YEAR_1),  A.author, "
            + "AF.norm_affiliation, "
            + "A.resourcename_2, "
            + "concat(A.CONF_2,' ', A.YEAR_2)"
            + "FROM " + __TABR2RVA__ + " A, " + __TABAFFI__ + " AF  ," + __TABFAMILY__
            + "where LOWER(A.affiliation_1) = AF.affiliation "
            + "AND lower(C.name)=lower(A.resourcename_2) "
            + "AND C.CONF=A.CONF_2 and C.YEAR=A.YEAR_2 ";

}
