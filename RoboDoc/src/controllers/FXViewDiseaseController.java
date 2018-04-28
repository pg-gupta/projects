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
import models.Disease;
import models.Diseasedao;
/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Controller which manages operations and event on the ViewDisease partial page
 *
 */
public class FXViewDiseaseController implements Initializable {

	@FXML
	private AnchorPane main;

	@FXML
	private TableView<Disease> diseaseTable;

	@FXML
	private TableColumn<Disease, String> idCol;

	@FXML
	private TableColumn<Disease, String> nameCol;

	@FXML
	private TableColumn<Disease, String> treatmentCol;

	@FXML
	private TableColumn<Disease, String> preventionCol;

	@FXML
	private TableColumn<Disease, String> iscontagiousCol;

	@FXML
	private TableColumn<Disease, String> preffereddietCol;

	@FXML
	private TableColumn<Disease, String> testsuggestedCol;

	@FXML
	private TextField testsuggested;

	@FXML
	private TextField id;

	@FXML
	private TextField name;

	@FXML
	private TextField treatement;

	@FXML
	private TextField prevention;

	@FXML
	private TextField iscontagious;

	@FXML
	private TextField preferreddiet;
	@FXML
	private AnchorPane editDiseasePane;
	@FXML
	private Button commit;

	@FXML
	private Button cancel;

	ObservableList<Disease> diseases = FXCollections.observableArrayList();

	public FXViewDiseaseController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idCol.setCellValueFactory(new PropertyValueFactory<Disease, String>("disease_id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Disease, String>("disease_name"));
		treatmentCol.setCellValueFactory(new PropertyValueFactory<Disease, String>("treatment"));
		preventionCol.setCellValueFactory(new PropertyValueFactory<Disease, String>("prevention"));
		iscontagiousCol.setCellValueFactory(new PropertyValueFactory<Disease, String>("is_contagious"));
		preffereddietCol.setCellValueFactory(new PropertyValueFactory<Disease, String>("preffered_diet"));
		testsuggestedCol.setCellValueFactory(new PropertyValueFactory<Disease, String>("test_suggested"));

		// add delete button on row
		TableColumn<Disease, Boolean> col_action = new TableColumn<Disease, Boolean>("Action");

		diseaseTable.getColumns().add(col_action);

		col_action.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Disease, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disease, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});

		// Adding the Button to the cell
		col_action.setCellFactory(new Callback<TableColumn<Disease, Boolean>, TableCell<Disease, Boolean>>() {

			@Override
			public TableCell<Disease, Boolean> call(TableColumn<Disease, Boolean> p) {
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

		diseaseTable.toFront();

		diseaseTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
			if (nv != null) {
				id.setText(String.valueOf(nv.getDisease_id()));
				name.setText(nv.getDisease_name());
				prevention.setText(nv.getPrevention());
				preferreddiet.setText(nv.getPreffered_diet());
				treatement.setText(nv.getTreatment());
				iscontagious.setText(nv.getIs_contagious());
				testsuggested.setText(nv.getTest_suggested());

			}
		});

		commit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				Diseasedao diseasedao = new Diseasedao();
				Disease item = diseaseTable.getSelectionModel().getSelectedItem();
				item.setDisease_id(Integer.parseInt(id.getText()));
				item.setDisease_name(name.getText());
				item.setPrevention(prevention.getText());
				item.setPreffered_diet(preferreddiet.getText());
				item.setTreatment(treatement.getText());
				item.setIs_contagious(iscontagious.getText());
				item.setTest_suggested(testsuggested.getText());

				try {
					diseasedao.update(item);
					diseaseTable.refresh();
					showAlert(Alert.AlertType.INFORMATION, "Record Updated!",
							"Disease id " + item.getDisease_id() + " is updated in the database");
				} catch (Exception e) {
					showAlert(AlertType.ERROR, "Disease", "Error occurred while updating record");
				}
				diseaseTable.toFront();
			}
		});

		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				diseaseTable.toFront();
			}
		});

		diseaseTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					editDiseasePane.toFront();
				}
			}
		});

	}

	private void bindData() {

		Diseasedao Diseasedao = new Diseasedao();

		List<Disease> resultSet = Diseasedao.getRecords(new Disease());
		for (Disease doc : resultSet) {
			diseases.add(doc);
		}

		diseaseTable.setItems(diseases);

	}

	// Define the button cell
	class ButtonCell extends TableCell<Disease, Boolean> {
		final Button cellButton = new Button("Delete");

		ButtonCell() {

			// Action when the button is pressed
			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					// get Selected Item
					Disease currentPerson = (Disease) ButtonCell.this.getTableView().getItems()
							.get(ButtonCell.this.getIndex());

					Disease disease = new Disease();
					Diseasedao diseasedao = new Diseasedao();
					disease.setDisease_id(currentPerson.getDisease_id());
					try {
						diseasedao.delete(disease);
						diseaseTable.refresh();
						showAlert(Alert.AlertType.INFORMATION, "Record deleted!",
								"Disease id " + currentPerson.getDisease_id() + " is deleted from  the database");
					} catch (Exception e) {
						showAlert(AlertType.INFORMATION, "Disease", "Error deleted record");
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