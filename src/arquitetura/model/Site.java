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
@Table(name = "site")
public class Site extends BaseModel<Site>{

    @Column(name = "label")
    protected String label = ""; 
    
    @Column(name = "url")
    protected String url   = ""; 

    @Column(name = "last_seen")
    protected String lastSeen = ""; 
    
    @Column(name = "created_at")
    protected String createdAt = ""; 
    
    @Column(name = "updated_at")
    protected String updatedAt = "";
    
    @Column(name = "priority_id")
    protected Integer priorityId = 0; 
    
    @Column(name = "id")
    protected Integer id = -1; 
    
    public Site(){
        super(); 
    }
    
    @Override
    public Integer getId() {
       return this.id; 
    }

    @Override
    public void setId(int id) {
      this.id = id; 
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
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

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }
    
    
}
