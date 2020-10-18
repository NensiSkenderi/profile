package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.opencsv.CSVWriter;

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
		if(ProfileController.edit == true) {
			profileId = ProfileController.profileDataHolder.getProfileNumber();
			getData(ProfileController.profileDataHolder);
		}
		else
			profileId = 0;
	}

	private void getData(Profile p) {
		txtFirstName.setText(p.getFirstName()); 
		txtLastName.setText(p.getLastName());
		txtLine1.setText(p.getLine1());
		txtLine2.setText(p.getLine2());
		txtTown.setText(p.getTown());
		txtRegionName.setText(p.getRegionName());
		txtPostalCode.setText(p.getPostalCode());
		//txtCC.setText(p.getCc());
		txtCCSecurityCode.setText(p.getCcSecurityCode());
		txtPhone.setText(p.getPhone());
		txtEmail.setText(p.getEmailAddress());


	}

	@FXML
	private void save() throws SQLException, IOException {
		Profile p = new Profile();
		p.setProfileNumber(profileId);
		p.setFirstName(txtFirstName.getText());
		p.setLastName(txtLastName.getText());
		p.setLine1(txtLine1.getText());
		p.setLine2(txtLine2.getText());
		p.setTown(txtTown.getText());
		p.setRegionName(txtRegionName.getText());
		p.setPostalCode(txtPostalCode.getText());

		//bej set-et e reja nga txt fields e reja qe do shtoje ergi

		p.setCcSecurityCode(txtCCSecurityCode.getText());
		p.setPhone(txtPhone.getText());
		p.setEmailAddress(txtEmail.getText());
		p.setProfileNumber(profileId);


//		FileWriter pw = new FileWriter("F:\\data.csv");
//		Iterator s = customerIterator(); --jep error
//		if (s.hasNext()==false){
//			System.out.println("Empty");
//		}
//		while(s.hasNext()){
//			Profile profile  = (Profile) s.next();
//			System.out.println(profile.toString()+"\n");
//			pw.append(profile.getFirstName());
//			pw.append(",");
//			pw.append(profile.getLastName());
//			pw.append("\n");
//		}
//		pw.flush();
//		pw.close();


		//menyra 2 - te perdorim open csv
		String csv = "profile.csv";
		CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
		String [] record = "3,David,Feezor,USA,40".split(",");
		writer.writeNext(record);
		writer.close();

		Helpers.close_stage(btnCancel);
	}

	@FXML
	private void cancel() {
		utils.Helpers.close_stage(btnCancel);
	}

}
