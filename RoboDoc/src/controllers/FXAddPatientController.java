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
import models.Patient;
import models.Patientdao;
import models.Person;
import models.Persondao;

public class FXAddPatientController implements Initializable {
	
	@FXML
	private TextArea id;


	@FXML
	private TextArea fName;

	@FXML
	private TextArea lName;

	@FXML
	private TextArea age;

	@FXML
	private TextArea phoneNo;
	
	@FXML
	private TextArea email;
	
	@FXML
	private TextArea sex;

	@FXML
	private TextArea height;
	
	@FXML
	private TextArea weight;

	@FXML
	private TextArea isdiabetic;

	@FXML
	private TextArea add;


	@FXML
	private JFXButton addBtn;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	void addPatient(ActionEvent event) {
		
		if (id.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!","Please enter  id");
			return;
		}
		
		if (!validate(id.getText())) 
		{
			
			showAlert(Alert.AlertType.ERROR,  "Form Error!", "Please enter a numeric value for id");
			return;
		}
		
		
		if (fName.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!","Please enter  first name");
			return;
		}
		if (lName.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!",	"Please enter last name");
			return;
		}
				
		if (phoneNo.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!","Please enter  phone number");
			return;
		}
		if (add.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter  address");
			return;
		}
		
		if (age.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR,  "Form Error!", "Please enter age");
			return;
		}
		
		if (!validate(age.getText())) 
		{
			
			showAlert(Alert.AlertType.ERROR,  "Form Error!", "Please enter a numeric value for age");
			return;
		}
		
		if (height.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR,  "Form Error!", "Please enter age");
			return;
		}
		
		if (!validate(height.getText())) 
		{
			
			showAlert(Alert.AlertType.ERROR,  "Form Error!", "Please enter a numeric value for age");
			return;
		}

		if (weight.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR,  "Form Error!", "Please enter age");
			return;
		}
		
		if (!validate(weight.getText())) 
		{
			
			showAlert(Alert.AlertType.ERROR,  "Form Error!", "Please enter a numeric value for age");
			return;
		}

		if (isdiabetic.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR,  "Form Error!", "Please enter age");
			return;
		}
		
		Person person= new Person();
		Persondao personDao =new Persondao();
		Patientdao patientdao =new Patientdao();
		Patient patient = new Patient();
		person.setId(Integer.parseInt(id.getText()));
		patient.setId(person.getId());
		patient.setFname(fName.getText());
		patient.setLname(lName.getText());
		patient.setPhonenumber(phoneNo.getText());
		patient.setAddress(add.getText());
		patient.setEmail(email.getText());
		patient.setAge(Integer.parseInt(age.getText()));
		patient.setSex(sex.getText());
		patient.setHeight(Integer.parseInt(height.getText()));
		patient.setWeight(Integer.parseInt(height.getText()));
		patient.setIsDiabetic(isdiabetic.getText());
		//personDao.insertData(person);
		patientdao.insertData(patient);

	}

	private void showAlert(Alert.AlertType alertType, String title, String message) {
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
