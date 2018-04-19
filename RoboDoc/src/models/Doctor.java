package models;

import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;


@ Entity(name="doctor_details")
public class Doctor

{   


	@Id
	int doctor_Id;
	String fname;
	String lname;
	String visiting_Hours;
	String Specialization;
	String Degree;
	String phone_Number;
	String Address;
	/**
	 * @return the doctor_Id
	 */
	public int getDoctor_Id() {
		return doctor_Id;
	}
	/**
	 * @param doctor_Id the doctor_Id to set
	 */
	public void setDoctor_Id(int doctor_Id) {
		this.doctor_Id = doctor_Id;
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
	 * @return the visiting_Hours
	 */
	public String getVisiting_Hours() {
		return visiting_Hours;
	}
	/**
	 * @param visiting_Hours the visiting_Hours to set
	 */
	public void setVisiting_Hours(String visiting_Hours) {
		this.visiting_Hours = visiting_Hours;
	}
	/**
	 * @return the specialization
	 */
	public String getSpecialization() {
		return Specialization;
	}
	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialization(String specialization) {
		Specialization = specialization;
	}
	/**
	 * @return the degree
	 */
	public String getDegree() {
		return Degree;
	}
	/**
	 * @param degree the degree to set
	 */
	public void setDegree(String degree) {
		Degree = degree;
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
	/**
	 * @return the address
	 */
	public String getAddress() {
		return Address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		Address = address;
	}
	/**
	 * @return the serialversionuid
	 */
	
	
	
}
