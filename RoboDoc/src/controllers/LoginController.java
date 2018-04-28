package controllers;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/** Controls the main application screen */
public class LoginController {
  
  @FXML private Button buttonlogin;
  @FXML private Label  lbladmin;
  @FXML private TextField  txtusername;
  @FXML private TextField  txtpassword;

  
  
  public void login(ActionEvent event)
  {
	  if  (txtusername.getText().equals("admin") && txtpassword.getText().equals("admin@123") )
	  {
		  lbladmin.setText("Login Success");
		  		  
	  }
		  
	  else
	  {
		  lbladmin.setText("Login Failed");
		  
		  
	  }
  }
  
}
  
