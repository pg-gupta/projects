package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;

public class MainAppController implements Initializable {
	@FXML
	private MenuItem addMedicine;

	@FXML
	private MenuItem addMedicine3;

	@FXML
	private MenuItem addMedicine31;

	@FXML
	private MenuItem addMedicine4;

	@FXML
	private MenuItem addMedicine32;

	@FXML
	private MenuItem addMedicine311;

	@FXML
	private MenuItem addMedicine5;

	@FXML
	private MenuItem addMedicine33;

	@FXML
	private MenuItem addMedicine312;

	@FXML
	private MenuItem addMedicine6;

	@FXML
	private MenuItem addMedicine34;

	@FXML
	private MenuItem addMedicine313;

	@FXML
	private MenuItem addMedicine7;

	@FXML
	private MenuItem addMedicine35;

	@FXML
	private MenuItem addMedicine314;

	@FXML
	private AnchorPane dynamicPane;

	@FXML
	void EditMedicine(ActionEvent event) {

	}

	@FXML
	void deleteMedicine(ActionEvent event) {

	}

	@FXML
	void viewMedicine(ActionEvent event) {

	}

	@FXML
	void handleAddMedicineACtion(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("Main2.fxml"));
		dynamicPane.getChildren().setAll(child);
	}

	@FXML
	void handleMedicineButtonClick(ActionEvent event) throws IOException {
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("Main2.fxml"));
		dynamicPane.getChildren().setAll(child);
	}

	/*@FXML
	void handleViewDoctorAction(ActionEvent event) throws IOException {
		//DoctorController ctnrl=new DoctorController();
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("ViewDoctor.fxml"));
		
		dynamicPane.getChildren().setAll(child);
		
		
	}*/
	
	@FXML
	void handleViewDoctorAction(ActionEvent event) throws IOException {
		//DoctorController ctnrl=new DoctorController();
		Node child = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("ViewDoctor.fxml"));
		
		dynamicPane.getChildren().setAll(child);
		
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}