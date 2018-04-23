package controllers;

import java.io.IOException;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/** Controls the main application screen */
public class FXLoginController {
  
  @FXML private Button buttonlogin;
  @FXML private Label  lbladmin;
  @FXML private TextField  txtusername;
  @FXML private TextField  txtpassword;

 
  
  public void login(ActionEvent event) throws IOException
  {
	  if  (txtusername.getText().equals("admin") && txtpassword.getText().equals("admin@123") )
	  {
		  lbladmin.setText("Login Success");
		  Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main.fxml"));
		  Stage primaryStage = new  Stage();
		  primaryStage.setScene(new Scene(root));
		  primaryStage.show();

		  		  
	  }
		  
	  else
	  {
		  lbladmin.setText("Login Failed");
		  
		  
	  }
  }
  
}
  
