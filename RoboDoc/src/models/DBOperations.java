package models;
import org.hibernate.Transaction;


import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.query.Query;
import org.hibernate.*;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;



public abstract class DBOperations implements DBActions {
	
	@SuppressWarnings({"unchecked", "deprecation"})
	org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");

    //getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);

    
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
        catch(ConstraintViolationException  e)
    	{                                                       
        	if (trans != null)
            trans.rollback();
            e.printStackTrace();
     
        } 
    }
   /**
    * .
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
        	if (trans != null)
        		trans.rollback();
                e.printStackTrace();
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
        	if (trans != null)
        		trans.rollback();
                e.printStackTrace();
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
                e.printStackTrace();

	        }   
            return object;
    }
    
    /**
     * 
     * @param clazz
     * @param id
     * @return
     */
    public int findMaxid(Class clazz) 
    {
        int objects = 0;
    
    	    try 
            {
            DBConnect(clazz);
    	               
            Query query = sess.createQuery("select max(id) from " + clazz.getName());
            objects =query.getFirstResult();
            trans.commit();
            }
            catch (HibernateException e)
	        {
                e.printStackTrace();

	        }   
            return objects;
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
        	if (trans != null)
        		trans.rollback();
                e.printStackTrace();        }
              
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
    	catch(HibernateException e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
    	}
    }
}
