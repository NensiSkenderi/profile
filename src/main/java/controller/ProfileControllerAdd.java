package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Profile;
import utils.Helpers;

public class ProfileControllerAdd implements Initializable {

	@FXML private JFXButton btnCancel;
	@FXML private JFXTextField txtFirstName, txtLastName, txtLine1, txtLine2, txtTown, txtRegionName, txtPostalCode,
	txtCC, txtCCSecurityCode, txtPhone, txtEmail;
	@FXML private Label lblError;
	private int profileId = 0;
	
	ObservableList<Profile> profileData = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	
	@FXML
	private void save() throws SQLException {
		Profile p = new Profile();
		p.setProfileNumber(profileId);
		p.setFirstName(txtFirstName.getText());
		p.setLastName(txtLastName.getText());
		p.setLine1(txtLine1.getText());
		p.setLine2(txtLine2.getText());
		p.setTown(txtTown.getText());
		p.setRegionName(txtRegionName.getText());
		p.setPostalCode(txtPostalCode.getText());
		p.setCc(txtCC.getText());
		p.setCcSecurityCode(txtCCSecurityCode.getText());
		p.setPhone(txtPhone.getText());
		p.setEmailAddress(txtEmail.getText());
		
		if(profileId == 0) 
			ControlDAO.getControlDao().getProfileDao().addProfile(p);
		else 
			ControlDAO.getControlDao().getProfileDao().updateProfile(p);
		
		Helpers.close_stage(btnCancel);
	}
	
	@FXML
	private void cancel() {
		utils.Helpers.close_stage(btnCancel);
	}

}
