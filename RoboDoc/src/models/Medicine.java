package models;

public class Medicine {

	int id;
	String name;
	String[] drugs;
	String description;
	String dosage;
	boolean requiresPrescription;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the drugs
	 */
	public String[] getDrugs() {
		return drugs;
	}
	/**
	 * @param drugs the drugs to set
	 */
	public void setDrugs(String[] drugs) {
		this.drugs = drugs;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the dosage
	 */
	public String getDosage() {
		return dosage;
	}
	/**
	 * @param dosage the dosage to set
	 */
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	/**
	 * @return the requiresPrescription
	 */
	public boolean isRequiresPrescription() {
		return requiresPrescription;
	}
	/**
	 * @param requiresPrescription the requiresPrescription to set
	 */
	public void setRequiresPrescription(boolean requiresPrescription) {
		this.requiresPrescription = requiresPrescription;
	}
}
