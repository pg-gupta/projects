package models;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import models.User;



public  class Userdao extends DBOperations
{   
    public void insertData(User user) 
    {
        super.insert(user);       
    }
 
    public void updateData(User user) 
    {
        super.update(user);       
    }
    
    public void deleteData(User user) 
    {
        super.delete(user);
    }
    
    public User getById(int Id) 
    {  
    	int id =Id;
       return (User) super.find(User.class,id);
    }
    
    public List getRecords(User user) 
    {
       return  super.findAll(User.class);
    }
    
    

}
	