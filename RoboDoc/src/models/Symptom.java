package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@ Entity(name="dim_symptoms")
public class Symptom {
	@Id
	int symptom_id;
	int disease_id;
	String description;

	/**
	 * @return the symptom_id
	 */
	public int getSymptom_id() {
		return symptom_id;
	}
	/**
	 * @param symptom_id the symptom_id to set
	 */
	public void setSymptom_id(int symptom_id) {
		this.symptom_id = symptom_id;
	}
	/**
	 * @return the description
	 */
	public String getdescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
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

}
