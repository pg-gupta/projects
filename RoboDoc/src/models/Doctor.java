package models;

import javax.persistence.Entity;



@ Entity
public class Doctor extends Person

{   
	String visithours;
	String specialization;
	String degree;
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
	
	
	
}