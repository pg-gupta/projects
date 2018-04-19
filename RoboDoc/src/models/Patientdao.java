package models;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import models.Patient;



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
	