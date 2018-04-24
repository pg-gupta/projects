package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@ Entity(name="dim_disease")
public class Disease 
{   @Id
	int disease_id;
	String disease_name;
	String treatment;
	String prevention;
    String is_contagious;
	String preffered_diet;
	String test_suggested;
	int specializationid;
	/**
	 * @return the disease_id
	 */
	public int getDisease_id() {
		return disease_id;
	}
	/**
	 * @param disease_id the disease_id to set
	 */
	public void setDisease_id(int disease_id) {
		this.disease_id = disease_id;
	}
	/**
	 * @return the disease_name
	 */
	public String getDisease_name() {
		return disease_name;
	}
	/**
	 * @param disease_name the disease_name to set
	 */
	public void setDisease_name(String disease_name) {
		this.disease_name = disease_name;
	}
	/**
	 * @return the treatment
	 */
	public String getTreatment() {
		return treatment;
	}
	/**
	 * @param treatment the treatment to set
	 */
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	/**
	 * @return the prevention
	 */
	public String getPrevention() {
		return prevention;
	}
	/**
	 * @param prevention the prevention to set
	 */
	public void setPrevention(String prevention) {
		this.prevention = prevention;
	}
	/**
	 * @return the is_contagious
	 */
	public String Is_contagious() {
		return is_contagious;
	}
	/**
	 * @param is_conagious the is_conagious to set
	 */
	public void setIs_contagious(String is_contagious) {
		this.is_contagious = is_contagious;
	}
	/**
	 * @return the is_contagious
	 */
	public String getIs_contagious() {
		return is_contagious;
	}
	/**
	 * @return the preffered_diet
	 */
	public String getPreffered_diet() {
		return preffered_diet;
	}
	/**
	 * @param preffered_diet the preffered_diet to set
	 */
	public void setPreffered_diet(String preffered_diet) {
		this.preffered_diet = preffered_diet;
	}
	/**
	 * @return the test_suggested
	 */
	public String getTest_suggested() {
		return test_suggested;
	}
	/**
	 * @param test_suggested the test_suggested to set
	 */
	public void setTest_suggested(String test_suggested) {
		this.test_suggested = test_suggested;
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
