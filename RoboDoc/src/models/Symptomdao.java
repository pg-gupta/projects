package models;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import models.Symptom;



public  class Symptomdao extends DBOperations
{   
    public void insertData(Symptom symptom) 
    {
        super.insert(symptom);       
    }
 
    public void updateData(Symptom symptom) 
    {
        super.update(symptom);       
    }
    
    public void deleteData(Symptom symptom) 
    {
        super.delete(symptom);
    }
    
    public Symptom getById(int Id) 
    {  
        int id =Id;
       return (Symptom) super.find(Symptom.class,id);
    }
    
    public List getRecords(Symptom doc) 
    {
       return  super.findAll(Symptom.class);
    }
    
    

}
	