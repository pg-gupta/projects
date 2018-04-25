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
import models.Doctor;
import models.Doctordao;
import models.Person;
import models.Persondao;

public class FXViewDoctorController implements Initializable {

	@FXML
	private AnchorPane main;

	@FXML
	private TableView<Doctor> doctorTable;

	@FXML
	private TableColumn<Doctor, String> idCol;

	@FXML
	private TableColumn<Doctor, String> fnameCol;
	
	@FXML
	private TableColumn<Doctor, String> lnameCol;

	@FXML
	private TableColumn<Doctor, String> ageCol;

	@FXML
	private TableColumn<Doctor, String> sexCol;

	@FXML
	private TableColumn<Doctor, String> emailCol;

	@FXML
	private TableColumn<Doctor, String> phonenoCol;

	@FXML
	private TableColumn<Doctor, String> addCol;
	
	@FXML
	private TableColumn<Doctor, String> specializationCol;

	@FXML
	private TableColumn<Doctor, String> visithoursCol;

	@FXML
	private TableColumn<Doctor, String> degreeCol;
	
	@FXML
	private TableColumn<Disease, String> specializationidCol;
	
	@FXML
	private Label lid;
	
	@FXML
	private Label llname;
	
	@FXML
	private Label lfname;
	
	@FXML
	private Label lage;
	
	@FXML
	private Label lsex;
	
	@FXML
	private Label lemail;
	
	@FXML
	private Label lphoneno;
	
	@FXML
	private Label ladd;
	
	@FXML
	private Label lvisithours;
	
	@FXML
	private Label lspecialization;
	
	@FXML
	private Label ldegree;
	
	@FXML
	private Label lspecializationid;
	
	
	@FXML
	private TextField id;
	
	@FXML
	private TextField lname;
	
	@FXML
	private TextField fname;
	
	@FXML
	private TextField age;
	
	@FXML
	private TextField sex;
	
	@FXML
	private TextField email;
	
	@FXML
	private TextField phoneno;
	
	@FXML
	private TextField add;
	
	@FXML
	private TextField visithours;
	
	@FXML
	private TextField specialization;
	
	@FXML
	private TextField degree;
	
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

	ObservableList<Doctor> doctors = FXCollections.observableArrayList();

	public FXViewDoctorController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("id"));
		fnameCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("fname"));
		lnameCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("lname"));
		ageCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("age"));
		sexCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("sex"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("email"));
		phonenoCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("phonenumber"));
		addCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("address"));
		specializationCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("specialization"));
		visithoursCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("visithours"));
		degreeCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("degree"));
		specializationidCol.setCellValueFactory(new PropertyValueFactory<Disease, String>("specializationid"));



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
		/*Label lid = new Label("Id");
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
		Label lspecialization = new Label("Specialization");
		lspecialization.setPrefWidth(100);
		Label lvisithours= new Label("Visiting Hours");
		lvisithours.setPrefWidth(100);
		Label ldegree = new Label("Degree");
		ldegree.setPrefWidth(100);
		Label lspecializationid= new Label("Specializationid");
		lspecializationid.setPrefWidth(100);
		

		

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
		TextField specialization = new TextField();
		specialization.setPrefWidth(100);
		TextField visithours = new TextField();
		visithours.setPrefWidth(100);
		TextField degree = new TextField();
		degree.setPrefWidth(100);
		TextField specializationid = new TextField();
		specializationid.setPrefWidth(100);*/




	   doctorTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
		   if (nv != null) {
			id.setText(String.valueOf(nv.getId()));
			fname.setText(nv.getFname());
			lname.setText(nv.getLname());
			age.setText(String.valueOf(nv.getAge()));
			sex.setText(nv.getSex());
			email.setText(nv.getEmail());
			phoneno.setText(nv.getPhonenumber());
			add.setText(nv.getAddress());
			specialization.setText(nv.getSpecialization());
			visithours.setText(nv.getVisithours());
			degree.setText(nv.getDegree());
			specializationid.setText(String.valueOf(nv.getSpecializationid()));




			}
		});

		// save the content on commit
		//Button commit = new Button("Commit");
		//Button cancel = new Button("Cancel");

		commit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				Persondao perdao =new Persondao();
				Person person =new Person();
				Doctordao Doctordao=new Doctordao();
				Doctor item = doctorTable.getSelectionModel().getSelectedItem();
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
				item.setSpecialization(specialization.getText());
				item.setVisithours(visithours.getText());
				item.setDegree(degree.getText());
				item.setSpecializationid(Integer.parseInt(specializationid.getText()));
				//perdao.update(person);
				Doctordao.update(item);
				showAlert(Alert.AlertType.INFORMATION,"Record Updated!","Doctor id " + item.id + " is updated in the database");

				doctorTable.toFront();
			}
		});
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				doctorTable.toFront();
			}
		});

        
		//hbox1.getChildren().addAll(lid, lfname,llname,lage,lsex,lemail,lphoneno,ladd,lspecialization,lvisithours,ldegree,lspecializationid);

		//hbox2.getChildren().addAll(id, fname,lname,age,sex,email,phoneno,add,specialization,visithours,degree,specializationid, commit,cancel);

		doctorTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
		// main.getChildren().add(root);
	}

	private void bindData() {

		// DaoModel model = new DaoModel();
		Doctordao Doctordao = new Doctordao();

		List<Doctor> resultSet = Doctordao.getRecords(new Doctor());
		for (Doctor pt : resultSet) {
			doctors.add(pt);
		}

		doctorTable.setItems(doctors);

		/*
		 * try { while (resultSet.next()) {
		 * 
		 * Doctor pt = new Doctor();
		 * 
		 * pt.setDoctor_Id(Integer.parseInt(resultSet.getString(1)));
		 * pt.setFname(resultSet.getString(2)); doctors.add(pt); }
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
					Doctor currentPerson = (Doctor) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
					// remove selected item from the table list
					//doctors.remove(currentPerson);
					Persondao perdao =new Persondao();
					Doctor pt =new Doctor();
					Doctordao ptdao= new Doctordao();
					System.out.println("currentPerson is" + currentPerson.id);
					pt.setId(currentPerson.id);
					ptdao.delete(pt);
					perdao.delete(pt);
					showAlert(Alert.AlertType.INFORMATION,"Record deleted!","Doctor id " + currentPerson.id + " is deleted from  the database");
					
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