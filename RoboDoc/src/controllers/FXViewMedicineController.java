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
import models.Medicine;
import models.Medicinedao;

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
	private Label lid;
	
	@FXML
	private Label lname;
	
	@FXML
	private Label ldrugs;
	
	@FXML
	private Label ldescription;
	
	@FXML
	private Label ldosage;
	
	@FXML
	private Label ldiseaseid;
	
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
	private VBox vbox1;
	

	@FXML
	private HBox hbox1;
	@FXML
	private HBox hbox2;

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
		/*Label lid= new Label("Id");
		lid.setPrefWidth(100);
		Label lname= new Label("Name");
		lname.setPrefWidth(100);
		Label ldrugs= new Label("Drugs");
		ldrugs.setPrefWidth(100);
		Label ldescription= new Label("Description");
		ldescription.setPrefWidth(100);
		Label ldosage= new Label("Dosage");
		ldosage.setPrefWidth(100);
		Label ldiseaseid= new Label("Disease id");
		ldiseaseid.setPrefWidth(100);
		Label laction1= new Label("Action");
		ldiseaseid.setPrefWidth(100);
		Label laction2= new Label("Action");
		ldiseaseid.setPrefWidth(100);*/
		
		
		/*TextField id = new TextField();
		id.setPrefWidth(100);
		TextField name = new TextField();
		name.setPrefWidth(100);
		TextField drugs = new TextField();
		drugs.setPrefWidth(100);
		TextField description = new TextField();
		description.setPrefWidth(100);
		TextField dosage = new TextField();
		dosage.setPrefWidth(100);
		TextField diseaseid = new TextField();
		diseaseid.setPrefWidth(100);*/


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

		// save the content on commit
		Button commit = new Button("Commit");
	    commit.setPrefWidth(100);
		Button cancel = new Button("Cancel");
        cancel.setPrefWidth(100);
		commit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				Medicinedao meddao =new Medicinedao();
				Medicine item = medicineTable.getSelectionModel().getSelectedItem();
				item.setMedicine_id(Integer.parseInt(id.getText()));
				item.setMedicine_name(name.getText());
				item.setDrugs(drugs.getText());
				item.setDescription(description.getText());
				item.setDosage(dosage.getText());
				item.setDisease_id(Integer.parseInt(diseaseid.getText()));
				meddao.update(item);
				showAlert(Alert.AlertType.INFORMATION,"Record Updated!","Medicine id " + item.getMedicine_id() + " is updated in the database");

				medicineTable.toFront();
			}
		});
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				medicineTable.toFront();
			}
		});

		
        
		
		//hbox1.getChildren().addAll(lid,lname,ldrugs,ldescription,ldosage,ldiseaseid);

		//hbox2.getChildren().addAll(id, name,drugs,description,dosage,diseaseid, commit,cancel);
		 
	   //vbox1.getChildren().addAll(hbox1,hbox2);

		medicineTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					StackPane.setMargin(vbox1, new Insets(evt.getSceneY(), 0, 0, 0));
					StackPane.setMargin(hbox1, new Insets(evt.getSceneY(), 0, 0, 0));
					StackPane.setMargin(hbox2, new Insets(evt.getSceneY(), 0, 0, 0));
					vbox1.toFront();
					//hbox1.toFront();
					//hbox2.toFront();
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

		/*
		 * try { while (resultSet.next()) {
		 * 
		 * Medicine doc = new Medicine();
		 * 
		 * doc.setDoctor_Id(Integer.parseInt(resultSet.getString(1)));
		 * doc.setFname(resultSet.getString(2)); medicines.add(doc); }
		 * medicineTable.setItems(medicines); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
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
					Medicine currentPerson = (Medicine) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
					// remove selected item from the table list
					medicines.remove(currentPerson);
					Medicine med =new Medicine();
					Medicinedao meddao= new Medicinedao();
					//System.out.println("currentPerson is" + currentPerson.getMedicine_id);
					med.setMedicine_id(currentPerson.getMedicine_id());
					meddao.delete(med);
					showAlert(Alert.AlertType.INFORMATION,"Record deleted!","Medicine id " + currentPerson.getMedicine_id() + " is deleted from  the database");
					
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