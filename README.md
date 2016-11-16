# MicroArquiteturaJavaDesktop


### Organização do projeto
```sh
C:.
│   build.xml
│   manifest.mf
│   README.md
│
├───build
│   └───classes
│       │   .netbeans_automatic_build
│       │   .netbeans_update_resources
│       │
│       └───arquitetura
│           │   Main.class
│           │
│           ├───annotation
│           │       Column.class
│           │       Table.class
│           │
│           ├───controller
│           ├───model
│           │       BaseModel.class
│           │       Conector.class
│           │       Person.class
│           │       Site.class
│           │
│           ├───util
│           │       DB.class
│           │
│           └───view
├───nbproject
│   │   build-impl.xml
│   │   genfiles.properties
│   │   project.properties
│   │   project.xml
│   │
│   └───private
│           config.properties
│           private.properties
│
├───src
│   └───arquitetura
│       │   Main.java
│       │
│       ├───annotation
│       │       Column.java
│       │       Table.java
│       │
│       ├───controller
│       ├───model
│       │       BaseModel.java
│       │       Conector.java
│       │       Person.java
│       │       Site.java
│       │
│       ├───util
│       │       DB.java
│       │
│       └───view
└───test
```


### Criando um modelo 
```java
package arquitetura.model;

import arquitetura.annotation.Column; 
import arquitetura.annotation.Table;

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
    
    public Person(){
        super(); 
    }
    

    //Getters and Setters
    @Override
    public Integer getId() {
       return this.id; 
    }

    @Override
    public void setId(int id ) {
        this.id = id; 
    }  
}

```
@Table e @Column são anotações essencias para que o mapeador funcione. Os valores dessas anotações devem compreender o nome real na tabela do banco de dados. 

### INSERT

```java
Person p = new Person(); 
      
p.setName("Allyson");
p.setTelegramId("123456789"); 
p.setCreatedAt("2016-10-19 10:24:35");
p.setUpdatedAt("2016-10-19 10:24:35");
      
p.save(); 

output: INSERT INTO person (name, created_at, updated_at, telegram_id) VALUES ('Allyson', '2016-10-19 10:24:35', '2016-10-19 10:24:35', '123456789')

``` 

### FIND BY ID

```java


``` 

### UPDATE

```java


``` 

### DELETE

```java


``` 

### ALL

```java


``` 
