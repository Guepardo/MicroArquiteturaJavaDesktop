/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitetura.model;

import arquitetura.annotation.Column; 
import arquitetura.annotation.Table;


/**
 *
 * @author bsine
 */
@Table(name = "person")
public class Person extends BaseModel<Person> {
    @Column(name = "name")
    protected String name; 
    
    @Column(name = "created_at")
    protected String createdAt = ""; 
    
    @Column(name = "updated_at")
    protected String updatedAt = ""; 
    
    @Column(name = "telegram_id")
    protected String telegramId = ""; 
    
    @Column(name = "id")
    protected Integer id  = -1; 
    
    //Ao menos este construtor deve existir em toda classe que extende BaseModel
    public Person(){
        super(); 
    }
    

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public Integer getId() {
       return this.id; 
    }

    @Override
    public void setId(int id ) {
        this.id = id; 
    }  
}
