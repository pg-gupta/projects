package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@ Entity(name="dim_medicine")
public class Medicine
{
	@Id
	int medicine_id;
	String medicine_name;
	String drugs;
	String Description;
	String Dosage;
	int disease_id;
	/**
	 * @return the medicine_id
	 */
	public int getMedicine_id() {
		return medicine_id;
	}
	/**
	 * @param medicine_id the medicine_id to set
	 */
	public void setMedicine_id(int medicine_id) {
		this.medicine_id = medicine_id;
	}
	/**
	 * @return the medicine_name
	 */
	public String getMedicine_name() {
		return medicine_name;
	}
	/**
	 * @param medicine_name the medicine_name to set
	 */
	public void setMedicine_name(String medicine_name) {
		this.medicine_name = medicine_name;
	}
	/**
	 * @return the drugs
	 */
	public String getDrugs() {
		return drugs;
	}
	/**
	 * @param drugs the drugs to set
	 */
	public void setDrugs(String drugs) {
		this.drugs = drugs;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}
	/**
	 * @return the dosage
	 */
	public String getDosage() {
		return Dosage;
	}
	/**
	 * @param dosage the dosage to set
	 */
	public void setDosage(String dosage) {
		Dosage = dosage;
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
