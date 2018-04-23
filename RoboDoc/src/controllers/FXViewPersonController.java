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
import models.Person;
import models.Persondao;

public class FXViewPersonController implements Initializable {

	@FXML
	private AnchorPane main;

	@FXML
	private TableView<Person> personTable;

	@FXML
	private TableColumn<Person, String> idCol;

	@FXML
	private TableColumn<Person, String> fnameCol;
	
	@FXML
	private TableColumn<Person, String> lnameCol;

	@FXML
	private TableColumn<Person, String> ageCol;

	@FXML
	private TableColumn<Person, String> sexCol;

	@FXML
	private TableColumn<Person, String> emailCol;

	@FXML
	private TableColumn<Person, String> phonenoCol;

	@FXML
	private TableColumn<Person, String> addCol;


	@FXML
	private VBox vbox1;
	

	@FXML
	private HBox hbox1;
	@FXML
	private HBox hbox2;

	ObservableList<Person> persons = FXCollections.observableArrayList();

	public FXViewPersonController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idCol.setCellValueFactory(new PropertyValueFactory<Person, String>("id"));
		fnameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("fname"));
		lnameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lname"));
		ageCol.setCellValueFactory(new PropertyValueFactory<Person, String>("age"));
		sexCol.setCellValueFactory(new PropertyValueFactory<Person, String>("sex"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
		phonenoCol.setCellValueFactory(new PropertyValueFactory<Person, String>("phonenumber"));
		addCol.setCellValueFactory(new PropertyValueFactory<Person, String>("address"));

		// add delete button on row
		TableColumn<Person, Boolean> col_action = new TableColumn<Person, Boolean>("Action");

		personTable.getColumns().add(col_action);

		col_action.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Person, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Person, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});

		// Adding the Button to the cell
		col_action.setCellFactory(new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {

			@Override
			public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> p) {
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

		personTable.toFront();
		
		Label lid = new Label();
		lid.setPrefWidth(100);
		Label lfname = new Label();
		lfname.setPrefWidth(100);
		Label llname = new Label();
		llname.setPrefWidth(100);
		Label lage = new Label();
		lage.setPrefWidth(100);
		Label lsex = new  Label();
		lsex.setPrefWidth(100);
		Label lemail = new Label();
		lemail.setPrefWidth(100);
		Label lphoneno = new Label();
		lphoneno.setPrefWidth(100);
		Label ladd = new Label();
		ladd.setPrefWidth(100);

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


	   personTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
		   if (nv != null) {
			id.setText(String.valueOf(nv.getId()));
			fname.setText(nv.getFname());
			lname.setText(nv.getLname());
			age.setText(String.valueOf(nv.getAge()));
			sex.setText(nv.getSex());
			email.setText(nv.getEmail());
			phoneno.setText(nv.getPhonenumber());
			add.setText(nv.getAddress());

			}
		});

		// save the content on commit
		Button commit = new Button("Commit");
		Button cancel = new Button("Cancel");

		commit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				Persondao perdao =new Persondao();
				Person item = personTable.getSelectionModel().getSelectedItem();
				item.setId(Integer.parseInt(id.getText()));
				item.setFname(fname.getText());
				item.setLname(lname.getText());
				item.setAge(Integer.parseInt(age.getText()));
				item.setSex(sex.getText());
				item.setEmail(email.getText());
				item.setPhonenumber(phoneno.getText());
				item.setAddress(add.getText());
				perdao.update(item);
				showAlert(Alert.AlertType.INFORMATION,"Record Updated!","Person id " + item.id + " is updated in the database");

				personTable.toFront();
			}
		});
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				personTable.toFront();
			}
		});

        
		hbox1.getChildren().addAll(lid, lfname,llname,lage,lsex,lemail,lphoneno,ladd);

		hbox2.getChildren().addAll(id, fname,lname,age,sex,email,phoneno,add, commit,cancel);

		personTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
		Persondao Persondao = new Persondao();

		List<Person> resultSet = Persondao.getRecords(new Person());
		for (Person per : resultSet) {
			persons.add(per);
		}

		personTable.setItems(persons);

		/*
		 * try { while (resultSet.next()) {
		 * 
		 * Person per = new Person();
		 * 
		 * per.setDoctor_Id(Integer.parseInt(resultSet.getString(1)));
		 * per.setFname(resultSet.getString(2)); persons.add(per); }
		 * personTable.setItems(persons); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}

	
	
	// Define the button cell
	class ButtonCell extends TableCell<Person, Boolean> {
		final Button cellButton = new Button("Delete");

		ButtonCell() {

			// Action when the button is pressed
			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					// get Selected Item
					Person currentPerson = (Person) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
					// remove selected item from the table list
					//persons.remove(currentPerson);
					Person per =new Person();
					Persondao perdao= new Persondao();
					System.out.println("currentPerson is" + currentPerson.id);
					per.setId(currentPerson.id);
					perdao.delete(per);
					showAlert(Alert.AlertType.INFORMATION,"Record deleted!","Person id " + currentPerson.id + " is deleted from  the database");
					
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