package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import javafx.util.Callback;
import models.Patient;
import models.Patientdao;
import models.Person;
import models.Persondao;
/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Controller which manages operations and event on the ViewPatient partial page
 *
 */
public class FXViewPatientController implements Initializable {

	@FXML
	private AnchorPane main;

	@FXML
	private TableView<Patient> patientTable;

	@FXML
	private TableColumn<Patient, String> idCol;

	@FXML
	private TableColumn<Patient, String> fnameCol;

	@FXML
	private TableColumn<Patient, String> lnameCol;

	@FXML
	private TableColumn<Patient, String> ageCol;

	@FXML
	private TableColumn<Patient, String> sexCol;

	@FXML
	private TableColumn<Patient, String> emailCol;

	@FXML
	private TableColumn<Patient, String> phonenoCol;

	@FXML
	private TableColumn<Patient, String> addCol;

	@FXML
	private TableColumn<Patient, String> heightCol;

	@FXML
	private TableColumn<Patient, String> weightCol;

	@FXML
	private TableColumn<Patient, String> diabeticCol;

	@FXML
	private AnchorPane editPatientPane;

	@FXML
	private TextField id;

	@FXML
	private TextField fname;

	@FXML
	private TextField lname;

	@FXML
	private TextField age;

	@FXML
	private TextField sex;

	@FXML
	private TextField email;

	@FXML
	private TextField phoneno;

	@FXML
	private TextField add;

	@FXML
	private TextField height;

	@FXML
	private TextField weight;

	@FXML
	private TextField diabetic;

	@FXML
	private Button commit;

	@FXML
	private Button cancel;

	ObservableList<Patient> patients = FXCollections.observableArrayList();

	public FXViewPatientController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("id"));
		fnameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("fname"));
		lnameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("lname"));
		ageCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("age"));
		sexCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("sex"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("email"));
		phonenoCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("phonenumber"));
		addCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("address"));
		heightCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("height"));
		weightCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("weight"));
		diabeticCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("isDiabetic"));

		// add delete button on row
		TableColumn<Patient, Boolean> col_action = new TableColumn<Patient, Boolean>("Action");

		patientTable.getColumns().add(col_action);

		col_action.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Patient, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Patient, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});

		// Adding the Button to the cell
		col_action.setCellFactory(new Callback<TableColumn<Patient, Boolean>, TableCell<Patient, Boolean>>() {

			@Override
			public TableCell<Patient, Boolean> call(TableColumn<Patient, Boolean> p) {
				return new ButtonCell();
			}

		});

		// bind data of the database to the table
		bindData();
		// edit row on click
		editRow();
	}

	// edit row of the table on double click
	private void editRow() {

		patientTable.toFront();

		patientTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
			if (nv != null) {
				id.setText(String.valueOf(nv.getId()));
				fname.setText(nv.getFname());
				lname.setText(nv.getLname());
				age.setText(String.valueOf(nv.getAge()));
				sex.setText(nv.getSex());
				email.setText(nv.getEmail());
				phoneno.setText(nv.getPhonenumber());
				add.setText(nv.getAddress());
				height.setText(String.valueOf(nv.getHeight()));
				weight.setText(String.valueOf(nv.getWeight()));
				diabetic.setText(String.valueOf(nv.getIsDiabetic()));

			}
		});

		commit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {

				Patientdao patientdao = new Patientdao();
				Patient item = patientTable.getSelectionModel().getSelectedItem();

				item.setFname(fname.getText());
				item.setLname(lname.getText());
				item.setAge(Integer.parseInt(age.getText()));
				item.setSex(sex.getText());
				item.setEmail(email.getText());
				item.setPhonenumber(phoneno.getText());
				item.setAddress(add.getText());
				item.setHeight(Integer.parseInt(height.getText()));
				item.setWeight(Integer.parseInt(weight.getText()));
				item.setIsDiabetic(diabetic.getText());

				try {
				patientdao.update(item);
				patientTable.refresh();
				showAlert(Alert.AlertType.INFORMATION, "Record Updated!",
						"Patient id " + item.id + " is updated in the database");
				}
				catch(Exception e) {
					showAlert(AlertType.ERROR, "Patient", "Error occurred while updating entry, message:" + e.getMessage());
				}
				patientTable.toFront();
			}
		});

		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				patientTable.toFront();
			}
		});

		patientTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					editPatientPane.toFront();
				}
			}
		});

	}

	private void bindData() {
		Patientdao Patientdao = new Patientdao();

		List<Patient> resultSet = Patientdao.getRecords(new Patient());
		for (Patient pt : resultSet) {
			patients.add(pt);
		}

		patientTable.setItems(patients);

	}

	// Define the button cell
	class ButtonCell extends TableCell<Patient, Boolean> {
		final Button cellButton = new Button("Delete");

		ButtonCell() {

			// Action when the button is pressed
			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					// get Selected Item
					Patient currentPerson = (Patient) ButtonCell.this.getTableView().getItems()
							.get(ButtonCell.this.getIndex());
					// remove selected item from the table list
					patients.remove(currentPerson);
					Persondao perdao = new Persondao();
					Patient pt = new Patient();
					Patientdao ptdao = new Patientdao();
					System.out.println("currentPerson is" + currentPerson.id);
					pt.setId(currentPerson.id);
					try {
						ptdao.delete(pt);
						patientTable.refresh();
						showAlert(Alert.AlertType.INFORMATION, "Record deleted!",
								"Patient id " + currentPerson.id + " is deleted from  the database");
					} catch (Exception e) {
						showAlert(AlertType.ERROR, "Patient",
								"Error occurred while updating entry, message:" + e.getMessage());
					}
				}
			});

			cellButton.getStyleClass().add("danger");
		}

		// Display button if the row is not empty
		@Override
		protected void updateItem(Boolean t, boolean empty) {
			super.updateItem(t, empty);
			if (!empty) {
				setGraphic(cellButton);
			}
		}

	}

	public void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}

}