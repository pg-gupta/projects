package models;

/**
 * Class is derived from Person class and has all the attributes
 * relevant for a Patient
 */
/**
 * 
 * @author pooja gupta & yeshwanthi durairaj
 * Date: 03/21/2018
 * File: Patient.java
 * Final Project
 *
 */
public class Patient extends Person {

	int id;
	String[] symptoms;
	double height;
	double weight;
	String bloodgroup;
	Boolean diabetic;
	String others;
	double BMI;
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
	 * @return the bloodgroup
	 */
	public String getBloodgroup() {
		return bloodgroup;
	}
	/**
	 * @param bloodgroup the bloodgroup to set
	 */
	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}
	/**
	 * @return the diabetic
	 */
	public Boolean getDiabetic() {
		return diabetic;
	}
	/**
	 * @param diabetic the diabetic to set
	 */
	public void setDiabetic(Boolean diabetic) {
		this.diabetic = diabetic;
	}
	/**
	 * @return the others
	 */
	public String getOthers() {
		return others;
	}
	/**
	 * @param others the others to set
	 */
	public void setOthers(String others) {
		this.others = others;
	}
	/**
	 * @return the bMI
	 */
	public double getBMI() {
		return BMI;
	}
	/**
	 * @param bMI the bMI to set
	 */
	public void setBMI(double bMI) {
		BMI = bMI;
	}

}
