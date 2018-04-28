package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Controller which manages operations and event on the Main Page
 *
 */
public class FXMainAppController implements Initializable {
	@FXML
	private Menu personTab;

	@FXML
	private MenuItem addPerson;

	@FXML
	private MenuItem ViewPerson;

	@FXML
	private Menu medicineTab;

	@FXML
	private MenuItem addMedicine;

	@FXML
	private MenuItem ViewMedicine;

	@FXML
	private Menu doctorTab;

	@FXML
	private MenuItem addDoctor;

	@FXML
	private MenuItem viewDoctor;

	@FXML
	private Menu patientTab;

	@FXML
	private MenuItem addPatient;

	@FXML
	private MenuItem viewPatient;

	@FXML
	private Menu symptomTab;

	@FXML
	private MenuItem addSymptom;

	@FXML
	private MenuItem viewSymptom;

	@FXML
	private Menu diseaseaTab;

	@FXML
	private MenuItem addDisease;

	@FXML
	private MenuItem viewDisease;
	
	@FXML
	private Menu specializationTab;

	@FXML
	private MenuItem addSpecialization;

	@FXML
	private MenuItem viewSpecialization;


	@FXML
	private AnchorPane dynamicPane;

	@FXML
	void handleAddMedicineAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("AddMedicine.fxml"));
		dynamicPane.getChildren().setAll(child);
	}

	@FXML
	void handleViewMedicineAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("ViewMedicine.fxml"));
		dynamicPane.getChildren().setAll(child);
	}

	@FXML
	void handleAddDoctorAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("AddDoctor.fxml"));
		dynamicPane.getChildren().setAll(child);
	}

	@FXML
	void handleViewDoctorAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("ViewDoctor.fxml"));
		dynamicPane.getChildren().setAll(child);
	}

	@FXML
	void handleAddPatientAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("AddPatient.fxml"));
		dynamicPane.getChildren().setAll(child);
	}

	@FXML
	void handleViewPatientAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("ViewPatient.fxml"));
		dynamicPane.getChildren().setAll(child);
	}

	@FXML
	void handleAddSymptomAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("AddSymptom.fxml"));
		dynamicPane.getChildren().setAll(child);
	}

	@FXML
	void handleViewSymptomAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("ViewSymptom.fxml"));
		dynamicPane.getChildren().setAll(child);
	}

	@FXML
	void handleAddDiseaseAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("AddDisease.fxml"));

		dynamicPane.getChildren().setAll(child);

	}

	@FXML
	void handleViewDiseaseAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("ViewDisease.fxml"));

		dynamicPane.getChildren().setAll(child);

	}
	
	@FXML
	void handleAddPersonAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("AddPerson.fxml"));

		dynamicPane.getChildren().setAll(child);

	}

	
	@FXML
	void handleViewPersonAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("ViewPerson.fxml"));

		dynamicPane.getChildren().setAll(child);

	}

	
	@FXML
	void handleAddSpecializationAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("AddSpecialization.fxml"));

		dynamicPane.getChildren().setAll(child);

	}

	
	@FXML
	void handleViewSpecializationAction(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("ViewSpecialization.fxml"));

		dynamicPane.getChildren().setAll(child);

	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}