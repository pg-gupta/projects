package models;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import models.Person;


/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Class defining database methods for the Person class
 *
 */
public  class Persondao extends DBOperations
{   
    public void insertData(Person person) 
    {
        super.insert(person);       
    }
 
    public void updateData(Person person) 
    {
        super.update(person);       
    }
    
    public void deleteData(Person person) 
    {
        super.delete(person);
    }
    
    public Person getById(int Id) 
    {  
    	int id =Id;
       return (Person) super.find(Person.class,id);
    }
    
    public List getRecords(Person person) 
    {
       return  super.findAll(Person.class);
    }
    
    

}
	