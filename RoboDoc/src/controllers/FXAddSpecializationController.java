package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import models.Specialization;
import models.Specializationdao;
/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Controller which manages operations and event on the AddSpecialization view
 *
 */
public class FXAddSpecializationController implements Initializable {

	@FXML
	private TextArea Specialization;

	@FXML
	private JFXButton addBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	void addSpecialization(ActionEvent event) {

		if (Specialization.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter specialization name");
			return;
		}

		Specialization spec = new Specialization();
		Specializationdao specdao = new Specializationdao();

		spec.setSpecname(Specialization.getText());
		try {
			specdao.insertData(spec);
			showAlert(AlertType.INFORMATION, "Specialization", "Record successfully inserted");
		} catch (Exception e) {
			showAlert(AlertType.ERROR, "Specialization", "Error inserting record, message:" + e.getMessage());
		}
	}

	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}

}
