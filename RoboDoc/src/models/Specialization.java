package models;

import javax.persistence.Entity;
import javax.persistence.Id;

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
