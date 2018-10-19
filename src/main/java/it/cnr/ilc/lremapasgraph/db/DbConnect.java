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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author riccardo
 */
public class DbConnect {

    public final String host = "localhost";
    public final String user = "mystart";
    public final String passwd = "mystart";
    public final String database = "demomap_db";
    private Connection conn = null;
    

    public Connection db_connect() {
        String connStr = "jdbc:mysql://" + host + "/" + database + "?useUnicode=true&characterEncoding=utf-8&" + "user=" + user + "&password=" + passwd;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            if (conn == null) {
                conn = DriverManager
                        .getConnection(connStr);
                
            }

        } catch (SQLException  sqle) {

            System.exit((-1));

        } catch(ClassNotFoundException e){
            System.exit((-1));
        }
        return conn;
    }
    
    public static int execStm(Connection conn, String stm){
        Statement statement = null;
        try {
            
            statement = conn.createStatement();
            // Result set get the result of the SQL query
            statement
                    .executeQuery(stm);
            

        } catch (Exception e) {
            return 1;
           
        }
    
        return 0;
    }
    
    public static ResultSet execStmAndGetResultSet(Connection conn, String stm){
        Statement statement = null;
        ResultSet res=null;
        try {
            
            statement = conn.createStatement();
            // Result set get the result of the SQL query
            res=statement
                    .executeQuery(stm);
            

        } catch (Exception e) {
            return null;
           
        }
    
        return res;
    }
    

}
