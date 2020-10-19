package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.opencsv.CSVWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.Main;
import model.Profile;
import utils.Helpers;

public class ProfileControllerAdd implements Initializable {

	@FXML private JFXButton btnCancel;
	@FXML private JFXTextField txtFirstName, txtLastName, txtLine1, txtLine2, txtTown, txtCountryName, txtPostalCode,
	txtCC, txtCCExpiryMonth, txtCCExpiryYear, txtCCSecurityCode, txtPhone, txtEmail;
	@FXML private JFXComboBox<String> cmbRegionName;
	@FXML private Label lblError;
	private int profileId = 0;
	public static boolean isCancel = false;
	public ObservableList<String> regionNameList = FXCollections.observableArrayList("AL", "AK", "AS", "AZ", "AR", "AA", "AE", "AP", "CA", "CO", "CT", 
			"DE", "DC", "FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MH", "MD", "MI", "FM", "MN", "MS", "MO", "MT", "NE", "NV",
			"NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UM", "VI", "UT", "VT", "VA", "WA", 
			"WV", "WI", "WY");


	ObservableList<Profile> profileData = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cmbRegionName.getItems().setAll(regionNameList);
		//cmbRegionName.setValue("AS");
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
		txtCountryName.setText(p.getCountryCode());
		txtPostalCode.setText(p.getPostalCode());
		txtCC.setText(p.getCcNumber());
		txtCCSecurityCode.setText(p.getCcSecurityCode());
		txtPhone.setText(p.getPhone());
		txtEmail.setText(p.getEmailAddress());
		txtCCExpiryMonth.setText(p.getCcExpMonth());
		txtCCExpiryYear.setText(p.getCcExpYear());
		cmbRegionName.setValue(p.getRegionName());

	}

	@FXML
	private void save() throws SQLException, IOException {
		isCancel = false;
		Profile p = new Profile();
		p.setProfileNumber(profileId);
		p.setFirstName(txtFirstName.getText());
		p.setLastName(txtLastName.getText());
		p.setLine1(txtLine1.getText());
		p.setLine2(txtLine2.getText());
		p.setTown(txtTown.getText());
		p.setCountryCode(txtCountryName.getText());
		p.setPostalCode(txtPostalCode.getText());
		p.setCcNumber(txtCC.getText());
		//bej set-et e reja nga txt fields e reja qe do shtoje ergi
		p.setCcExpMonth(txtCCExpiryMonth.getText());
		p.setCcExpYear(txtCCExpiryYear.getText());
		p.setRegionName(cmbRegionName.getValue());

		p.setCcSecurityCode(txtCCSecurityCode.getText());
		p.setPhone(txtPhone.getText());
		p.setEmailAddress(txtEmail.getText());
		p.setProfileNumber(profileId);

		String csv = getTargetPath().replace("target", "") + "src\\main\\java\\profile.csv";

		CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
		String [] record = (txtFirstName.getText().toString() == "" ? txtFirstName.getText().toString() : " " + 
				"," + txtLastName.getText().toString() + ","
				+ txtLine1.getText().toString() + "," + 
				txtLine2.getText().toString() +
				"," + txtTown.getText().toString() + ","
				+ cmbRegionName.getValue() + ","
				+ txtPostalCode.getText().toString() + "," + 
				txtCC.getText().toString() +
				"," + txtCountryName.getText().toString()
				+ "," + txtCCExpiryMonth.getText().toString() + "," 
				+ txtCCExpiryYear.getText().toString() + 
				"," + txtCCSecurityCode.getText().toString()
				+ "," + txtPhone.getText().toString() + "," 
				+ txtEmail.getText().toString()).split(",");
		writer.writeNext(record);
		writer.close();

		Helpers.close_stage(btnCancel);


	}

	public static String getTargetPath() {
		CodeSource codeSource = Main.class.getProtectionDomain().getCodeSource();
		File jarFile = null;
		try {
			jarFile = new File(codeSource.getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String jarDir = jarFile.getParentFile().getPath();
		return jarDir;
	}

	@FXML
	private void cancel() {
		isCancel = true;
		utils.Helpers.close_stage(btnCancel);
	}

}


