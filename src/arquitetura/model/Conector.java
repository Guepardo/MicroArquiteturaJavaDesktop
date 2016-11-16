/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitetura.model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bsine
 */
public class Conector {
   private java.sql.Connection conn = null; 
   private static Conector singleton = null; 
   
   //Configurar a sua conexão com o banco de dados por meio dessas variáveis: 
   private String host     = "localhost"; 
   private String db       = "myping"; 
   private String user     = "root"; 
   private String password = ""; 
   
   public Conector(){
        this.conn = null;
  
       try {
            try {
                Object newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
            }
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
       }
       try {
           conn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+db+"?" +
                   "user="+user+"&password="+password);
       } catch (SQLException ex) {
           Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   public static java.sql.Connection getConnection(){
       if(singleton == null){
           singleton = new Conector(); 
           return singleton.conn; 
       }
       return singleton.conn; 
   }
}
