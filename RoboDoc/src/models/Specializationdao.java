package models;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import models.Specialization;

/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Class defining database methods for the Specialization class
 *
 */

public  class Specializationdao extends DBOperations
{   
    public void insertData(Specialization spec) 
    {
        super.insert(spec);       
    }
 
    public void updateData(Specialization spec) 
    {
        super.update(spec);       
    }
    
    public void deleteData(Specialization spec) 
    {
        super.delete(spec);
    }
    
    public Specialization getById(int Id) 
    {  
    	int id =Id;
       return (Specialization) super.find(Specialization.class,id);
    }
    
    public List getRecords(Specialization spec) 
    {
       return  super.findAll(Specialization.class);
    }
    
    

}
	