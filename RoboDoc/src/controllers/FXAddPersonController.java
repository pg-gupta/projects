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
import models.Person;
import models.Persondao;

public class FXAddPersonController implements Initializable {

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
	private JFXButton addBtn;

	GridPane gridPane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


	}

	@FXML
	void addPerson(ActionEvent event) {
		
		
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
		
		
		if (sex.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR,  "Form Error!", "Please enter sex");
			return;
		}
		
		if (email.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR,  "Form Error!", "Please enter email");
			return;
		}
		Person person= new Person();
		Persondao personDao =new Persondao();
		person.setFname(fName.getText());
		person.setLname(lName.getText());
		person.setPhonenumber(phoneNo.getText());
		person.setAddress(add.getText());
		person.setAge(Integer.parseInt(age.getText()));
		person.setEmail(email.getText());
		person.setSex(sex.getText());


		personDao.insertData(person);
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
