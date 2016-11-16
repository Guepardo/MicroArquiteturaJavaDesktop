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
        Site s = new Site(); 
        //Select * from site where id = 1; Site.findById(int)
        System.out.println(s.findById(1).getLabel()); 
        
        //Select * from site; Site.all()
        for( Site temp: s.all()){
            System.out.println(temp.getId()+" "+ temp.getUrl());
        }
        
        Site a = new Site();
        
        a.findById(3); 
        a.setLabel("Label Atualizado");
        
        //Gera query Update de acordo com o contexto do objeto
        a.update(); 
        
        
        //Executa qualquer query e retorna um ResultSet.
         ResultSet set = DB.exec("SELECT COUNT(*) AS qtd FROM site"); 
      
        while(set.next()){
            System.out.println(set.getString("qtd"));
        }
    
    }
    
}
