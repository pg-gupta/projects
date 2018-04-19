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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import models.Doctor;
import models.Doctordao;

public class FXDoctorController implements Initializable {

	@FXML
	private AnchorPane main;

	@FXML
	private TableView<Doctor> doctorTable;

	@FXML
	private TableColumn<Doctor, String> idCol;

	@FXML
	private TableColumn<Doctor, String> nameCol;

	@FXML
	private HBox hbox;

	ObservableList<Doctor> doctors = FXCollections.observableArrayList();

	public FXDoctorController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("doctor_Id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("fname"));

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
		TextField id = new TextField();
		id.setPrefWidth(100);
		TextField name = new TextField();
		name.setPrefWidth(100);

		doctorTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
			if (nv != null) {
				id.setText(String.valueOf(nv.getDoctor_Id()));
				name.setText(nv.getFname());
			}
		});

		// save the content on commit
		Button commit = new Button("Commit");
		commit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				Doctor item = doctorTable.getSelectionModel().getSelectedItem();
				item.setDoctor_Id(Integer.parseInt(id.getText()));
				item.setFname(name.getText());
				doctorTable.toFront();
			}
		});

		hbox.getChildren().addAll(id, name, commit);

		doctorTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					StackPane.setMargin(hbox, new Insets(evt.getSceneY(), 0, 0, 0));
					hbox.toFront();
				}
			}
		});
		// main.getChildren().add(root);
	}

	private void bindData() {

		// DaoModel model = new DaoModel();
		Doctordao doctorDao = new Doctordao();

		List<Doctor> resultSet = doctorDao.getRecords(new Doctor());
		for (Doctor doc : resultSet) {
			doctors.add(doc);
		}

		doctorTable.setItems(doctors);

		/*
		 * try { while (resultSet.next()) {
		 * 
		 * Doctor doc = new Doctor();
		 * 
		 * doc.setDoctor_Id(Integer.parseInt(resultSet.getString(1)));
		 * doc.setFname(resultSet.getString(2)); doctors.add(doc); }
		 * doctorTable.setItems(doctors); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
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
					// remove selected item from the table list
					doctors.remove(currentPerson);
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
}
