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
import models.Specialization;
import models.Specializationdao;
import models.Medicinedao;

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
	private VBox vbox1;
	

	@FXML
	private HBox hbox1;
	@FXML
	private HBox hbox2;

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
		col_action.setCellFactory(new Callback<TableColumn<Specialization, Boolean>, TableCell<Specialization, Boolean>>() {

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
		Label lid= new Label("Id");
		lid.setPrefWidth(100);
		Label lspec= new Label("Specialization");
		lspec.setPrefWidth(100);
		
		
		
		TextField id = new TextField();
		id.setPrefWidth(100);
		TextField specialization = new TextField();
		specialization.setPrefWidth(100);
		

	   specializationTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
		   if (nv != null) {
			id.setText(String.valueOf(nv.getSpecializationid()));
			specialization.setText(nv.getSpecname());
			
			}
		});

		// save the content on commit
		Button commit = new Button("Commit");
	   commit.setPrefWidth(100);
		Button cancel = new Button("Cancel");
       cancel.setPrefWidth(100);
		commit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				Specializationdao specdao =new Specializationdao();
				Specialization item = specializationTable.getSelectionModel().getSelectedItem();
				item.setSpecializationid(Integer.parseInt(id.getText()));
				item.setSpecname(specialization.getText());
				specdao.update(item);
				showAlert(Alert.AlertType.INFORMATION,"Record Updated!","Specialization id " + item.getSpecializationid() + " is updated in the database");

				specializationTable.toFront();
			}
		});
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				specializationTable.toFront();
			}
		});

		
        
		
		hbox1.getChildren().addAll(lid,lspec);

		hbox2.getChildren().addAll(id, specialization, commit,cancel);
		 
	    //vbox1.getChildren().addAll(hbox1,hbox2);

		specializationTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
	}

	private void bindData() {

		// DaoModel model = new DaoModel();
		Specializationdao specdao = new Specializationdao();

		List<Specialization> resultSet = specdao.getRecords(new Specialization());
		for (Specialization doc : resultSet) {
			specializations.add(doc);
		}

		specializationTable.setItems(specializations);

		/*
		 * try { while (resultSet.next()) {
		 * 
		 * Specialization doc = new Specialization();
		 * 
		 * doc.setDoctor_Id(Integer.parseInt(resultSet.getString(1)));
		 * doc.setFname(resultSet.getString(2)); specializations.add(doc); }
		 * specializationTable.setItems(specializations); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
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
					Specialization currentPerson = (Specialization) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
					// remove selected item from the table list
					specializations.remove(currentPerson);
					Specialization spec =new Specialization();
					Specializationdao specdao= new Specializationdao();
					//System.out.println("currentPerson is" + currentPerson.getMedicine_id);
					spec.setSpecializationid(currentPerson.getSpecializationid());
				    specdao.delete(spec);
					showAlert(Alert.AlertType.INFORMATION,"Record deleted!","Specialization id " + currentPerson.getSpecializationid() + " is deleted from  the database");
					
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