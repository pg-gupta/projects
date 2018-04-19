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

public class FXAddDoctorController implements Initializable {

	@FXML
	private TextArea fName;

	@FXML
	private TextArea lName;

	@FXML
	private TextArea age;

	@FXML
	private TextArea specialization;

	@FXML
	private TextArea visitHours;

	@FXML
	private TextArea degree;

	@FXML
	private TextArea phoneNo;

	@FXML
	private TextArea add;

	@FXML
	private JFXButton addBtn;

	GridPane gridPane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gridPane = createRegistrationFormPane();

	}

	@FXML
	void addDoctor(ActionEvent event) {
		
		
		if (fName.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
					"Please enter your first name");
			return;
		}
		if (lName.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
					"Please enter your last name");
			return;
		}
		if (specialization.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
					"Please enter a specialization");
			return;
		}

		if (visitHours.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
					"Please enter a visit hours");
			return;
		}
		if (degree.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a degree");
			return;
		}
		if (phoneNo.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
					"Please enter a phone number");
			return;
		}
		if (add.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a address");
			return;
		}
		
		Doctor doc= new Doctor();
		Doctordao doctorDao=new Doctordao();
		doc.setFname(fName.getText());
		doc.setLname(lName.getText());
		doc.setDegree(degree.getText());
		doc.setSpecialization(specialization.getText());
		doc.setVisiting_Hours(visitHours.getText());
		doc.setPhone_Number(phoneNo.getText());
		doctorDao.insertData(doc);
	}

	private GridPane createRegistrationFormPane() {
		// Instantiate a new Grid Pane
		GridPane gridPane = new GridPane();

		// Position the pane at the center of the screen, both vertically and
		// horizontally
		gridPane.setAlignment(Pos.CENTER);

		// Set a padding of 20px on each side
		gridPane.setPadding(new Insets(40, 40, 40, 40));

		// Set the horizontal gap between columns
		gridPane.setHgap(10);

		// Set the vertical gap between rows
		gridPane.setVgap(10);

		// Add Column Constraints

		// columnOneConstraints will be applied to all the nodes placed in column one.
		ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		columnOneConstraints.setHalignment(HPos.RIGHT);

		// columnTwoConstraints will be applied to all the nodes placed in column two.
		ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
		columnTwoConstrains.setHgrow(Priority.ALWAYS);

		gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

		return gridPane;
	}

	private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}

}
