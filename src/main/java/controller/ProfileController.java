package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Profile;
import utils.Helpers;


public class ProfileController implements Initializable {

	@FXML private TableView<Profile> tblProfile;
	@FXML private TableColumn<Profile, String> tblcolProfileNumber, tblcolFirstName, tblcolLastName,
	tblcolLine1, tblcolLine2, tblcolTown, tblcolRegionName, tblcolPostalCode, tblcolCC,
	tblcolCCSecurityCode, tblcolPhone, tblcolEmail;
	@FXML private JFXButton btnAdd, btnEdit;
	@FXML private JFXTextField txtSearch;
	public static Profile profileDataHolder = new Profile();
	public static boolean edit = false;
	
	ObservableList<Profile> profileData = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			fillData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void fillData() throws SQLException {
		tblProfile.getItems().clear();
		profileData.addAll(ControlDAO.getControlDao().getProfileDao().viewProfiles());

		tblcolProfileNumber.setCellValueFactory(new PropertyValueFactory<>("profileId"));
		tblcolFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		tblcolLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		tblcolLine1.setCellValueFactory(new PropertyValueFactory<>("line1"));
		tblcolLine2.setCellValueFactory(new PropertyValueFactory<>("line2"));
		tblcolTown.setCellValueFactory(new PropertyValueFactory<>("town"));
		tblcolRegionName.setCellValueFactory(new PropertyValueFactory<>("regionName"));
		tblcolPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
		tblcolCC.setCellValueFactory(new PropertyValueFactory<>("CC"));
		tblcolCCSecurityCode.setCellValueFactory(new PropertyValueFactory<>("CCSecurityCode"));
		tblcolPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		tblcolEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		
		tblProfile.setItems(profileData);
	}
	
	private void getData() throws IOException, SQLException {
		Profile profile = tblProfile.getSelectionModel().getSelectedItem();
		profileDataHolder.setProfileNumber(profile.getProfileNumber());
		profileDataHolder.setFirstName(profile.getFirstName());
		profileDataHolder.setLastName(profile.getLastName());
		profileDataHolder.setLine1(profile.getLine1());
		profileDataHolder.setLine2(profile.getLine2());
		profileDataHolder.setTown(profile.getTown());
		profileDataHolder.setRegionName(profile.getRegionName());
		profileDataHolder.setPostalCode(profile.getPostalCode());
		profileDataHolder.setCc(profile.getCc());
		profileDataHolder.setCcSecurityCode(profile.getCcSecurityCode());
		profileDataHolder.setPhone(profile.getPhone());
		profileDataHolder.setEmailAddress(profile.getEmailAddress());

		new utils.Helpers().open_edit_scene("ProfileAdd", "icon");
		fillData();
	}
	
	public void searchTableview() {
		txtSearch.textProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable o) {

				if(txtSearch.textProperty().get().isEmpty()) {
					tblProfile.setItems(profileData);
					return;
				}

				ObservableList<Profile> tableItems = FXCollections.observableArrayList();
				ObservableList<TableColumn<Profile, ?>> cols = tblProfile.getColumns();

				for(int i=0; i<profileData.size(); i++) {
					for(int j=0; j<4; j++) {
						TableColumn<Profile, ?> col = cols.get(j);
						String cellValue = col.getCellData(((List<Profile>) tblProfile).get(i)).toString();
						cellValue = cellValue.toLowerCase();
						if(cellValue.contains(txtSearch.textProperty().get().toLowerCase())) {
							tableItems.add(((List<Profile>) tblProfile).get(i));
							break;
						}                        
					}
				}

				tblProfile.setItems(tableItems);
			}
		});
	}
	
	@FXML
	private void add() throws IOException, SQLException {
		edit = false;
		new utils.Helpers().open_edit_scene("ProfileAdd", "icon");
		fillData();
	}
	
	@FXML
	private void edit() throws IOException, SQLException {
		edit = true;
		if(tblProfile.getSelectionModel().getSelectedItem() != null)  
			getData();
			else
				Helpers.alert("STOP!", "Pick a row from the table!", AlertType.WARNING);
	}

}

