package models;

import java.util.List;
import models.User;
/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Class defining database methods for the User class
 *
 */
public class Userdao extends DBOperations {
	public void insertData(User user) {
		super.insert(user);
	}

	public void updateData(User user) {
		super.update(user);
	}

	public void deleteData(User user) {
		super.delete(user);
	}

	public User getById(int Id) {
		int id = Id;
		return (User) super.find(User.class, id);
	}

	public User getByName(String name) {

		return (User) super.findByName(User.class, name);
	}

	public List getRecords(User user) {
		return super.findAll(User.class);
	}

}
