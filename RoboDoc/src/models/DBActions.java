
package models;
/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Interface declaring database operations 
 *
 */

public interface DBActions 
{
	public void insert(Object obj);
	public void update(Object obj);
	public void delete(Object obj);
	//public Object Find (Object obj);
    //public List<T> findAll(Object obj);
   
}

