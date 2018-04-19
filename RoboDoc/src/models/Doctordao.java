package models;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import models.Doctor;



public  class Doctordao extends DBOperations
{   
    public void insertData(Doctor doc) 
    {
        super.insert(doc);       
    }
 
    public void updateData(Doctor doc) 
    {
        super.update(doc);       
    }
    
    public void deleteData(Doctor doc) 
    {
        super.delete(doc);
    }
    
    public Doctor getById(int Id) 
    {  
    	int id =Id;
       return (Doctor) super.find(Doctor.class,id);
    }
    
    public List getRecords(Doctor doc) 
    {
       return  super.findAll(Doctor.class);
    }
    
    

}
	