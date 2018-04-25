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
	private VBox vbox1;
	

	@FXML
	private HBox hbox1;
	@FXML
	private HBox hbox2;

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
		
		Label lid = new Label("Id");
		lid.setPrefWidth(100);
		Label ldescription = new Label("Description");
		ldescription.setPrefWidth(100);
		Label ldiseaseid = new Label("Disease Id");
		ldiseaseid.setPrefWidth(100);


		TextField id = new TextField();
		id.setPrefWidth(100);
		TextField description = new TextField();
		description.setPrefWidth(100);
		TextField diseaseid = new TextField();
		diseaseid.setPrefWidth(100);


	   symptomTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
		   if (nv != null) {
			id.setText(String.valueOf(nv.getSymptom_id()));
			description.setText(nv.getDesc());
			diseaseid.setText(String.valueOf(nv.getDisease_id()));
			}
		});

		// save the content on commit
		Button commit = new Button("Commit");
		Button cancel = new Button("Cancel");

		commit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				Symptomdao symdao =new Symptomdao();
				Symptom item = symptomTable.getSelectionModel().getSelectedItem();
				item.setSymptom_id((Integer.parseInt(id.getText())));
				item.setDesc(description.getText());
				item.setDisease_id((Integer.parseInt(diseaseid.getText())));
				symdao.update(item);
				showAlert(Alert.AlertType.INFORMATION,"Record Updated!","Symptom id " + item.getSymptom_id() + " is updated in the database");

				symptomTable.toFront();
			}
		});
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				symptomTable.toFront();
			}
		});

		
		

		hbox1.getChildren().addAll(lid,ldescription,ldiseaseid);

		hbox2.getChildren().addAll(id, description,diseaseid ,commit,cancel);

		symptomTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					StackPane.setMargin(vbox1, new Insets(evt.getSceneY(), 0, 0, 0));
					StackPane.setMargin(hbox1, new Insets(evt.getSceneY(), 0, 0, 0));
					StackPane.setMargin(hbox2, new Insets(evt.getSceneY(), 0, 0, 0));
					vbox1.toFront();
					hbox1.toFront();
					hbox2.toFront();
				}
			}
		});
		// main.getChildren().add(root);
	}

	private void bindData() {

		// DaoModel model = new DaoModel();
		Symptomdao Symptomdao = new Symptomdao();

		List<Symptom> resultSet = Symptomdao.getRecords(new Symptom());
		for (Symptom doc : resultSet) {
			symptoms.add(doc);
		}

		symptomTable.setItems(symptoms);

		/*
		 * try { while (resultSet.next()) {
		 * 
		 * Symptom doc = new Symptom();
		 * 
		 * doc.setDoctor_Id(Integer.parseInt(resultSet.getString(1)));
		 * doc.setFname(resultSet.getString(2)); symptoms.add(doc); }
		 * symptomTable.setItems(symptoms); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
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
					Symptom currentPerson = (Symptom) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
					// remove selected item from the table list
					//symptoms.remove(currentPerson);
					Symptom sym =new Symptom();
					Symptomdao symdao= new Symptomdao();
					//System.out.println("currentPerson is" + currentPerson.id);
					sym.setSymptom_id(currentPerson.getSymptom_id());
					symdao.delete(sym);
					showAlert(Alert.AlertType.INFORMATION,"Record deleted!","Symptom id " + currentPerson.getSymptom_id() + " is deleted from  the database");
					
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
	public void showAlert(Alert.AlertType alertType, String title, String message) 
	{
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
	    //alert.getDialogPane().setContent(gridPane);
	    alert.show();
       // alert.initOwner(Window);

			
	}

}