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
		p.setFirstName(!txtFirstName.getText().toString().isEmpty() ? txtFirstName.getText().toString() : " ");
		p.setLastName(!txtLastName.getText().toString().isEmpty() ? txtLastName.getText().toString() : " ");
		p.setLine1(!txtLine1.getText().toString().isEmpty() ? txtLine1.getText().toString() : " ");
		p.setLine2(!txtLine2.getText().toString().isEmpty() ? txtLine2.getText().toString() : " ");
		p.setTown(!txtTown.getText().toString().isEmpty() ? txtTown.getText().toString() : " ");
		p.setCountryCode(!txtCountryName.getText().toString().isEmpty() ? txtCountryName.getText().toString() : " ");
		p.setPostalCode(!txtPostalCode.getText().toString().isEmpty() ? txtPostalCode.getText().toString() : " ");
		p.setCcNumber(!txtCC.getText().toString().isEmpty() ? txtCC.getText().toString() : " ");
		p.setCcExpMonth(!txtCCExpiryMonth.getText().toString().isEmpty() ? txtCCExpiryMonth.getText().toString() : " ");
		p.setCcExpYear(!txtCCExpiryYear.getText().toString().isEmpty() ? txtCCExpiryYear.getText().toString() : " ");
		p.setRegionName(cmbRegionName.getValue());
		p.setCcSecurityCode(!txtCCSecurityCode.getText().toString().isEmpty() ? txtCCSecurityCode.getText().toString() : " ");
		p.setPhone(!txtPhone.getText().toString().isEmpty() ? txtPhone.getText().toString() : " ");
		p.setEmailAddress(!txtEmail.getText().toString().isEmpty() ? txtEmail.getText().toString() : " ");
		p.setProfileNumber(profileId);

	//	String csv = getTargetPath().replace("target", "") + "src\\main\\java\\profile.csv";
		String csv = System.getProperty("user.home") + "/Desktop/profile.csv";
		CSVWriter writer =
				new CSVWriter(
						new FileWriter(csv, true),
						CSVWriter.DEFAULT_SEPARATOR,
						CSVWriter.NO_QUOTE_CHARACTER,
						CSVWriter.DEFAULT_ESCAPE_CHARACTER,
						CSVWriter.DEFAULT_LINE_END);

		String [] record = (p.getFirstName() + "," + p.getLastName() + ","  + p.getLine1() + "," +
				p.getLine2()  +  "," + p.getTown() + ","  + p.getRegionName() + "," + p.getPostalCode() + "," +
				p.getCcNumber()  +  "," + p.getCountryCode()  + ","  + p.getCcExpMonth()  + "," 
				+ p.getCcExpYear()  + "," + p.getCcSecurityCode()   + "," + p.getPhone()  + "," 
				+ p.getEmailAddress()).split(",");
		
		writer.writeNext(record);
		writer.flush();
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


