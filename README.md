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

### Configurando a Conexão com o banco: 
A conexão com o banco de dados é configurável através da classe Conector.java dentro de src/arquitetura/model/Conector.java
Abra o arquivo e altere estas linhas para as configurações do seu banco de dados: 

```java
//Configurar a sua conexão com o banco de dados por meio dessas variáveis: 
   private String host     = "localhost"; 
   private String db       = "myping"; 
   private String user     = "root"; 
   private String password = ""; 
   
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

### Métodos Helpers para os Models
### INSERT

```java
Person p = new Person(); 
      
p.setName("Allyson");
p.setTelegramId("123456789"); 
p.setCreatedAt("2016-10-19 10:24:35");
p.setUpdatedAt("2016-10-19 10:24:35");
      
p.save(); 

output: 
INSERT INTO person (name, created_at, updated_at, telegram_id) VALUES ('Allyson', '2016-10-19 10:24:35', '2016-10-19 10:24:35', '123456789')

``` 

### FIND BY ID

```java
Person p = new Person(); 
      
p.findById(1); 
      
p.getName(); //É isso aí, só isso. nome da pessoa já está aqui. 

output: 
SELECT * FROM person WHERE id = 1

``` 

### UPDATE

```java
Person p = new Person(); 
      
p.findById(1); 
      
p.setName("Marcos de Paula"); 
      
p.update(); 

output: 
SELECT * FROM person WHERE id = 1; 
UPDATE person SET name = 'Marcos de Paula', created_at = '2016-10-19 10:18:49.0', updated_at = '2016-10-19 10:18:49.0', telegram_id = '14124124' WHERE id = 1; 
``` 

### DELETE
```java
Person p = new Person(); 
      
p.findById(1); 
      
p.delete(); 

output: 
SELECT * FROM person WHERE id = 1
DELETE FROM person WHERE id = 1

``` 

### ALL

```java
Person p = new Person(); 
      
ArrayList<Person> array = p.all(); 
     
for(Person temp: array)
    System.out.println(temp.getId()); 

output: 
SELECT * FROM person
1
2
3
4
5
6
[..]

``` 

### WHERE
```java
Site site = new Site(); 
      
ArrayList<Site> array = site.where(" url LIKE '%.php%' or url LIKE '%google%' "); 
        
    for(Site s: array)
       System.out.println("label: "+s.getLabel()+" |||  url: "+s.getUrl()); 
```
### Classe Helper DB


```java
ResultSet set =  DB.exec("SELECT MAX(id) AS maior_id FROM person"); 
    
while(set.next()){
   System.out.println("Maior id "+ set.getString("maior_id"));
}

output: 
Maior id 30
``` 
Essa classe tem como finalidae suprir as lacunas das interações limitadas providas pelo BaseModel. 
