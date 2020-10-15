package utils;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Helpers {
	 
	public Helpers() {
		
	}
   

    public void open_edit_scene(String view_name, String icon) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+view_name+".fxml")); 
		Parent root=(Parent)loader.load();
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		Stage stage = new Stage();
		stage.initStyle(StageStyle.TRANSPARENT); 
		stage.getIcons().add(new Image("/images/"+icon+".png"));
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}
    
    public static void close_stage(JFXButton button) {
		Stage stage = (Stage) button.getScene().getWindow();
		stage.close();
	}
    
    public static void alert(String title, String text, AlertType type) {
		Alert alert = new Alert (type,text);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.showAndWait();
	}
}