package models;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import models.Patient;


/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Class defining database methods for the Patient class
 *
 */
public  class Patientdao extends DBOperations
{   
    public void insertData(Patient patient) 
    {
        super.insert(patient);       
    }
 
    public void updateData(Patient patient) 
    {
        super.update(patient);       
    }
    
    public void deleteData(Patient patient) 
    {
        super.delete(patient);
    }
    
    public Patient getById(int Id) 
    {  
    	int id =Id;
       return (Patient) super.find(Patient.class,id);
    }
    
    public List getRecords(Patient doc) 
    {
       return  super.findAll(Patient.class);
    }
    
    

}
	