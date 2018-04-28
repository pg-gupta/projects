package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import models.Medicine;
import models.Medicinedao;
/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Controller which manages operations and event on the AddMedicine view
 *
 */
public class FXAddMedicineController implements Initializable {

	@FXML
	private TextArea medicine_name;

	@FXML
	private TextArea drugs;

	@FXML
	private TextArea description;

	@FXML
	private TextArea dosage;

	@FXML
	private TextArea disease_id;

	@FXML
	private JFXButton addBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	void addMedicine(ActionEvent event) {

		if (medicine_name.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter medicine name");
			return;
		}
		if (drugs.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter drug name");
			return;
		}
		if (description.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter description");
			return;
		}

		if (dosage.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter required dosage");
			return;
		}
		if (disease_id.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter disease id");
			return;
		}

		if (!validate(disease_id.getText())) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a numeric value for disease id");
			return;
		}

		Medicine medicine = new Medicine();
		Medicinedao medicinedao = new Medicinedao();

		medicine.setMedicine_name(medicine_name.getText());
		medicine.setDrugs(drugs.getText());
		medicine.setDescription(description.getText());
		medicine.setDosage(dosage.getText());
		medicine.setDisease_id(Integer.parseInt(disease_id.getText()));

		try {
			medicinedao.insertData(medicine);
			showAlert(AlertType.INFORMATION, "Medicine Insert", "Medicine successfully inserted");
		} catch (Exception e) {
			showAlert(AlertType.ERROR, "Medicine Insert", "Errpr inserting record, message:" + e.getMessage());
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
