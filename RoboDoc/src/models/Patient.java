package models;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Class describing database entity Patient
 *
 */

@ Entity
public class Patient  extends Person
{ 
	int height;
	int weight;
	String isDiabetic;
	int patientid;

	
	
	/**
	 * @return the patientid
	 */
	public int getPatientid() {
		return patientid;
	}
	/**
	 * @param patientid the patientid to set
	 */
	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}

	
	/**
	 * @return the personid
	 */
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	/**
	 * @return the isDiabetic
	 */
	public String getIsDiabetic() {
		return isDiabetic;
	}
	/**
	 * @param isDiabetic the isDiabetic to set
	 */
	public void setIsDiabetic(String isDiabetic) {
		this.isDiabetic = isDiabetic;
	}
	
	
	
}