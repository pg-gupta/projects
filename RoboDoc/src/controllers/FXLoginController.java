package controllers;

import java.io.IOException;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.User;
import models.Userdao;
/**
 * 
 * @author Yeshwanthi & Pooja 
 * Date: 04/27/2018
 * Lab: Final project
 */
/**
 * 
 * Controller which manages operations and event on the Login view
 *
 */
/** Controls the main application screen */
public class FXLoginController {

	@FXML
    private AnchorPane rootAnchor;
	@FXML
	private Button buttonlogin;
	@FXML
	private Label lbladmin;
	@FXML
	private TextField txtusername;
	@FXML
	private TextField txtpassword;

	public void login(ActionEvent event) throws IOException {
		String username = txtusername.getText();
		String password = txtpassword.getText();
		if (username != "" && password != "") {
			Userdao userdao = new Userdao();
			User user = userdao.getByName(username);
			if (user != null && user.getUpassword().equals(password)) {
				if (user.getIsadmin()) {
					lbladmin.setText("Login Success");
					Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main.fxml"));
					
					rootAnchor.getChildren().setAll(root);
//					Stage primaryStage = new Stage();
//					primaryStage.setScene(new Scene(root));
//					primaryStage.show();
					
				} else
					showAlert(AlertType.ERROR, "Login Failed", "Unauthorized access!");
			} else
				showAlert(AlertType.ERROR, "Login Failed", "Credentials are incorrect!");

		} else
			showAlert(AlertType.ERROR, "Login Failed", "Credentials are required!");
	}

	public void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}
}
