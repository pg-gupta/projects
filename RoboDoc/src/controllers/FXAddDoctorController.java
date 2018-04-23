package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Doctor;
import models.Doctordao;
import models.Person;
import models.Persondao;

public class FXAddDoctorController  implements Initializable {
	
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
	private TextArea add;


	@FXML
	private TextArea specialization;

	@FXML
	private TextArea visitHours;

	@FXML
	private TextArea degree;

	@FXML
	private JFXButton addBtn;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

	}

	@FXML
	void addDoctor(ActionEvent event) {
		
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

		
		if (specialization.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!",	"Please enter  specialization");
			return;
		}

		if (visitHours.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR,"Form Error!","Please enter  visiting hours");
			return;
		}
		if (degree.getText().isEmpty()) 
		{
			showAlert(Alert.AlertType.ERROR,"Form Error!", "Please enter degree");
			return;
		}

	
		
		Person person= new Person();
		Persondao personDao =new Persondao();
		Doctor doc= new Doctor();
		Doctordao doctorDao=new Doctordao();
		person.setId(Integer.parseInt(id.getText()));
		doc.setId(person.getId());
		doc.setFname(fName.getText());
		doc.setLname(lName.getText());
		doc.setPhonenumber(phoneNo.getText());
		doc.setAddress(add.getText());
		doc.setAge(Integer.parseInt(age.getText()));
		doc.setEmail(email.getText());
		doc.setSex(sex.getText());
        doc.setSpecialization(specialization.getText());
		doc.setVisithours(visitHours.getText());
		doc.setDegree(degree.getText());
		//personDao.insertData(person);
		doctorDao.insertData(doc);
		
	
		
	}
	
	private void showAlert(Alert.AlertType alertType, String title, String message) 
	{
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

