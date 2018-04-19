
package models;

import java.util.List;
import java.io.Serializable;

public interface DBActions 
{
	public void insert(Object obj);
	public void update(Object obj);
	public void delete(Object obj);
	//public Object Find (Object obj);
    //public List<T> findAll(Object obj);
   
}

