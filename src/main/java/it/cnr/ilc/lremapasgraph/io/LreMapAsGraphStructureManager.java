/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lremapasgraph.io;

import it.cnr.ilc.lremapasgraph.db.Vars;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Riccardo Del Gratta &lt;riccardo.delgratta@ilc.cnr.it&gt;
 */
public class LreMapAsGraphStructureManager {

    /**
     * A map submitted to normalized. submitted as key, norm as value
     */
    private HashMap<String, String> affiliation2normaffiliation = new HashMap<String, String>();

    /**
     * A map submitted to normalized. author as key, norm as value
     */
    private HashMap<String, String> author2normaffiliation = new HashMap<String, String>();

    /**
     * A map normalized to an index used for visualization. norm as key, index
     * as value
     */
    private HashMap<String, Integer> normaffiliation2Idx = new HashMap<String, Integer>();

    /**
     * A set of normalized affiliation
     */
    private Set<String> norm = new HashSet<String>();

    /**
     * A set of submitted affiliation
     */
    private Set<String> affi = new HashSet<String>();

    /**
     * A list of distinct resources
     */
    private List<String> resources = new ArrayList<String>();

    /**
     * A list of distinct resources
     */
    private List<String> auth = new ArrayList<String>();

    /**
     * A map normalized to an index used for visualization. resource as key,
     * index as value
     */
    private HashMap<String, Integer> resource2Idx = new HashMap<String, Integer>();

    /**
     * A map normalized to an index used for visualization. author as key, index
     * as value
     */
    private HashMap<String, Integer> author2Idx = new HashMap<String, Integer>();

    /**
     * A map normalized to an index used for visualization. resource as key,
     * year as value
     */
    private HashMap<String, String> resources2year = new HashMap<String, String>();

    /**
     * Create the association between affiliation as submitted and its
     * normalized value
     *
     * @param affifile_arg2
     * @return
     */
    public String readIstiFileAndPrepareStructure(List<String> affifile_arg2) {
        int i = 0;
        String result = Vars.__OK__;
        HashMap<String, String> temp_affiliation2normaffiliation = new HashMap<String, String>();
        HashMap<String, Integer> temp_normaffiliation2Idx = new HashMap<String, Integer>();
        Set<String> temp_norm = new HashSet<String>();
        Set<String> temp_affi = new HashSet<String>();
        String l0 = "", l1 = "";
        try {
            for (String s : affifile_arg2) {
                l0 = s.split(Vars.__SEPTAB__)[0].trim(); // submitted 
                l1 = s.split(Vars.__SEPTAB__)[1].trim(); // normalized
                l0 = cleaner(l0);
                l1 = cleaner(l1);

                temp_affiliation2normaffiliation.put(l0, l1);
                temp_norm.add(l1);
                temp_affi.add(l0);
            }

            for (String n : temp_norm) {
                temp_normaffiliation2Idx.put(n, i);
                i++;
            }
        } catch (Exception e) {
            result = Vars.__KO__;
        }
        // set structures
        setAffi(temp_affi);
        setNorm(temp_norm);
        setAffiliation2normaffiliation(temp_affiliation2normaffiliation);
        setNormaffiliation2Idx(temp_normaffiliation2Idx);

        return result;
    }

    public String readResourceAndARFilesAndPrepareStructure(List<String> distinct_res_arg4, List<String> a2avr_arg5, List<String> shared_arg6) {
        int i = 0;
        int j = 0;
        String result = Vars.__OK__;
        HashMap<String, Integer> temp_resource2Idx = new HashMap<String, Integer>();
        HashMap<String, Integer> temp_author2Idx = new HashMap<String, Integer>();
        List<String> temp_resources = new ArrayList<String>();
        List<String> temp_auth = new ArrayList<String>();
        HashMap<String, String> temp_author2normaffiliation = new HashMap<String, String>();
        HashMap<String, String> temp_resource2year = new HashMap<String, String>();

        String l0 = "", l1 = "";
        String a0 = "", a1 = "", a00 = "", a11 = "", affi0 = "", affi1 = "", r = "", year = "";

        try {

            for (String s : distinct_res_arg4) {
                l0 = s.split(Vars.__SEPTAB__)[0].trim(); // submitted 
                l0 = cleaner(l0);

                temp_resource2Idx.put(l0, i);
                temp_resources.add(l0);
                i++;
            }

            for (String s : shared_arg6) {
                r = s.split(Vars.__SEPTAB__)[0].trim(); // submitted 
                r = cleaner(r);

                year = s.split(Vars.__SEPTAB__)[1].trim(); // submitted 

                temp_resource2year.put(r, year);

            }

            for (String s : a2avr_arg5) {
                a0 = s.split(Vars.__SEPTAB__)[0].trim(); // author_1 
                a0 = cleaner(a0);

                affi0 = s.split(Vars.__SEPTAB__)[1].trim(); // affi_1
                affi0 = cleaner(affi0);

                a1 = s.split(Vars.__SEPTAB__)[3].trim(); // author_1 
                a1 = cleaner(a1);

                affi1 = s.split(Vars.__SEPTAB__)[4].trim(); // affi_1
                affi1 = cleaner(affi1);
//            a00=a0+Vars.__SEPHASHTAG__+affi0;
//            a11=a0+Vars.__SEPHASHTAG__+affi1;

                /*
        self.set_dn(DN)
        self.set_auth(auth)
        self.set_auth_2affi(A) 
        print    self.get_auth_2affi() 
                 */
                if (!temp_auth.contains(a0)) {
                    temp_auth.add(a0);
                    temp_author2Idx.put(a0, j);
                    temp_author2normaffiliation.put(a0, affi0);
                    j++;
                }

                if (!temp_auth.contains(a1)) {
                    temp_auth.add(a1);
                    temp_author2Idx.put(a1, j);
                    temp_author2normaffiliation.put(a1, affi1);
                    j++;
                }
            }

        } catch (Exception e) {
            result = Vars.__KO__;
        }
        // set structures
        setResource2Idx(temp_resource2Idx); // self.set_d(D).
        setResources(temp_resources); // self.set_res(res)

        setAuth(temp_auth); //self.set_auth(auth)
        setAuthor2Idx(temp_author2Idx); //self.set_dn(DN)
        setAuthor2normaffiliation(temp_author2normaffiliation); //self.set_auth_2affi(A) 
        setResources2year(temp_resource2year); // S

        return result;
    }
    //distinct_resources.csv distinct_a2avr.csv

    private String cleaner(String str2clean) {
        String ret = "";
        str2clean = str2clean.replace("\\", "");

        str2clean = str2clean.replace(",", "");
        str2clean = str2clean.replace("/", " ");
        str2clean = str2clean.replace(" ", "_");
        ret = str2clean;
        return ret;
    }

    /**
     * @return the affiliation2normaffiliation
     */
    public HashMap<String, String> getAffiliation2normaffiliation() {
        return affiliation2normaffiliation;
    }

    /**
     * @param affiliation2normaffiliation the affiliation2normaffiliation to set
     */
    public void setAffiliation2normaffiliation(HashMap<String, String> affiliation2normaffiliation) {
        this.affiliation2normaffiliation = affiliation2normaffiliation;
    }

    /**
     * @return the normaffiliation2Idx
     */
    public HashMap<String, Integer> getNormaffiliation2Idx() {
        return normaffiliation2Idx;
    }

    /**
     * @param normaffiliation2Idx the normaffiliation2Idx to set
     */
    public void setNormaffiliation2Idx(HashMap<String, Integer> normaffiliation2Idx) {
        this.normaffiliation2Idx = normaffiliation2Idx;
    }

    /**
     * @return the norm
     */
    public Set<String> getNorm() {
        return norm;
    }

    /**
     * @param norm the norm to set
     */
    public void setNorm(Set<String> norm) {
        this.norm = norm;
    }

    /**
     * @return the affi
     */
    public Set<String> getAffi() {
        return affi;
    }

    /**
     * @param affi the affi to set
     */
    public void setAffi(Set<String> affi) {
        this.affi = affi;
    }

    /**
     * @return the resources
     */
    public List<String> getResources() {
        return resources;
    }

    /**
     * @param resources the resources to set
     */
    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    /**
     * @return the resource2Idx
     */
    public HashMap<String, Integer> getResource2Idx() {
        return resource2Idx;
    }

    /**
     * @param resource2Idx the resource2Idx to set
     */
    public void setResource2Idx(HashMap<String, Integer> resource2Idx) {
        this.resource2Idx = resource2Idx;
    }

    /**
     * @return the auth
     */
    public List<String> getAuth() {
        return auth;
    }

    /**
     * @param auth the auth to set
     */
    public void setAuth(List<String> auth) {
        this.auth = auth;
    }

    /**
     * @return the author2normaffiliation
     */
    public HashMap<String, String> getAuthor2normaffiliation() {
        return author2normaffiliation;
    }

    /**
     * @param author2normaffiliation the author2normaffiliation to set
     */
    public void setAuthor2normaffiliation(HashMap<String, String> author2normaffiliation) {
        this.author2normaffiliation = author2normaffiliation;
    }

    /**
     * @return the author2Idx
     */
    public HashMap<String, Integer> getAuthor2Idx() {
        return author2Idx;
    }

    /**
     * @param author2Idx the author2Idx to set
     */
    public void setAuthor2Idx(HashMap<String, Integer> author2Idx) {
        this.author2Idx = author2Idx;
    }

    /**
     * @return the temp_resource2year
     */
    public HashMap<String, String> getResources2year() {
        return resources2year;
    }

    /**
     * @param resources2year the temp_resource2year to set
     */
    public void setResources2year(HashMap<String, String> resources2year) {
        this.resources2year = resources2year;
    }

}
