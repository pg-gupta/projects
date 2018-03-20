package models;

public class Disease {
	int id;
	String name;
	String[] symptoms;
	String treatment;
	String prevention;
	String facts;
	String transmission;
	Boolean isContiguous;
	String preferredDiet;
	String[] testsSuggested;
	
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
	 * @return the symptoms
	 */
	public String[] getSymptoms() {
		return symptoms;
	}
	/**
	 * @param symptoms the symptoms to set
	 */
	public void setSymptoms(String[] symptoms) {
		this.symptoms = symptoms;
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
	 * @return the facts
	 */
	public String getFacts() {
		return facts;
	}
	/**
	 * @param facts the facts to set
	 */
	public void setFacts(String facts) {
		this.facts = facts;
	}
	/**
	 * @return the transmission
	 */
	public String getTransmission() {
		return transmission;
	}
	/**
	 * @param transmission the transmission to set
	 */
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	/**
	 * @return the isContiguous
	 */
	public Boolean getIsContiguous() {
		return isContiguous;
	}
	/**
	 * @param isContiguous the isContiguous to set
	 */
	public void setIsContiguous(Boolean isContiguous) {
		this.isContiguous = isContiguous;
	}
	/**
	 * @return the preferredDiet
	 */
	public String getPreferredDiet() {
		return preferredDiet;
	}
	/**
	 * @param preferredDiet the preferredDiet to set
	 */
	public void setPreferredDiet(String preferredDiet) {
		this.preferredDiet = preferredDiet;
	}
	/**
	 * @return the testsSuggested
	 */
	public String[] getTestsSuggested() {
		return testsSuggested;
	}
	/**
	 * @param testsSuggested the testsSuggested to set
	 */
	public void setTestsSuggested(String[] testsSuggested) {
		this.testsSuggested = testsSuggested;
	}

}
