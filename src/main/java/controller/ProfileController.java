package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Profile;

public class ProfileController implements Initializable {

	@FXML private TableView<Profile> tblProfile;
	@FXML private TableColumn<Profile, String> tblcolProfileNumber, tblcolFirstName, tblcolLastName;
	
	ObservableList<Profile> profileData = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fillData();
	}

	private void fillData() {
		Profile row1 = new Profile();
		row1.setFirstName("Nensi");
		row1.setLastName("Skenderi");
		
		Profile row2 = new Profile();
		row2.setFirstName("E");
		row2.setLastName("K");
		
		profileData.addAll(row1, row2);
		
		tblcolFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		tblcolLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		
		
		tblProfile.setItems(profileData);
	}

}
