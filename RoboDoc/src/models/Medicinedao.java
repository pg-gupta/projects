package models;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import models.Medicine;



public  class Medicinedao extends DBOperations
{   
    public void insertData(Medicine med) 
    {
        super.insert(med);       
    }
 
    public void updateData(Medicine med) 
    {
        super.update(med);       
    }
    
    public void deleteData(Medicine med) 
    {
        super.delete(med);
    }
    
    public Medicine getById(int Id) 
    {  
    	int id =Id;
       return (Medicine) super.find(Medicine.class,id);
    }
    
    public List getRecords(Medicine med) 
    {
       return  super.findAll(Medicine.class);
    }
    
    

}
	