/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitetura;

import arquitetura.model.Site;
import arquitetura.util.DB;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author bsine
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
      ResultSet set = DB.exec("SELECT COUNT(*) AS qtd FROM site"); 
      
      while(set.next()){
          System.out.println(set.getString("qtd"));
      }
    }
    
}
