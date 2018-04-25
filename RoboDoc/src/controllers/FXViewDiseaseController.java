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
import models.Disease;
import models.Diseasedao;

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
	private TableColumn<Disease, String> iscontagiousCol ;
	
	@FXML
	private TableColumn<Disease, String> preffereddietCol;
	
	@FXML
	private TableColumn<Disease, String> testsuggested;
	
	@FXML
	private TableColumn<Disease, String> specializationidCol;
	
	
	@FXML
	private Label lid;
	
	@FXML
	private Label lname;
	
	@FXML
	private Label ltreatment;
	
	@FXML
	private Label lprevention;
	
	@FXML
	private Label lcontagious;
	
	@FXML
	private Label ldiet;
	
	@FXML
	private Label ltest;
	
	
	@FXML
	private Label lspecializationid;
	
	
	@FXML
	private TextField id;
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField treatment;
	
	@FXML
	private TextField prevention;
	
	@FXML
	private TextField contagious;
	
	@FXML
	private TextField diet;
	
	@FXML
	private TextField testsuggested;
	
	@FXML
	private TextField specializationid;
	
	
	
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
		testsuggested.setCellValueFactory(new PropertyValueFactory<Disease, String>("test_suggested"));
		specializationidCol.setCellValueFactory(new PropertyValueFactory<Disease, String>("specializationid"));





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
		/*Label lid= new Label("Id");
		lid.setPrefWidth(100);
		Label lname= new Label("Name");
		lname.setPrefWidth(100);
		Label ltreatment= new Label("Treatment");
		ltreatment.setPrefWidth(100);
		Label lprevention= new Label("Prevention");
		lprevention.setPrefWidth(100);
		Label lcontagious= new Label("Contagious");
		lcontagious.setPrefWidth(100);
		Label ldiet= new Label("Diet");
		ldiet.setPrefWidth(100);
		Label ltest= new Label("Test Suggested");
		ltest.setPrefWidth(100);
		Label laction1= new Label("Action");
		laction1.setPrefWidth(100);
		Label laction2= new Label("Action");
		laction2.setPrefWidth(100);
		Label lspecializationid= new Label("Specializationid");
		lspecializationid.setPrefWidth(100);
		
		
		TextField id = new TextField();
		id.setPrefWidth(100);
		TextField name = new TextField();
		name.setPrefWidth(100);
		TextField treatment = new TextField();
		treatment.setPrefWidth(100);
		TextField prevention = new TextField();
		prevention.setPrefWidth(100);
		TextField iscontagious = new TextField();
		iscontagious.setPrefWidth(100);
		TextField preffereddiet = new TextField();
		preffereddiet.setPrefWidth(100);
		TextField testsuggested = new TextField();
		testsuggested.setPrefWidth(100);
		TextField specializationid = new TextField();
		specializationid.setPrefWidth(100);*/



	   diseaseTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
		   if (nv != null) {
			id.setText(String.valueOf(nv.getDisease_id()));
			name.setText(nv.getDisease_name());
			prevention.setText(nv.getPrevention());
			diet.setText(nv.getPreffered_diet());
			treatment.setText(nv.getTreatment());
			contagious.setText(nv.getIs_contagious());
			testsuggested.setText(nv.getTest_suggested());
			specializationid.setText(String.valueOf(nv.getSpecializationid()));

			

			}
		});

		// save the content on commit
		//Button commit = new Button("Commit");
		//Button cancel = new Button("Commit");

		commit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				Diseasedao diseasedao =new Diseasedao();
				Disease item = diseaseTable.getSelectionModel().getSelectedItem();
				item.setDisease_id(Integer.parseInt(id.getText()));
				item.setDisease_name(name.getText());
				item.setPrevention(prevention.getText());
				item.setPreffered_diet(diet.getText());
				item.setTreatment(treatment.getText());
				item.setIs_contagious(contagious.getText());
				item.setTest_suggested(testsuggested.getText());
				item.setSpecializationid(Integer.parseInt(specializationid.getText()));


				diseasedao.update(item);
				showAlert(Alert.AlertType.INFORMATION,"Record Updated!","Disease id " + item.getDisease_id() + " is updated in the database");

				diseaseTable.toFront();
			}
		});
		


		//hbox1.getChildren().addAll(lid,lname,ltreatment,lprevention,lcontagious,ldiet,ltest,lspecializationid);

		//hbox2.getChildren().addAll(id, name,prevention,preffereddiet,treatment,iscontagious,testsuggested,specializationid,commit,cancel);
		 
	    //vbox1.getChildren().addAll(hbox1,hbox2);

		diseaseTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
		Diseasedao Diseasedao = new Diseasedao();

		List<Disease> resultSet = Diseasedao.getRecords(new Disease());
		for (Disease doc : resultSet) {
			diseases.add(doc);
		}

		diseaseTable.setItems(diseases);

		/*
		 * try { while (resultSet.next()) {
		 * 
		 * Disease doc = new Disease();
		 * 
		 * doc.setDoctor_Id(Integer.parseInt(resultSet.getString(1)));
		 * doc.setFname(resultSet.getString(2)); diseases.add(doc); }
		 * DiseaseTable.setItems(diseases); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
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
					Disease currentPerson = (Disease) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
					// remove selected item from the table list
					//diseases.remove(currentPerson);
					Disease disease =new Disease();
					Diseasedao diseasedao= new Diseasedao();
					//System.out.println("currentPerson is" + currentPerson.getMedicine_id);
					disease.setDisease_id(currentPerson.getDisease_id());
					diseasedao.delete(disease);
					showAlert(Alert.AlertType.INFORMATION,"Record deleted!","Disease id " + currentPerson.getDisease_id() + " is deleted from  the database");
					
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