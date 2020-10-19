package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class test implements Initializable{

	@FXML
	Text test;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//test.setText("sdfdsf");
		test.lineSpacingProperty().add(5.6);
		System.out.println(test.getLineSpacing());
		
	}

}
