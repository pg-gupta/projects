package models;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import models.Medicine;


/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Class defining database methods for the Medicine class
 *
 */
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
	