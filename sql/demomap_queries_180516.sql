/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Riccardo Del Gratta &lt;riccardo.delgratta@ilc.cnr.it&gt;
 * Created: May 18, 2016
 */
-- demomap_db --
-- affiliations --
DROP TABLE IF EXISTS `affiliations`;
CREATE TABLE `affiliations` (
  `CONF` varchar(50) COLLATE utf8_bin NOT NULL,
  `YEAR` char(4) COLLATE utf8_bin NOT NULL,
  `affiliation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `norm_affiliation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `country` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`affiliation`,`norm_affiliation`,`CONF`,`YEAR`,`country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- families --

DROP TABLE IF EXISTS `temp_families`;
CREATE TABLE `temp_families` (
  `CONF` varchar(50) COLLATE utf8_bin NOT NULL,
  `YEAR` char(4) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `family` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`name`,`family`,`CONF`,`YEAR`),
  KEY `famidx` (`family`),
  KEY `residx` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `families`;
CREATE TABLE `families` (
  `CONF` varchar(50) COLLATE utf8_bin NOT NULL,
  `YEAR` char(4) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `family` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`name`,`family`,`CONF`,`YEAR`),
  KEY `famidx` (`family`),
  KEY `residx` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- resources_authors --
DROP TABLE IF EXISTS `demomap_db`.`resources_authors`;
CREATE TABLE `demomap_db`.`resources_authors` (
  `resourceid` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '589275fdd4e5908f18310b56beaf439b',
  `CONF` varchar(50) COLLATE utf8_bin NOT NULL,
  `YEAR` char(4) COLLATE utf8_bin NOT NULL,
  `passcode` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '',
  `resid` int(11) NOT NULL DEFAULT '0',
  `type` text COLLATE utf8_bin,
  `name` text COLLATE utf8_bin,
  `status` text COLLATE utf8_bin,
  `authornumber` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '',
  `firstname` text COLLATE utf8_bin,
  `lastname` text COLLATE utf8_bin,
  `email` text COLLATE utf8_bin,
  `affiliation` text COLLATE utf8_bin,
  `country` text COLLATE utf8_bin,
  PRIMARY KEY (`resourceid`,`authornumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- a2avr --
DROP   TABLE IF EXISTS `demomap_db`.`a2avr`;
CREATE TABLE `demomap_db`.`a2avr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CONF_1` varchar(50) COLLATE utf8_bin NOT NULL,
  `YEAR_1` char(4) COLLATE utf8_bin NOT NULL,
  `passcode_1` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '',
  `firstname_1` text COLLATE utf8_bin,
  `lastname_1` text COLLATE utf8_bin,
  `author_1` VARCHAR(255) COLLATE utf8_bin,
  `email_1` text COLLATE utf8_bin,
  `affiliation_1` text COLLATE utf8_bin,
  `country_1` text COLLATE utf8_bin,
  `resourcename` varchar(255)
  `resourcetype` text COLLATE utf8_bin,
  `CONF_2` varchar(50) COLLATE utf8_bin NOT NULL,
  `YEAR_2` char(4) COLLATE utf8_bin NOT NULL,
  `passcode_2` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '',
  `firstname_2` text COLLATE utf8_bin,
  `lastname_2` text COLLATE utf8_bin,
  `author_2` VARCHAR(255) COLLATE utf8_bin,
  `email_2` text COLLATE utf8_bin,
  `affiliation_2` text COLLATE utf8_bin,
  `country_2` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

ALTER TABLE `demomap_db`.`a2avr` 
ADD INDEX `idxname` (`resourcename` ASC);


-- cited_resources --
DROP   TABLE IF EXISTS `cited_resources`;
CREATE TABLE `cited_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CONF` varchar(50) COLLATE utf8_bin NOT NULL,
  `YEAR` char(4) COLLATE utf8_bin NOT NULL,
  `number` INT(11) NOT NULL DEFAULT 1,
  `resourcename` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- shared_resources --
DROP   TABLE IF EXISTS `shared_resources`;
CREATE TABLE `shared_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CONF` varchar(50) COLLATE utf8_bin NOT NULL,
  `YEAR` char(4) COLLATE utf8_bin NOT NULL,
  `number` INT(11) NOT NULL DEFAULT 1,
  `resourcename` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- cited_shared_resources --
DROP   TABLE IF EXISTS `cited_shared_resources`;
CREATE TABLE `cited_shared_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CONF` varchar(50) COLLATE utf8_bin NOT NULL,
  `YEAR` char(4) COLLATE utf8_bin NOT NULL,
`resourcename` text COLLATE utf8_bin,
  `number_of_citation` INT(11) NOT NULL DEFAULT 1,
  `number_of_shared_authors` INT(11) NOT NULL DEFAULT 1,
`number_of_links` INT(11) NOT NULL DEFAULT 1,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- list_of_resources_by_year --
DROP TABLE IF EXISTS `list_of_resources_by_year`;
CREATE TABLE `list_of_resources_by_year` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CONF` varchar(50) COLLATE utf8_bin NOT NULL,
  `YEAR` char(4) COLLATE utf8_bin NOT NULL,
  `resourcename` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- r2rva --
DROP   TABLE IF EXISTS `demomap_db`.`r2rva`;
CREATE TABLE `demomap_db`.`r2rva` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CONF_1` varchar(50) COLLATE utf8_bin NOT NULL,
  `YEAR_1` char(4) COLLATE utf8_bin NOT NULL,
  `passcode_1` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '',
  `resourcename_1` varchar(255) ,
  `resourcetype_1` text COLLATE utf8_bin ,
  `firstname` text COLLATE utf8_bin,
  `lastname` text COLLATE utf8_bin,
  `author` VARCHAR(255) COLLATE utf8_bin,
  `email_1` text COLLATE utf8_bin,
  `affiliation_1` text COLLATE utf8_bin,
  `country_1` text COLLATE utf8_bin,
`email_2` text COLLATE utf8_bin,
  `affiliation_2` text COLLATE utf8_bin,
  `country_2` text COLLATE utf8_bin,  
  `CONF_2` varchar(50) COLLATE utf8_bin NOT NULL,
  `YEAR_2` char(4) COLLATE utf8_bin NOT NULL,
  `passcode_2` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '',
`resourcename_2` varchar(255)  ,
  `resourcetype_2` text COLLATE utf8_bin ,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- data --

-- families --
LOAD DATA LOCAL INFILE '/home/riccardo/Dropbox/2015/CLIC2015/mappa/families_2010_gp.csv' INTO TABLE temp_families
CHARACTER SET utf8
  FIELDS TERMINATED BY '\t' OPTIONALLY ENCLOSED BY '"';
LOAD DATA LOCAL INFILE '/home/riccardo/Dropbox/2015/CLIC2015/mappa/families_2012_gp.csv' INTO TABLE temp_families
CHARACTER SET utf8
  FIELDS TERMINATED BY '\t' OPTIONALLY ENCLOSED BY '"';
LOAD DATA LOCAL INFILE '/home/riccardo/Dropbox/2015/CLIC2015/mappa/families_2014_gp.csv' INTO TABLE temp_families
  CHARACTER SET utf8
  FIELDS TERMINATED BY '\t' OPTIONALLY ENCLOSED BY '"';

INSERT INTO families SELECT CONF, YEAR, trim(lower(replace(name,'\n',' '))), family from temp_families where family<>'Family' ;

-- fill resources_authors --
INSERT INTO `demomap_db`.`resources_authors` 
SELECT 
    R.resourceid, R.CONF, R.YEAR,  R.passcode, R.resid, R.type,R.name, P.status, A.authornumber, A.firstname, A.lastname, A.email, 
     case when A.affiliation is not null then replace(A.affiliation,'\n',' ') else 'NULLAFFI' end as affiliation, 
     case when A.COUNTRY is not null then A.COUNTRY else 'NULLC' end as COUNTRY

FROM
    lremap_authors_norm A,
    lremap_resource_norm R,
    lremap_papers_norm P
WHERE
    A.resourceid = R.resourceid
        AND P.resourceid = A.resourceid
        AND P.status LIKE 'Accep%'
        AND R.conf LIKE 'LREC%'
        AND A.authornumber <> 'Main Contact'
ORDER BY year DESC;

-- fill affiliations --
use demomap_db;
INSERT INTO affiliations
SELECT DISTINCT 
    CONF, YEAR,
    LOWER(affiliation) as affiliation,
    affiliation as norm_affiliation, 
    COUNTRY
FROM
    resources_authors

ORDER BY affiliation;

-- fill a2avr --
INSERT INTO `demomap_db`.`a2avr` (CONF_1,YEAR_1,passcode_1,firstname_1,
    lastname_1, author_1,
    email_1,
    affiliation_1,
    country_1,
    resourcename,
    resourcetype,
    CONF_2,YEAR_2,passcode_2,firstname_2,
    lastname_2,author_2,
    email_2,
    affiliation_2,
    country_2)


 SELECT DISTINCT
    RA1.CONF,
    RA1.YEAR,
    RA1.passcode,
    RA1.firstname,
    RA1.lastname,
    concat (replace(RA1.firstname,'\\n',' '), ' ', replace(RA1.lastname,'\\n',' ')),
    RA1.email,
    RA1.affiliation,
    RA1.country,
    -- RA1.name,
    trim(lower(replace(RA1.name,'\n',' '))),
    RA1.type,
    RA2.CONF,
    RA2.YEAR,
    RA2.passcode,
    RA2.firstname,
    RA2.lastname,
    concat (replace(RA2.firstname,'\\n',' '), ' ', replace(RA2.lastname,'\\n',' ')),
    RA2.email,
    RA2.affiliation,
    RA2.country
FROM
    resources_authors RA1,
    resources_authors RA2
WHERE
    RA1.name = RA2.name;
        --AND RA1.email <> RA2.email;


-- fill cited_resources--
INSERT INTO `demomap_db`.`cited_resources` (CONF, YEAR, number, resourcename) 
SELECT 
    Q.CONF_1, Q.YEAR_1,COUNT(*), Q.lrn
FROM
    (SELECT DISTINCT
        CONF_1, YEAR_1,lower(resourcename) as lrn, passcode_1 as passcode
    FROM
        demomap_db.a2avr
    WHERE
        YEAR_1 = '2010' AND YEAR_1 = YEAR_2
            AND CONF_1 = CONF_2 -- and passcode_1<>passcode_2
    UNION 
	SELECT DISTINCT
        CONF_2, YEAR_2,lower(resourcename) as lrn, passcode_2
    FROM
        demomap_db.a2avr
    WHERE
        YEAR_1 = '2010' AND YEAR_1 = YEAR_2
            AND CONF_1 = CONF_2 -- and passcode_1<>passcode_2
    ORDER BY 1) AS Q
GROUP BY Q.lrn
HAVING COUNT(*)>1
ORDER BY 3 DESC;

INSERT INTO `demomap_db`.`cited_resources` (CONF, YEAR, number, resourcename) 
SELECT 
    Q.CONF_1, Q.YEAR_1,COUNT(*), Q.lrn
FROM
    (SELECT DISTINCT
        CONF_1, YEAR_1,lower(resourcename) as lrn, passcode_1 as passcode
    FROM
        demomap_db.a2avr
    WHERE
        YEAR_1 = '2012' AND YEAR_1 = YEAR_2
            AND CONF_1 = CONF_2 -- and passcode_1<>passcode_2
    UNION 
	SELECT DISTINCT
        CONF_2, YEAR_2,lower(resourcename) as lrn, passcode_2
    FROM
        demomap_db.a2avr
    WHERE
        YEAR_1 = '2012' AND YEAR_1 = YEAR_2
            AND CONF_1 = CONF_2 -- and passcode_1<>passcode_2
    ORDER BY 1) AS Q
GROUP BY Q.lrn
HAVING COUNT(*)>1
ORDER BY 3 DESC;
INSERT INTO `demomap_db`.`cited_resources` (CONF, YEAR, number, resourcename) 
SELECT 
    Q.CONF_1, Q.YEAR_1,COUNT(*), Q.lrn
FROM
    (SELECT DISTINCT
        CONF_1, YEAR_1,lower(resourcename) as lrn, passcode_1 as passcode
    FROM
        demomap_db.a2avr
    WHERE
        YEAR_1 = '2014' AND YEAR_1 = YEAR_2
            AND CONF_1 = CONF_2 -- and passcode_1<>passcode_2
    UNION 
	SELECT DISTINCT
        CONF_2, YEAR_2,lower(resourcename) as lrn, passcode_2
    FROM
        demomap_db.a2avr
    WHERE
        YEAR_1 = '2014' AND YEAR_1 = YEAR_2
            AND CONF_1 = CONF_2 -- and passcode_1<>passcode_2
    ORDER BY 1) AS Q
GROUP BY Q.lrn
HAVING COUNT(*)>1
ORDER BY 3 DESC;

-- fill shared_resources--
INSERT INTO `demomap_db`.`shared_resources` (CONF, YEAR, number, resourcename) 
SELECT 
    Q.CONF_1, Q.YEAR_1,COUNT(*), Q.lrn
FROM
    (SELECT DISTINCT firstname_1, lastname_1,
        CONF_1, YEAR_1,lower(resourcename) as lrn
    FROM
        demomap_db.a2avr
    WHERE
        YEAR_1 = '2010' AND YEAR_1 = YEAR_2 and email_1<> email_2
            AND CONF_1 = CONF_2
            UNION SELECT DISTINCT firstname_2, lastname_2,
        CONF_1, YEAR_1,lower(resourcename) as lrn
    FROM
        demomap_db.a2avr
    WHERE
        YEAR_1 = '2010' AND YEAR_1 = YEAR_2  and email_1<> email_2
            AND CONF_1 = CONF_2
    ORDER BY 1) AS Q
GROUP BY Q.lrn
HAVING COUNT(*)>1
ORDER BY 3 DESC;

INSERT INTO `demomap_db`.`shared_resources` (CONF, YEAR, number, resourcename) 
SELECT 
    Q.CONF_1, Q.YEAR_1,COUNT(*), Q.lrn
FROM
    (SELECT DISTINCT firstname_1, lastname_1,
        CONF_1, YEAR_1,lower(resourcename) as lrn
    FROM
        demomap_db.a2avr
    WHERE
        YEAR_1 = '2012' AND YEAR_1 = YEAR_2 and email_1<> email_2
            AND CONF_1 = CONF_2
            UNION SELECT DISTINCT firstname_2, lastname_2,
        CONF_1, YEAR_1,lower(resourcename) as lrn
    FROM
        demomap_db.a2avr
    WHERE
        YEAR_1 = '2012' AND YEAR_1 = YEAR_2  and email_1<> email_2
            AND CONF_1 = CONF_2
    ORDER BY 1) AS Q
GROUP BY Q.lrn
HAVING COUNT(*)>1
ORDER BY 3 DESC;

INSERT INTO `demomap_db`.`shared_resources` (CONF, YEAR, number, resourcename) 
SELECT 
    Q.CONF_1, Q.YEAR_1,COUNT(*), Q.lrn
FROM
    (SELECT DISTINCT firstname_1, lastname_1,
        CONF_1, YEAR_1,lower(resourcename) as lrn
    FROM
        demomap_db.a2avr
    WHERE
        YEAR_1 = '2014' AND YEAR_1 = YEAR_2 and email_1<> email_2
            AND CONF_1 = CONF_2
            UNION SELECT DISTINCT firstname_2, lastname_2,
        CONF_1, YEAR_1,lower(resourcename) as lrn
    FROM
        demomap_db.a2avr
    WHERE
        YEAR_1 = '2014' AND YEAR_1 = YEAR_2  and email_1<> email_2
            AND CONF_1 = CONF_2
    ORDER BY 1) AS Q
GROUP BY Q.lrn
HAVING COUNT(*)>1
ORDER BY 3 DESC;

-- fill cited_shared_resources --
INSERT INTO cited_shared_resources (
 CONF,
YEAR,
resourcename,
number_of_citation,
number_of_shared_authors,
number_of_links)

SELECT 
    SR.CONF, SR.YEAR,SR.resourcename, CR.number AS number_of_citation, SR.number AS number_of_shared_authors, (SR.number*(SR.number-1)) as number_of_links
FROM
    shared_resources SR,
    cited_resources CR
WHERE
    SR.resourcename = CR.resourcename and SR.CONF = CR.CONF and SR.YEAR = CR.YEAR 
    order by 2 asc, 4 desc, 5 desc;

-- fill r2rva --
INSERT INTO r2rva (CONF_1, YEAR_1,passcode_1, resourcename_1,resourcetype_1,
  `firstname` ,
  `lastname` ,
  `author` ,
  `email_1` ,
  `affiliation_1` ,
  `country_1` ,
`email_2`,
  `affiliation_2`,
  `country_2` ,  
  `CONF_2` ,
  `YEAR_2` ,
  `passcode_2` ,
`resourcename_2` ,
  `resourcetype_2`)
  SELECT DISTINCT
    RA1.CONF,
    RA1.YEAR,
    RA1.passcode,
    TRIM(LOWER(REPLACE(RA1.name,'\n',' '))) AS resourcename_1,
    RA1.type AS type_1,
    RA1.firstname,
    RA1.lastname,
    CONCAT(REPLACE(RA1.firstname,'\n',' '),' ', REPLACE(RA1.lastname,'\n',' ')) AS author,
    RA1.email,
    RA1.affiliation,
    RA1.country,
    RA2.email,
    RA2.affiliation,
    RA2.country,
    RA2.CONF,
    RA2.YEAR,
    RA2.passcode,
    TRIM(LOWER(REPLACE(RA2.name,'\n',' '))) AS resourcename_2,
    RA2.type AS type_2
FROM
    resources_authors RA1,
    resources_authors RA2
WHERE
    RA1.firstname = RA2.firstname
        AND RA1.lastname = RA2.lastname
        
        AND RA1.name <> RA2.name

-- files --


-- create distinct authors distinct_authors_<YEAR>.csv args[1]
SELECT distinct replace(firstname_1,'\n',' '), replace(lastname_1,'\n',' '), 
concat(replace(firstname_1,'\n',' '),'_', replace(lastname_1,'\n',' ')) as wikiname, 
lower(affiliation_1) as affiliation FROM a2avr  where YEAR_1='2010' 
UNION 
SELECT distinct replace(firstname_2,'\n',' '), replace(lastname_2,'\n',' '), 
concat(replace(firstname_2,'\n',' '),'_', replace(lastname_2,'\n',' ')) as wikiname, 
lower(affiliation_2) FROM a2avr  where YEAR_2='2010' order by affiliation;

-- create file affiliation_normalized_<YEAR>.csv -- args[2]
-- add a where with year if needed --
SELECT DISTINCT
    affiliation,
    norm_affiliation
	
FROM affiliations
WHERE YEAR ='2010'
;

-- create size resources size_affiliation_<YEAR>_all.csv args[3]
SELECT 
COUNTRY_1,

lower(affiliation_1) as affiliation,
concat(replace(firstname_1,'\n',' '),'_', replace(lastname_1,'\n',' ')) as fullname,
count(*)
FROM demomap_db.a2avr WHERE YEAR_1='2010' group by lower(replace(affiliation_1, '\n', ' ')) ,concat(replace(firstname_1,'\n',' '),'_', replace(lastname_1,'\n',' ')) 
UNION
SELECT 
COUNTRY_2,
lower(affiliation_2) as affiliation,
concat(replace(firstname_2,'\n',' '),'_', replace(lastname_2,'\n',' ')) as fullname,
count(*)
FROM demomap_db.a2avr WHERE YEAR_2='2010' group by lower(replace(affiliation_2, '\n', ' ')) ,concat(replace(firstname_2,'\n',' '),'_', replace(lastname_2,'\n',' ')) 
order by 4 desc;

-- fill distinct_resources_lrec2010.csv args[4] --
SELECT distinct
lower(replace(A.resourcename,'\n',' ')) as resname
FROM
    a2avr A,
    affiliations AF1,
    affiliations AF2
WHERE
    YEAR_1 = '2010' AND YEAR_1 = YEAR_2 and CONF_1=CONF_2
        AND LOWER(A.affiliation_1) = AF1.affiliation
        AND A.YEAR_1 = AF1.year
        AND LOWER(A.affiliation_2) = AF2.affiliation
        AND A.YEAR_2 = AF2.year order by 1;

-- with filter
SELECT distinct
lower(replace(A.resourcename,'\n',' ')) as resname
FROM
    a2avr A,cited_shared_resources C,
    affiliations AF1,
    affiliations AF2
WHERE
    YEAR_1 = '2010' AND YEAR_1 = YEAR_2 and CONF_1=CONF_2
        AND LOWER(A.affiliation_1) = AF1.affiliation
        AND A.YEAR_1 = AF1.year
        AND LOWER(A.affiliation_2) = AF2.affiliation
        AND A.YEAR_2 = AF2.year
and C.resourcename=lower(A.resourcename) order by 1;

-- fill distinct_a2avr_lrec2010.csv  args[5]

SELECT 
concat(replace(A.firstname_1,'\n',' '),'_', replace(A.lastname_1,'\n',' ')) as wikiname_1, 
AF1.norm_affiliation as affi_1,
lower(replace(A.resourcename,'\n',' ')) as resname,
concat(replace(A.firstname_2,'\n',' '),'_', replace(A.lastname_2,'\n',' ')) as wikiname_2, AF2.norm_affiliation as affi2
FROM
    a2avr A,
    affiliations AF1,
    affiliations AF2
WHERE
    YEAR_1 = '2010' AND YEAR_1 = YEAR_2 and CONF_1=CONF_2
        AND LOWER(A.affiliation_1) = AF1.affiliation
        AND A.YEAR_1 = AF1.year
        AND LOWER(A.affiliation_2) = AF2.affiliation
        AND A.YEAR_2 = AF2.year;
-- with filter 
SELECT 
concat(replace(A.firstname_1,'\n',' '),'_', replace(A.lastname_1,'\n',' ')) as wikiname_1, 
AF1.norm_affiliation as affi_1,
lower(replace(A.resourcename,'\n',' ')) as resname,
concat(replace(A.firstname_2,'\n',' '),'_', replace(A.lastname_2,'\n',' ')) as wikiname_2, AF2.norm_affiliation as affi2
FROM
    a2avr A,cited_shared_resources C,
    affiliations AF1,
    affiliations AF2
WHERE
    YEAR_1 = '2010' AND YEAR_1 = YEAR_2 and CONF_1=CONF_2
        AND LOWER(A.affiliation_1) = AF1.affiliation
        AND A.YEAR_1 = AF1.year
        AND LOWER(A.affiliation_2) = AF2.affiliation
        AND A.YEAR_2 = AF2.year
and C.resourcename=lower(A.resourcename);

-- shared args[6] shared_resources_2010.csv
SELECT distinct
lower(replace(A.resourcename,'\n',' ')) as resname, CONF_1
FROM
    a2avr A,
    affiliations AF1,
    affiliations AF2
WHERE
    YEAR_1 = '2010' AND YEAR_1 = YEAR_2 and CONF_1=CONF_2
        AND LOWER(A.affiliation_1) = AF1.affiliation
        AND A.YEAR_1 = AF1.year
        AND LOWER(A.affiliation_2) = AF2.affiliation
        AND A.YEAR_2 = AF2.year order by 1;

-- load data for sample alternative
-- lOAD DATA --

LOAD DATA LOCAL INFILE '/home/riccardo/Dropbox/2015/CLIC2015/mappa/Lexicon_2010.csv' INTO TABLE list_of_resources_by_year
  FIELDS TERMINATED BY '\t' (CONF, YEAR, RESOURCENAME);
LOAD DATA LOCAL INFILE '/home/riccardo/Dropbox/2015/CLIC2015/mappa/Lexicon_2012.csv' INTO TABLE list_of_resources_by_year
  FIELDS TERMINATED BY '\t' (CONF, YEAR, RESOURCENAME);
LOAD DATA LOCAL INFILE '/home/riccardo/Dropbox/2015/CLIC2015/mappa/Lexicon_2014.csv' INTO TABLE list_of_resources_by_year
  FIELDS TERMINATED BY '\t' (CONF, YEAR, RESOURCENAME);

-- fill distinct_a2avr_lrec2010.csv  args[5] alternate --
SELECT 
concat(replace(A.firstname_1,'\n',' '),'_', replace(A.lastname_1,'\n',' ')) as wikiname_1, 
AF1.norm_affiliation as affi_1,
lower(replace(A.resourcename,'\n',' ')) as resname,
concat(replace(A.firstname_2,'\n',' '),'_', replace(A.lastname_2,'\n',' ')) as wikiname_2, AF2.norm_affiliation as affi2
FROM
    a2avr A,list_of_resources_by_year C,
    affiliations AF1,
    affiliations AF2
WHERE
    YEAR_1 = '2010' AND YEAR_1 = YEAR_2 and CONF_1=CONF_2
        AND LOWER(A.affiliation_1) = AF1.affiliation
        AND A.YEAR_1 = AF1.year
        AND LOWER(A.affiliation_2) = AF2.affiliation
        AND A.YEAR_2 = AF2.year
and lower(C.resourcename)=lower(A.resourcename) and C.CONF=A.CONF_1 and C.YEAR=A.YEAR_1; 

-- distinct_resources_lrec2010.csv args[4] alternate
SELECT distinct
lower(replace(A.resourcename,'\n',' ')) as resname
FROM
    a2avr A,list_of_resources_by_year C,
    affiliations AF1,
    affiliations AF2
WHERE
    YEAR_1 = '2010' AND YEAR_1 = YEAR_2 and CONF_1=CONF_2
        AND LOWER(A.affiliation_1) = AF1.affiliation
        AND A.YEAR_1 = AF1.year
        AND LOWER(A.affiliation_2) = AF2.affiliation
        AND A.YEAR_2 = AF2.year
and lower(C.resourcename)=lower(A.resourcename) and C.CONF=A.CONF_1 and C.YEAR=A.YEAR_1; 

/* exec flag=args[0] = m
ifile=args[1] = distinct_autori_it.csv -> distinct_autori_lrec2010_it.csv
istifile=args[2] = LREC2014_affiliation_normalizzate.csv -> LREC2010_affiliation_normalizzate.csv
afile=args[3] = size_affiliation_all.csv -> size_affiliation_2010_all.csv
rfile=args[4] = distinct_resources_lrec2010.csv -> distinct_resources_2010_all.csv
arfile=args[5] = distinct_a2avr_lrec2010.csv 
sfile=args[6] = shared_resources_2010.csv

RUN 2010 m distinct_authors_2010.csv affiliation_normalized_2010.csv size_affiliation_all.csv distinct_resources_lrec2010.csv distinct_a2avr_lrec2010.csv  shared_resources_2010.csv

RUN 2012 m distinct_authors_2012.csv affiliation_normalized_2012.csv size_affiliation_all.csv distinct_resources_lrec2012.csv distinct_a2avr_lrec2012.csv  shared_resources_2012.csv

RUN 2014 m distinct_authors_2014.csv affiliation_normalized_2014.csv size_affiliation_all.csv distinct_resources_lrec2014.csv distinct_a2avr_lrec2014.csv  shared_resources_2014.csv


m distinct_authors_2014.csv affiliation_normalized_2014.csv size_affiliation_all.csv distinct_resources_lrec2014.csv distinct_a2avr_lrec2014.csv  shared_resources_2014.csv
*/

/* RUN DEMO 
m distinct_authors.csv affiliation_normalized.csv size_affiliation.csv distinct_resources.csv distinct_a2avr.csv shared_resources.csv
*/
/* PER IRENE 
SELECT 
    R.resourceid, R.CONF, R.YEAR,  R.passcode, R.resid, R.type,R.name, P.status, A.authornumber, A.firstname, A.lastname, A.email, 
     case when A.affiliation is not null then replace(A.affiliation,'\n',' ') else 'NULLAFFI' end as affiliation, 
     case when A.COUNTRY is not null then A.COUNTRY else 'NULLC' end as COUNTRY,
     L.lang1, L.lang2,L.lang3,L.lang4,L.lang5,L.langother

FROM
    lremap_authors_norm A,
    lremap_resource_norm R,
    lremap_papers_norm P,
     lremap_resource_lang_norm L
WHERE
    A.resourceid = R.resourceid
        AND P.resourceid = A.resourceid
        AND P.status LIKE 'Accep%'
        AND R.conf LIKE 'LREC%'
        AND A.authornumber <> 'Main Contact' and L.resourceid=R.resourceid
ORDER BY year DESC;

-- null langs
SELECT 
    R.resourceid,
    R.CONF,
    R.YEAR,
    R.passcode,
    R.resid,
    R.type,
    R.name,
    P.status,
    A.authornumber,
    A.firstname,
    A.lastname,
    A.email,
    CASE
        WHEN
            A.affiliation IS NOT NULL
        THEN
            REPLACE(A.affiliation,
                '
                ',
                ' ')
        ELSE 'NULLAFFI'
    END AS affiliation,
    CASE
        WHEN A.COUNTRY IS NOT NULL THEN A.COUNTRY
        ELSE 'NULLC'
    END AS COUNTRY,
    L.lang1,
    L.lang2,
    L.lang3,
    L.lang4,
    L.lang5,
    L.langother
FROM
    lremap_authors_norm A,
    lremap_resource_norm R,
    lremap_papers_norm P LEFT OUTER JOIN 
    lremap_resource_lang_norm L ON ( L.resourceid = P.resourceid )
WHERE
    A.resourceid = R.resourceid
        AND P.resourceid = A.resourceid
        AND P.status LIKE 'Accep%'
        AND R.conf LIKE 'LREC%'
        AND A.authornumber <> 'Main Contact'
         and R.name='AncientGreekWordNet'
ORDER BY year DESC

*/
