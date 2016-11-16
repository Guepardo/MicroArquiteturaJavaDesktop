/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitetura.model;

import arquitetura.annotation.Column;
import arquitetura.annotation.Table;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bsine
 */
public abstract class BaseModel<T>{
    private Connection co = null; 
    
    public BaseModel(){
        co = Conector.getConnection(); 
    }
    
    public abstract Integer getId(); 
    public abstract void    setId(int id); 
    
    @SuppressWarnings("UnusedAssignment")
    public boolean save(){
        try {
            Field[] f  = getFieldsInstance();
            
            String query = "INSERT INTO " + getNameTableInstance()+ " (";
            for(Field a: f){
                String name = getNameFieldByAnnotation(a);
                if(name.equals("id"))continue; 
                query += name +", ";
            }
            
            query = query.substring(0, query.lastIndexOf(","))+") VALUES (";
            
            for(Field a: f){
                String name = getNameFieldByAnnotation(a);
                if(name.equals("id"))continue;
                query += correctSintaxeValue(a)+", ";
            }
            
            query = query.substring(0, query.lastIndexOf(","))+ ")";
            
            System.out.println(query); 
            PreparedStatement pstmt = co.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            keys.next();
            this.setId(keys.getInt(1));
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean update(){
         Field[] f  = getFieldsInstance(); 
        
        String query = "UPDATE " + getNameTableInstance()+ " SET "; 
        for(Field a: f){
            String name = getNameFieldByAnnotation(a); 
            String value = correctSintaxeValue(a); 
            
            if(name.equals("id"))continue; 
            
            query += name +" = " + value +", "; 
        }
        
        query = query.substring(0, query.lastIndexOf(","))+" WHERE id = " + this.getId(); 
        
        return executeQuery(query);
    }
    
    public boolean delete(){
        String id = this.getId().toString(); 
        String query = "DELETE FROM "+ getNameTableInstance()+" WHERE id = "+ id; 
        return executeQuery(query); 
    } 

    public T findById(int id){
        String query = "SELECT * FROM " + getNameTableInstance() + " WHERE id = "+ id;  
        ResultSet result = executeQueryResultSet(query); 
        
        try {
            while(result.next()){
                Field[] f = getFieldsInstance(); 
                
                for(Field temp: f)
                   setFieldValue(temp, result); 
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (T) this;
    }
 
   public ArrayList<T> all(){
        String query = "SELECT * FROM " + getNameTableInstance(); 
        ResultSet result =  executeQueryResultSet(query); 
       
        ArrayList<T> array = new ArrayList(); 
        
        try {
            while(result.next()){
               T temp = null;
                try {
                    temp = (T) this.getClass().newInstance();
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                 Field[] f = getFieldsInstance(); 
                
                for(Field field: f)
                   setFieldValue(field, result, temp); 
                
                array.add(temp); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return array; 
    }
   
   public ArrayList<T> where(String where ){
        String query = "SELECT * FROM " + getNameTableInstance()+" WHERE"+ where; 
        ResultSet result =  executeQueryResultSet(query); 
       
        ArrayList<T> array = new ArrayList(); 
        
        try {
            while(result.next()){
               T temp = null;
                try {
                    temp = (T) this.getClass().newInstance();
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                 Field[] f = getFieldsInstance(); 
                
                for(Field field: f)
                   setFieldValue(field, result, temp); 
                
                array.add(temp); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return array; 
    }
   
   private void setFieldValue(Field f, ResultSet result) throws SQLException{
       String p = f.getType().toString(); 
       String name = getNameFieldByAnnotation(f); 
       
       switch(p){
           case "class java.lang.String": 
                {
                    try {
                        f.set(this, result.getString(name));
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
           break;  
           case "class java.lang.Integer": 
                {
                    try {
                        f.set(this, result.getInt(name));
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
           break; 
       }
   }
   
   private void setFieldValue(Field f, ResultSet result, T object){
       String p = f.getType().toString(); 
       String name = getNameFieldByAnnotation(f); 
       
       switch(p){
           case "class java.lang.String": 
                {
                    try {
                        f.set(object, result.getString(name));
                    } catch (IllegalArgumentException | IllegalAccessException | SQLException ex) {
                        Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
           break;  
           case "class java.lang.Integer": 
                {
                    try {
                        f.set(object, result.getInt(name));
                    } catch (IllegalArgumentException | IllegalAccessException | SQLException ex) {
                        Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
           break; 
       } 
   }
   
    public String correctSintaxeValue(Field f){
        try {
            switch(f.getType().toString()){
                case "class java.lang.String":
                    return "'"+f.get(this).toString()+"'";                
                case "class java.lang.Integer": 
                    return f.get(this).toString();
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
 
    private String getNameTableInstance(){
        return this.getClass().getAnnotation(Table.class).name();  
    }
    
    private Field[] getFieldsInstance(){
        return this.getClass().getDeclaredFields(); 
    }
    
    private String getNameFieldByAnnotation(Field f){
        return f.getAnnotation(Column.class).name(); 
    }
    
    public ResultSet executeQueryResultSet(String query ){
        System.out.println(query); 
        ResultSet result = null; 
        try { 
            Statement statement = co.createStatement();
            result = statement.executeQuery(query); 
        } catch (SQLException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean executeQuery(String query ){
        System.out.println(query); 
        ResultSet result = null; 
        try { 
            Statement statement = co.createStatement();
            return statement.execute(query); 
        } catch (SQLException ex) {
            Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
