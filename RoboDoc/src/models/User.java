package models;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Class describing database entity User
 *
 */
@ Entity(name="robodocuser")

public class User {
	@Id
	int uid;
	String uname;
	String upassword;
	Boolean isadmin;
	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}
	/**
	 * @return the uname
	 */
	public String getUname() {
		return uname;
	}
	/**
	 * @param uname the uname to set
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}
	/**
	 * @return the upass
	 */
	public String getUpassword() {
		return upassword;
	}
	/**
	 * @param upass the upass to set
	 */
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	/**
	 * @return the isadmin
	 */
	public Boolean getIsadmin() {
		return isadmin;
	}
	/**
	 * @param isadmin the isadmin to set
	 */
	public void setIsadmin(Boolean isadmin) {
		this.isadmin = isadmin;
	}
	
	

}