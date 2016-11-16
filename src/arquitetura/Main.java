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
import java.util.ArrayList;


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
        Site site = new Site(); 
      
        ArrayList<Site> array = site.where(" url LIKE '%.php%' or url LIKE '%google%' "); 
        
        for(Site s: array)
            System.out.println("label: "+s.getLabel()+" |||  url: "+s.getUrl()); 
    
    }
    
}
