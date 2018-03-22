package models;

import java.util.List;

/**
 * This is a generic class which implements DBActions interface
 * It will define all the CRUD operations
 */
/**
 * 
 * @author pooja gupta & yeshwanthi durairaj
 * Date: 03/21/2018
 * File: DBOperations.java
 * Final Project
 * @param <T> Any class in this package
 */
public class DBOperations<T> implements DBActions<T> {

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean postData(T obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateData(T obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteData(T obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
