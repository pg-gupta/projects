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
		bindData();

		
	}

		// add delete button on row

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

	
	

}