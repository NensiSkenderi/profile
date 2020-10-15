package utils;

import java.io.IOException;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Helpers {

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

	public static void styleDeleteButton(Button btn_delete) {
		btn_delete.setMaxWidth(20);
		btn_delete.setCursor(Cursor.HAND);
		btn_delete.getStyleClass().add("delete");
	}

	public static void alertDelete(JFXAlert alert,Button confirm,JFXButton cancel, String property, String imagePath) {
		alert.initModality(Modality.APPLICATION_MODAL);
		JFXDialogLayout layout = new JFXDialogLayout();
		layout.setBody(new Label("Are you sure you want to delete "+property+"?"));
		layout.setActions(confirm,cancel);

		alert.setContent(layout);
		alert.show();
	}

	public static void refreshFocusTable(TableView<?> tbl) {
		tbl.refresh();
		tbl.requestFocus();
	}

	public static void alert(String title, String text, AlertType type) {
		Alert alert = new Alert (type,text);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.showAndWait();
	}
}