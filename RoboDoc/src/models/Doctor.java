package models;

import javax.persistence.Entity;



@SuppressWarnings({})


@ Entity
public class Doctor extends Person

{   
	String visithours;
	String specialization;
	String degree;
	int specializationid;
	int doctorid;

	
	
	/**
	 * @return the doctorid
	 */
	public int getDoctorid() {
		return doctorid;
	}
	/**
	 * @param doctorid the doctorid to set
	 */
	public void setDoctorid(int doctorid) {
		this.doctorid = doctorid;
	}
	/**
	 * @return the visitinghours
	 */
	public String getVisithours() {
		return visithours;
	}
	/**
	 * @param visitinghours the visitinghours to set
	 */
	public void setVisithours(String visithours) {
		this.visithours = visithours;
	}
	/**
	 * @return the specialization
	 */
	public String getSpecialization() {
		return specialization;
	}
	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	/**
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}
	/**
	 * @param degree the degree to set
	 */
	public void setDegree(String degree) {
		this.degree = degree;
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