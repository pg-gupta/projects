package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@ Entity(name="patient_details")
public class Patient 
{ 
	@Id
	int patient_Id;
	String fname;
	String lname;
	String sex;
	double height;
	double weight;
	boolean is_diabetic;
	String street;
	String city;
	String country;
	String email;
	String phone_Number;
	/**
	 * @return the patient_Id
	 */
	public int getPatient_Id() {
		return patient_Id;
	}
	/**
	 * @param patient_Id the patient_Id to set
	 */
	public void setPatient_Id(int patient_Id) {
		this.patient_Id = patient_Id;
	}
	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	/**
	 * @return the is_diabetic
	 */
	public boolean isIs_diabetic() {
		return is_diabetic;
	}
	/**
	 * @param is_diabetic the is_diabetic to set
	 */
	public void setIs_diabetic(boolean is_diabetic) {
		this.is_diabetic = is_diabetic;
	}
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phone_Number
	 */
	public String getPhone_Number() {
		return phone_Number;
	}
	/**
	 * @param phone_Number the phone_Number to set
	 */
	public void setPhone_Number(String phone_Number) {
		this.phone_Number = phone_Number;
	}
	
}
