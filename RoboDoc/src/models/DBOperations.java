package models;
import org.hibernate.Transaction;


import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public abstract class DBOperations implements DBActions {
    public Session sess;
    public Transaction trans;
    public SessionFactory sf;
    public void insert(Object obj) 
    {     
    	try
    	{
        DBConnect(obj);   	
		sess.save(obj);
        trans.commit();
    	}
    	catch (HibernateException e)
    	{
    		
    	}
              
    }
    /**
     * 
     */
    public void update(Object obj) 
    {
        try
        {
        DBConnect(obj);   	
		sess.update(obj);
        trans.commit();
        }
        catch (HibernateException e)
    	{
    		
    	}
              
    }
    /**
     * 
     */
    public void delete(Object obj) 
    {
        try
        {
        DBConnect(obj);   	
		sess.delete(obj);
        trans.commit();
        }
        catch (HibernateException e)
    	{
    		
    	}
    	
    }
    /**
     * 
     * @param clazz
     * @param id
     * @return
     */
    public Object find(Class clazz, int id) 
    {
    	    Object object=null;      
    
    	    try 
            {
            DBConnect(clazz);
    	               
            object = sess.get(clazz,id);
            trans.commit();
            }
            catch (HibernateException e)
	        {
		
	        }   
            return object;
    }
    /**
     * 
     * @param clazz
     * @return
     */
    public List findAll(Class clazz) 
    {
        List objects = null;
        try
        {
            DBConnect(clazz);
            Query query = sess.createQuery("from " + clazz.getName());
            objects = query.list();
            trans.commit();
        }
        catch (HibernateException e)
        {
        	//RuntimeException rn= new RuntimeException();
        }
              
        return objects;
    }
      
    /**
     * 
     * @param obj
     */
    public void DBConnect(Object obj) 
    {   
    	try {
    	Configuration con = new Configuration().configure().addAnnotatedClass(Object.class	);
	    SessionFactory sf = con.buildSessionFactory();
        sess = sf.openSession();
        trans = sess.beginTransaction();
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
    }
}
