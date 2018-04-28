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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
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
 * Controller which manages operations and event on the ViewMedicine partial page
 *
 */
public class FXViewMedicineController implements Initializable {

	@FXML
	private AnchorPane main;

	@FXML
	private TableView<Medicine> medicineTable;

	@FXML
	private TableColumn<Medicine, String> idCol;

	@FXML
	private TableColumn<Medicine, String> nameCol;

	@FXML
	private TableColumn<Medicine, String> drugsCol;

	@FXML
	private TableColumn<Medicine, String> descriptionCol;

	@FXML
	private TableColumn<Medicine, String> dosageCol;

	@FXML
	private TableColumn<Medicine, String> diseaseidCol;

	@FXML
	private TextField id;

	@FXML
	private TextField name;

	@FXML
	private TextField drugs;

	@FXML
	private TextField description;

	@FXML
	private TextField dosage;

	@FXML
	private TextField diseaseid;

	@FXML
	private Button commit;

	@FXML
	private Button cancel;
	@FXML
	private AnchorPane editMedicinePane;

	ObservableList<Medicine> medicines = FXCollections.observableArrayList();

	public FXViewMedicineController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idCol.setCellValueFactory(new PropertyValueFactory<Medicine, String>("medicine_id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Medicine, String>("medicine_name"));
		drugsCol.setCellValueFactory(new PropertyValueFactory<Medicine, String>("drugs"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<Medicine, String>("Description"));
		dosageCol.setCellValueFactory(new PropertyValueFactory<Medicine, String>("Dosage"));
		diseaseidCol.setCellValueFactory(new PropertyValueFactory<Medicine, String>("disease_id"));

		// add delete button on row
		TableColumn<Medicine, Boolean> col_action = new TableColumn<Medicine, Boolean>("Action");

		medicineTable.getColumns().add(col_action);

		col_action.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Medicine, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Medicine, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});

		// Adding the Button to the cell
		col_action.setCellFactory(new Callback<TableColumn<Medicine, Boolean>, TableCell<Medicine, Boolean>>() {

			@Override
			public TableCell<Medicine, Boolean> call(TableColumn<Medicine, Boolean> p) {
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

		medicineTable.toFront();
		medicineTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
			if (nv != null) {
				id.setText(String.valueOf(nv.getMedicine_id()));
				name.setText(nv.getMedicine_name());
				drugs.setText(nv.getDrugs());
				description.setText(nv.getDescription());
				dosage.setText(nv.getDosage());
				diseaseid.setText(String.valueOf((nv.getDisease_id())));

			}
		});

		commit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				Medicinedao meddao = new Medicinedao();
				Medicine item = medicineTable.getSelectionModel().getSelectedItem();
				item.setMedicine_id(Integer.parseInt(id.getText()));
				item.setMedicine_name(name.getText());
				item.setDrugs(drugs.getText());
				item.setDescription(description.getText());
				item.setDosage(dosage.getText());
				item.setDisease_id(Integer.parseInt(diseaseid.getText()));
				try {
				meddao.update(item);
				medicineTable.refresh();
				showAlert(Alert.AlertType.INFORMATION, "Record Updated!",
						"Medicine id " + item.getMedicine_id() + " is updated in the database");
				}
				catch (Exception e) {
					showAlert(AlertType.ERROR, "Medicine",
							"Error occurred while updating entry, message:" + e.getMessage());
				}
				medicineTable.toFront();
			}
		});

		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				medicineTable.toFront();
			}
		});

		medicineTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt) {
				if (evt.getClickCount() == 2) {

					editMedicinePane.toFront();
				}
			}
		});
	}

	private void bindData() {

		// DaoModel model = new DaoModel();
		Medicinedao Medicinedao = new Medicinedao();

		List<Medicine> resultSet = Medicinedao.getRecords(new Medicine());
		for (Medicine doc : resultSet) {
			medicines.add(doc);
		}

		medicineTable.setItems(medicines);

	}

	// Define the button cell
	class ButtonCell extends TableCell<Medicine, Boolean> {
		final Button cellButton = new Button("Delete");

		ButtonCell() {

			// Action when the button is pressed
			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					// get Selected Item
					Medicine currentPerson = (Medicine) ButtonCell.this.getTableView().getItems()
							.get(ButtonCell.this.getIndex());
					// remove selected item from the table list
					medicines.remove(currentPerson);
					Medicine med = new Medicine();
					Medicinedao meddao = new Medicinedao();

					med.setMedicine_id(currentPerson.getMedicine_id());
					try {
					meddao.delete(med);
					medicineTable.refresh();
					showAlert(Alert.AlertType.INFORMATION, "Record deleted!",
							"Medicine id " + currentPerson.getMedicine_id() + " is deleted from  the database");
					}
					catch (Exception e) {
						showAlert(AlertType.ERROR, "Medicine",
								"Error occurred while deleting entry, message:" + e.getMessage());
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