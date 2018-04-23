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
import models.Patient;
import models.Patientdao;
import models.Person;
import models.Persondao;

public class FXViewPatientController implements Initializable {

	@FXML
	private AnchorPane main;

	@FXML
	private TableView<Patient> patientTable;

	@FXML
	private TableColumn<Patient, String> idCol;

	@FXML
	private TableColumn<Patient, String> fnameCol;
	
	@FXML
	private TableColumn<Patient, String> lnameCol;

	@FXML
	private TableColumn<Patient, String> ageCol;

	@FXML
	private TableColumn<Patient, String> sexCol;

	@FXML
	private TableColumn<Patient, String> emailCol;

	@FXML
	private TableColumn<Patient, String> phonenoCol;

	@FXML
	private TableColumn<Patient, String> addCol;
	
	@FXML
	private TableColumn<Patient, String> heightCol;

	@FXML
	private TableColumn<Patient, String> weightCol;

	@FXML
	private TableColumn<Patient, String> diabeticCol;



	@FXML
	private VBox vbox1;
	

	@FXML
	private HBox hbox1;
	@FXML
	private HBox hbox2;

	ObservableList<Patient> patients = FXCollections.observableArrayList();

	public FXViewPatientController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("id"));
		fnameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("fname"));
		lnameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("lname"));
		ageCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("age"));
		sexCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("sex"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("email"));
		phonenoCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("phonenumber"));
		addCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("address"));
		heightCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("height"));
		weightCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("weight"));
		diabeticCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("isDiabetic"));


		// add delete button on row
		TableColumn<Patient, Boolean> col_action = new TableColumn<Patient, Boolean>("Action");

		patientTable.getColumns().add(col_action);

		col_action.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Patient, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Patient, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});

		// Adding the Button to the cell
		col_action.setCellFactory(new Callback<TableColumn<Patient, Boolean>, TableCell<Patient, Boolean>>() {

			@Override
			public TableCell<Patient, Boolean> call(TableColumn<Patient, Boolean> p) {
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

		patientTable.toFront();
		Label lid = new Label("Id");
		lid.setPrefWidth(100);
		Label lfname = new Label("Fname");
		lfname.setPrefWidth(100);
		Label llname = new Label("Lname");
		llname.setPrefWidth(100);
		Label lage = new Label("Age");
		lage.setPrefWidth(100);
		Label lsex = new  Label("Sex");
		lsex.setPrefWidth(100);
		Label lemail = new Label("Email");
		lemail.setPrefWidth(100);
		Label lphoneno = new Label("Phone");
		lphoneno.setPrefWidth(100);
		Label ladd = new Label("Address");
		ladd.setPrefWidth(100);
		Label lheight = new Label("Height");
		lheight.setPrefWidth(100);
		Label lweight= new Label("Weight");
		lweight.setPrefWidth(100);
		Label ldiabetic = new Label("Diabetic");
		ldiabetic.setPrefWidth(100);

		

		TextField id = new TextField();
		id.setPrefWidth(100);
		TextField fname = new TextField();
		fname.setPrefWidth(100);
		TextField lname = new TextField();
		lname.setPrefWidth(100);
		TextField age = new TextField();
		age.setPrefWidth(100);
		TextField sex = new TextField();
		sex.setPrefWidth(100);
		TextField email = new TextField();
		email.setPrefWidth(100);
		TextField phoneno = new TextField();
		phoneno.setPrefWidth(100);
		TextField add = new TextField();
		add.setPrefWidth(100);
		TextField height = new TextField();
		height.setPrefWidth(100);
		TextField weight = new TextField();
		weight.setPrefWidth(100);
		TextField diabetic = new TextField();
		diabetic.setPrefWidth(100);



	   patientTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
		   if (nv != null) {
			id.setText(String.valueOf(nv.getId()));
			fname.setText(nv.getFname());
			lname.setText(nv.getLname());
			age.setText(String.valueOf(nv.getAge()));
			sex.setText(nv.getSex());
			email.setText(nv.getEmail());
			phoneno.setText(nv.getPhonenumber());
			add.setText(nv.getAddress());
			height.setText(String.valueOf(nv.getHeight()));
			weight.setText(String.valueOf(nv.getWeight()));
			diabetic.setText(String.valueOf(nv.getIsDiabetic()));



			}
		});

		// save the content on commit
		Button commit = new Button("Commit");
		Button cancel = new Button("Cancel");

		commit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				Persondao perdao =new Persondao();
				Person person =new Person();
				Patientdao patientdao=new Patientdao();
				Patient item = patientTable.getSelectionModel().getSelectedItem();
				/*person.setFname(fname.getText());
				person.setLname(lname.getText());
				person.setAge(Integer.parseInt(age.getText()));
				person.setSex(sex.getText());
				person.setEmail(email.getText());
				person.setPhonenumber(phoneno.getText());
				person.setAddress(add.getText());*/
				item.setFname(fname.getText());
				item.setLname(lname.getText());
				item.setAge(Integer.parseInt(age.getText()));
				item.setSex(sex.getText());
				item.setEmail(email.getText());
				item.setPhonenumber(phoneno.getText());
				item.setAddress(add.getText());
				item.setHeight(Integer.parseInt(height.getText()));
				item.setWeight(Integer.parseInt(weight.getText()));
				item.setIsDiabetic(diabetic.getText());
				//perdao.update(person);
				patientdao.update(item);
				showAlert(Alert.AlertType.INFORMATION,"Record Updated!","Patient id " + item.id + " is updated in the database");

				patientTable.toFront();
			}
		});
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				patientTable.toFront();
			}
		});

        
		hbox1.getChildren().addAll(lid, lfname,llname,lage,lsex,lemail,lphoneno,ladd,lheight,lweight,ldiabetic);

		hbox2.getChildren().addAll(id, fname,lname,age,sex,email,phoneno,add,height,weight,diabetic, commit,cancel);

		patientTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
		Patientdao Patientdao = new Patientdao();

		List<Patient> resultSet = Patientdao.getRecords(new Patient());
		for (Patient pt : resultSet) {
			patients.add(pt);
		}

		patientTable.setItems(patients);

		/*
		 * try { while (resultSet.next()) {
		 * 
		 * Patient pt = new Patient();
		 * 
		 * pt.setDoctor_Id(Integer.parseInt(resultSet.getString(1)));
		 * pt.setFname(resultSet.getString(2)); patients.add(pt); }
		 * patientTable.setItems(patients); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}

	
	
	// Define the button cell
	class ButtonCell extends TableCell<Patient, Boolean> {
		final Button cellButton = new Button("Delete");

		ButtonCell() {

			// Action when the button is pressed
			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					// get Selected Item
					Patient currentPerson = (Patient) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
					// remove selected item from the table list
					//patients.remove(currentPerson);
					Persondao perdao =new Persondao();
					Patient pt =new Patient();
					Patientdao ptdao= new Patientdao();
					System.out.println("currentPerson is" + currentPerson.id);
					pt.setId(currentPerson.id);
					ptdao.delete(pt);
					perdao.delete(pt);
					showAlert(Alert.AlertType.INFORMATION,"Record deleted!","Patient id " + currentPerson.id + " is deleted from  the database");
					
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