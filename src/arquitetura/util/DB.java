/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitetura.util;

import arquitetura.model.Conector;
import java.sql.ResultSet;
import java.sql.Connection; 
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author bsine
 */
public class DB {
    public static ResultSet exec( String query){
        Connection co = Conector.getConnection(); 
        
        Statement statement = null;
        try {
            statement = co.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet result = null;
        try {
            result = statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result; 
    }
}
