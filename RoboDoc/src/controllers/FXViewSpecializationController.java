package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import models.Specialization;
import models.Specializationdao;
import models.Medicinedao;
/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Controller which manages operations and event on the ViewSpecialization partial page
 *
 */
public class FXViewSpecializationController implements Initializable {

	@FXML
	private AnchorPane main;

	@FXML
	private TableView<Specialization> specializationTable;

	@FXML
	private TableColumn<Specialization, String> idCol;

	@FXML
	private TableColumn<Specialization, String> specCol;

	@FXML
	private AnchorPane editSpecializationPane;

	@FXML
	private TextField id;

	@FXML
	private TextField specialization;

	@FXML
	private Button commit;

	@FXML
	private Button cancel;

	ObservableList<Specialization> specializations = FXCollections.observableArrayList();

	public FXViewSpecializationController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idCol.setCellValueFactory(new PropertyValueFactory<Specialization, String>("specializationid"));
		specCol.setCellValueFactory(new PropertyValueFactory<Specialization, String>("specname"));

		// add delete button on row
		TableColumn<Specialization, Boolean> col_action = new TableColumn<Specialization, Boolean>("Action");

		specializationTable.getColumns().add(col_action);

		col_action.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Specialization, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Specialization, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});

		// Adding the Button to the cell
		col_action.setCellFactory(
				new Callback<TableColumn<Specialization, Boolean>, TableCell<Specialization, Boolean>>() {

					@Override
					public TableCell<Specialization, Boolean> call(TableColumn<Specialization, Boolean> p) {
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

		specializationTable.toFront();

		specializationTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
			if (nv != null) {
				id.setText(String.valueOf(nv.getSpecializationid()));
				specialization.setText(nv.getSpecname());

			}
		});

		// save the content on commit

		commit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				Specializationdao specdao = new Specializationdao();
				Specialization item = specializationTable.getSelectionModel().getSelectedItem();
				item.setSpecializationid(Integer.parseInt(id.getText()));
				item.setSpecname(specialization.getText());
				try {
					specdao.update(item);
					specializationTable.refresh();
					showAlert(Alert.AlertType.INFORMATION, "Record Updated!",
							"Specialization id " + item.getSpecializationid() + " is updated in the database");
				} catch (Exception e) {
					showAlert(AlertType.ERROR, "Specialization",
							"Error occurred while updating entry, message:" + e.getMessage());
				}
				specializationTable.toFront();
			}
		});

		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				specializationTable.toFront();
			}
		});

		specializationTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					editSpecializationPane.toFront();
				}
			}
		});
	}

	private void bindData() {

		// DaoModel model = new DaoModel();
		Specializationdao specdao = new Specializationdao();

		List<Specialization> resultSet = specdao.getRecords(new Specialization());
		for (Specialization doc : resultSet) {
			specializations.add(doc);
		}

		specializationTable.setItems(specializations);
	}

	// Define the button cell
	class ButtonCell extends TableCell<Specialization, Boolean> {
		final Button cellButton = new Button("Delete");

		ButtonCell() {

			// Action when the button is pressed
			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					// get Selected Item
					Specialization currentPerson = (Specialization) ButtonCell.this.getTableView().getItems()
							.get(ButtonCell.this.getIndex());
					// remove selected item from the table list
					specializations.remove(currentPerson);
					Specialization spec = new Specialization();
					Specializationdao specdao = new Specializationdao();
					spec.setSpecializationid(currentPerson.getSpecializationid());
					try {
						specdao.delete(spec);
						specializationTable.refresh();
						showAlert(Alert.AlertType.INFORMATION, "Record deleted!", "Specialization id "
								+ currentPerson.getSpecializationid() + " is deleted from  the database");
					} catch (Exception e) {
						showAlert(AlertType.ERROR, "Specialization",
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
		// alert.getDialogPane().setContent(gridPane);
		alert.show();
		// alert.initOwner(Window);

	}

}