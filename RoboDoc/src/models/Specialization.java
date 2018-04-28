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
 * Class describing database entity Specialization
 *
 */
@Entity
public class Specialization {
	@Id
	int specializationid;
	String specname;

	
	
	/**
	 * @return the specialization1
	 */
	public String getSpecname() {
		return specname;
	}
	/**
	 * @param specialization1 the specialization1 to set
	 */
	public void setSpecname(String specname) {
		this.specname = specname;
	}
	/**
	 * @return the specializationid
	 */
	public int getSpecializationid() {
		return specializationid;
	}
	/**
	 * @param specializationid the specializationid to set
	 */
	public void setSpecializationid(int specializationid) {
		this.specializationid = specializationid;
	}
	

}
