package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;



import javafx.stage.Stage;

public class MainView extends Application {
	
	

	public static void main(String[] args) {
		launch(args);
		
		//@SuppressWarnings("unchecked")
	    //org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
	    //java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
	    
	}
	
	
	
	@Override
	public void start(Stage stage) throws Exception {
		try {

			//Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));

			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
