package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@ Entity(name="patient_tracking_details")
public class Person {
    @Id
	int tracking_id;
	int patient_id;
	int doctor_id;
	String symptoms;
	String treatment_advised;
	String rating;
	/**
	 * @return the tracking_id
	 */
	public int getTracking_id() {
		return tracking_id;
	}
	/**
	 * @param tracking_id the tracking_id to set
	 */
	public void setTracking_id(int tracking_id) {
		this.tracking_id = tracking_id;
	}
	/**
	 * @return the patient_id
	 */
	public int getPatient_id() {
		return patient_id;
	}
	/**
	 * @param patient_id the patient_id to set
	 */
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	/**
	 * @return the doctor_id
	 */
	public int getDoctor_id() {
		return doctor_id;
	}
	/**
	 * @param doctor_id the doctor_id to set
	 */
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	/**
	 * @return the symptoms
	 */
	public String getSymptoms() {
		return symptoms;
	}
	/**
	 * @param symptoms the symptoms to set
	 */
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	/**
	 * @return the treatment_advised
	 */
	public String getTreatment_advised() {
		return treatment_advised;
	}
	/**
	 * @param treatment_advised the treatment_advised to set
	 */
	public void setTreatment_advised(String treatment_advised) {
		this.treatment_advised = treatment_advised;
	}
	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}
	
}
