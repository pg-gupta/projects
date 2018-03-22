package models;

import java.util.*;

/**
 * This is a generic interface where all the database operations are declared.
 * This can be utilized by any class who wants to perform CRUD operations on its class
 */
/**
 * 
 * @author pooja gupta & yeshwanthi durairaj 
 * Date: 03/21/2018 
 * File: DBActions.java 
 * Final Project
 *
 */
public interface DBActions<T> {

	List<T> getAll();

	Boolean postData(T obj);

	Boolean updateData(T obj);

	Boolean deleteData(T obj);

}
