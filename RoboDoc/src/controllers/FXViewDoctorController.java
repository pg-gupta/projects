package controllers;

import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.Doctor;
import models.Doctordao;
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
 * Controller which manages operations and event on the ViewDoctor partial page
 *
 */
public class FXViewDoctorController implements Initializable {

	@FXML
	private AnchorPane main;
	@FXML
	private AnchorPane editDoctorPane;
	@FXML
	private TableView<Doctor> doctorTable;

	@FXML
	private TableColumn<Doctor, String> idCol;

	@FXML
	private TableColumn<Doctor, String> fnameCol;

	@FXML
	private TableColumn<Doctor, String> lnameCol;

	@FXML
	private TableColumn<Doctor, String> ageCol;

	@FXML
	private TableColumn<Doctor, String> sexCol;

	@FXML
	private TableColumn<Doctor, String> emailCol;

	@FXML
	private TableColumn<Doctor, String> phonenoCol;

	@FXML
	private TableColumn<Doctor, String> addCol;

	@FXML
	private TableColumn<Doctor, String> specializationCol;

	@FXML
	private TableColumn<Doctor, String> visithoursCol;

	@FXML
	private TableColumn<Doctor, String> degreeCol;
	@FXML
	private TextField id;

	@FXML
	private TextField fname;

	@FXML
	private TextField lname;

	@FXML
	private TextField sex;

	@FXML
	private TextField age;

	@FXML
	private TextField phoneno;

	@FXML
	private TextField email;

	@FXML
	private TextField visithours;

	@FXML
	private TextField specialization;

	@FXML
	private TextField degree;

	@FXML
	private TextField add;

	@FXML
	private Button commit;

	@FXML
	private Button cancel;

	ObservableList<Doctor> doctors = FXCollections.observableArrayList();

	public FXViewDoctorController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("id"));
		fnameCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("fname"));
		lnameCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("lname"));
		ageCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("age"));
		sexCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("sex"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("email"));
		phonenoCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("phonenumber"));
		addCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("address"));
		specializationCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("specialization"));
		visithoursCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("visithours"));
		degreeCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("degree"));

		// add delete button on row
		TableColumn<Doctor, Boolean> col_action = new TableColumn<Doctor, Boolean>("Action");

		doctorTable.getColumns().add(col_action);

		col_action.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Doctor, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Doctor, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});

		// Adding the Button to the cell
		col_action.setCellFactory(new Callback<TableColumn<Doctor, Boolean>, TableCell<Doctor, Boolean>>() {

			@Override
			public TableCell<Doctor, Boolean> call(TableColumn<Doctor, Boolean> p) {
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

		doctorTable.toFront();

		doctorTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
			if (nv != null) {
				id.setText(String.valueOf(nv.getId()));
				fname.setText(nv.getFname());
				lname.setText(nv.getLname());
				age.setText(String.valueOf(nv.getAge()));
				sex.setText(nv.getSex());
				email.setText(nv.getEmail());
				phoneno.setText(nv.getPhonenumber());
				add.setText(nv.getAddress());
				specialization.setText(nv.getSpecialization());
				visithours.setText(nv.getVisithours());
				degree.setText(nv.getDegree());

			}
		});

		commit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				Doctordao Doctordao = new Doctordao();
				Doctor item = doctorTable.getSelectionModel().getSelectedItem();

				item.setFname(fname.getText());
				item.setLname(lname.getText());
				item.setAge(Integer.parseInt(age.getText()));
				item.setSex(sex.getText());
				item.setEmail(email.getText());
				item.setPhonenumber(phoneno.getText());
				item.setAddress(add.getText());
				item.setSpecialization(specialization.getText());
				item.setVisithours(visithours.getText());
				item.setDegree(degree.getText());

				try {
					Doctordao.update(item);
					doctorTable.refresh();
					showAlert(Alert.AlertType.INFORMATION, "Record Updated!",
							"Doctor id " + item.id + " is updated in the database");
				} catch (Exception e) {
					showAlert(AlertType.ERROR, "Doctor", "Error occurred while updating record");
				}

				doctorTable.toFront();
			}
		});

		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				doctorTable.toFront();
			}
		});

		doctorTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt) {
				if (evt.getClickCount() == 2) {

					editDoctorPane.toFront();

				}
			}
		});
		
	}

	private void bindData() {

		// DaoModel model = new DaoModel();
		Doctordao Doctordao = new Doctordao();

		List<Doctor> resultSet = Doctordao.getRecords(new Doctor());
		for (Doctor pt : resultSet) {
			doctors.add(pt);
		}

		doctorTable.setItems(doctors);

	}

	// Define the button cell
	class ButtonCell extends TableCell<Doctor, Boolean> {
		final Button cellButton = new Button("Delete");

		ButtonCell() {

			// Action when the button is pressed
			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					// get Selected Item
					Doctor currentPerson = (Doctor) ButtonCell.this.getTableView().getItems()
							.get(ButtonCell.this.getIndex());

					Persondao perdao = new Persondao();
					Doctor pt = new Doctor();
					Doctordao ptdao = new Doctordao();
					System.out.println("currentPerson is" + currentPerson.id);
					pt.setId(currentPerson.id);
					try {
						ptdao.delete(pt);
						perdao.delete(pt);
						doctorTable.refresh();
						showAlert(Alert.AlertType.INFORMATION, "Record deleted!",
								"Doctor id " + currentPerson.id + " is deleted from  the database");
					} catch (Exception e) {
						showAlert(AlertType.ERROR, "Doctor", "Error when deleting record, message:" + e.getMessage());
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