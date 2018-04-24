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
import models.Doctor;
import models.Doctordao;
import models.Specialization;
import models.Specializationdao;
import models.Medicinedao;

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
			showAlert(Alert.AlertType.ERROR,  "Form Error!","Please enter specialization name");
			return;
		}
			
		Specialization spec =new Specialization();
		Specializationdao specdao=new Specializationdao();
		
		spec.setSpecname(Specialization.getText());
		specdao.insertData(spec);
	}

	
	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}
	
  

}
