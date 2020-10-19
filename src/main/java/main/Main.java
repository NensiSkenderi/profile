package main;
	
import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	
	public Parent root;
	
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {       
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Profile.fxml"));
    	root=(Parent)loader.load();
    	
    	Scene scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Profile");
    	primaryStage.getIcons().add(new Image("/images/icon.png"));
    	//primaryStage.setMaximized(true);
    	primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}