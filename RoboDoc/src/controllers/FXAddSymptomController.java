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
import models.Symptom;
import models.Symptomdao;
/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Controller which manages operations and event on the AddSymptom view
 *
 */
public class FXAddSymptomController implements Initializable {

	@FXML
	private TextArea description;

	@FXML
	private TextArea disease_id;

	@FXML
	private JFXButton addBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	void addSymptom(ActionEvent event) {

		if (description.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter your description");
			return;
		}
		if (disease_id.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a disease_id");
			return;
		}

		if (!validate(disease_id.getText())) {

			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a numeric value for disease id");
			return;
		}

		Symptom sym = new Symptom();
		Symptomdao symdao = new Symptomdao();

		sym.setDesc(description.getText());
		sym.setDisease_id(Integer.parseInt(disease_id.getText()));

		try {
			symdao.insertData(sym);
			showAlert(AlertType.INFORMATION, "Symptom", "Record successfully inserted");
		} catch (Exception e) {
			showAlert(AlertType.ERROR, "Symptom", "Error inserting record, message:" + e.getMessage());
		}
	}

	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}

	private boolean validate(String text) {
		return text.matches("[0-9]*");
	}

}
