package controllers;



import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Window;
import models.Disease;
import models.Diseasedao;
import models.Doctor;
import models.Doctordao;

public class FXAddDiseaseController implements Initializable {

	@FXML
	private TextArea disease_name;

	@FXML
	private TextArea treatment;

	@FXML
	private TextArea prevention;

	@FXML
	private TextArea is_contagious;

	@FXML
	private TextArea preffered_diet;

	@FXML
	private TextArea test_suggested;
	
	@FXML
	private TextArea specializationid;


	
	@FXML
	private JFXButton addBtn;

	GridPane gridPane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


	}

	@FXML
	void addDisease(ActionEvent event) {
		
		
		if (disease_name.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!","Please enter disease name");
			return;
		}
		if (treatment.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!","Please enter treatment");
			return;
		}
		if (prevention.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR,  "Form Error!","Please enter prevention");
			return;
		}

		if (is_contagious.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!","Please enter is contagious or not");
			return;
		}
		if (preffered_diet.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR,  "Form Error!", "Please enter a preffered diet");
			return;
		}
		if (test_suggested.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR,  "Form Error!","Please enter test suggested");
			return;
		}
		
		if (specializationid.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR,  "Form Error!","Please enter test suggested");
			return;
		}
		
		Disease disease =new Disease();
		Diseasedao diseasedao=new Diseasedao();
		
		disease.setDisease_name(disease_name.getText());
		disease.setTreatment(treatment.getText());
		disease.setPrevention(prevention.getText());
		disease.setIs_contagious(is_contagious.getText());
		disease.setPreffered_diet(preffered_diet.getText());
		disease.setTest_suggested(test_suggested.getText());
		disease.setSpecializationid(Integer.parseInt(specializationid.getText()));

		diseasedao.insertData(disease);
	}

		private void showAlert(Alert.AlertType alertType,  String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}
		
		 private boolean validate(String text)
		    {
		        return text.matches("[0-9]*");
		    }


}
