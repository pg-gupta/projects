package models;

/**
 * Class is derived from Person class and has all the attributes
 * relevant for a Doctor
 */
/**
 * 
 * @author pooja gupta & yeshwanthi durairaj
 * Date: 03/21/2018
 * File: Doctor.java
 * Final Project
 *
 */
public class Doctor extends Person {
	int id;
	String category;
	String degree;
	String visitingHours;
	String experience;
	String fax;
	String consultationFee;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}

	/**
	 * @param degree
	 *            the degree to set
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * @return the visitingHours
	 */
	public String getVisitingHours() {
		return visitingHours;
	}

	/**
	 * @param visitingHours
	 *            the visitingHours to set
	 */
	public void setVisitingHours(String visitingHours) {
		this.visitingHours = visitingHours;
	}

	/**
	 * @return the experience
	 */
	public String getExperience() {
		return experience;
	}

	/**
	 * @param experience
	 *            the experience to set
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the consultationFee
	 */
	public String getConsultationFee() {
		return consultationFee;
	}

	/**
	 * @param consultationFee
	 *            the consultationFee to set
	 */
	public void setConsultationFee(String consultationFee) {
		this.consultationFee = consultationFee;
	}

}
