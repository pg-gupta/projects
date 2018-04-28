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
import models.Symptom;
import models.Symptomdao;
/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Controller which manages operations and event on the ViewSymptom partial page
 *
 */

public class FXViewSymptomController implements Initializable {

	@FXML
	private AnchorPane main;

	@FXML
	private TableView<Symptom> symptomTable;

	@FXML
	private TableColumn<Symptom, String> idCol;

	@FXML
	private TableColumn<Symptom, String> descriptionCol;

	@FXML
	private TableColumn<Symptom, String> diseaseidCol;

	@FXML
	private AnchorPane editSymptomPane;

	@FXML
	private TextField id;

	@FXML
	private TextField description;

	@FXML
	private TextField diseaseid;

	@FXML
	private Button commit;

	@FXML
	private Button cancel;
	//

	ObservableList<Symptom> symptoms = FXCollections.observableArrayList();

	public FXViewSymptomController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idCol.setCellValueFactory(new PropertyValueFactory<Symptom, String>("symptom_id"));
		diseaseidCol.setCellValueFactory(new PropertyValueFactory<Symptom, String>("disease_id"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<Symptom, String>("desc"));

		// add delete button on row
		TableColumn<Symptom, Boolean> col_action = new TableColumn<Symptom, Boolean>("Action");

		symptomTable.getColumns().add(col_action);

		col_action.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Symptom, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Symptom, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});

		// Adding the Button to the cell
		col_action.setCellFactory(new Callback<TableColumn<Symptom, Boolean>, TableCell<Symptom, Boolean>>() {

			@Override
			public TableCell<Symptom, Boolean> call(TableColumn<Symptom, Boolean> p) {
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

		symptomTable.toFront();

		symptomTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
			if (nv != null) {
				id.setText(String.valueOf(nv.getSymptom_id()));
				description.setText(nv.getDesc());
				diseaseid.setText(String.valueOf(nv.getDisease_id()));
			}
		});

		commit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				Symptomdao symdao = new Symptomdao();
				Symptom item = symptomTable.getSelectionModel().getSelectedItem();
				item.setSymptom_id((Integer.parseInt(id.getText())));
				item.setDesc(description.getText());
				item.setDisease_id((Integer.parseInt(diseaseid.getText())));
				try {
					symdao.update(item);
					showAlert(Alert.AlertType.INFORMATION, "Record Updated!",
							"Symptom id " + item.getSymptom_id() + " is updated in the database");
					symptomTable.refresh();
				} catch (Exception e) {
					showAlert(AlertType.ERROR, "Symptom", "Error occurred in updated entry, message:" + e.getMessage());
				}
				symptomTable.toFront();
			}
		});

		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				symptomTable.toFront();
			}
		});

		symptomTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					editSymptomPane.toFront();
				}
			}
		});

	}

	private void bindData() {

		Symptomdao Symptomdao = new Symptomdao();

		List<Symptom> resultSet = Symptomdao.getRecords(new Symptom());
		for (Symptom doc : resultSet) {
			symptoms.add(doc);
		}

		symptomTable.setItems(symptoms);

	}

	// Define the button cell
	class ButtonCell extends TableCell<Symptom, Boolean> {
		final Button cellButton = new Button("Delete");

		ButtonCell() {

			// Action when the button is pressed
			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					// get Selected Item
					Symptom currentPerson = (Symptom) ButtonCell.this.getTableView().getItems()
							.get(ButtonCell.this.getIndex());
					Symptom sym = new Symptom();
					Symptomdao symdao = new Symptomdao();
					sym.setSymptom_id(currentPerson.getSymptom_id());
					try {
						symdao.delete(sym);
						symptomTable.refresh();
						showAlert(Alert.AlertType.INFORMATION, "Record deleted!",
								"Symptom id " + currentPerson.getSymptom_id() + " is deleted from  the database");
					} catch (Exception e) {
						showAlert(AlertType.ERROR, "Symptom",
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