package models;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import models.Disease;



public  class Diseasedao extends DBOperations
{   
    public void insertData(Disease disease) 
    {
        super.insert(disease);       
    }
 
    public void updateData(Disease disease) 
    {
        super.update(disease);       
    }
    
    public void deleteData(Disease disease) 
    {
        super.delete(disease);
    }
    
    public Disease getById(int id) 
    {  
       
       return (Disease) super.find(Disease.class,id);
    }
    
    public List getRecords(Disease doc) 
    {
       return  super.findAll(Disease.class);
    }
    
    

}
	