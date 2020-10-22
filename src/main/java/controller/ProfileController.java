package controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Profile;
import utils.Helpers;


public class ProfileController implements Initializable {

	@FXML private TableView<Profile> tblProfile;
	@FXML private TableColumn<Profile, String> tblcolProfileNumber, tblcolFirstName, tblcolLastName,
	tblcolLine1, tblcolLine2, tblcolTown, tblcolRegionName, tblcolPostalCode, tblcolCountryName, tblcolCcNumber,
	tblcolCcExpMonth, tblcolCcExpYear, tblcolCCSecurityCode, tblcolPhone, tblcolEmail;
	@FXML private TableColumn<Profile, Boolean> tblcolDelete;
	@FXML private JFXButton btnAdd, btnUpdateCsv;
	@FXML private JFXTextField txtSearch;
	public static Profile profileDataHolder = new Profile();
	public static boolean edit = false;

	ObservableList<Profile> profileData = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			File file = new File(System.getProperty("user.home") + "/Desktop/profile.csv");
			if(file.exists()) {
			fillData();
			searchTableview();
			}
			else
			{
				createCSV();
				fillData();
				searchTableview();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void fillData() throws IOException {
		tblProfile.getItems().clear();
		String FieldDelimiter = ",";

	//			getClass().getClassLoader().getResourceAsStream("profile.csv");
		 String input = System.getProperty("user.home") + "/Desktop/profile.csv";
		 
		BufferedReader reader = new BufferedReader(new FileReader(input));
		boolean firstLine = true;
		
		String line;
		while ((line = reader.readLine()) != null) {
			if (firstLine) {
				firstLine = false;
				continue;
			} 
			String[] fields = line.split(FieldDelimiter, -1);

			Profile profile = new Profile();
			profile.setFirstName(fields[0]);
			profile.setLastName(fields[1]);
			profile.setLine1(fields[2]);
			profile.setLine2(fields[3]);
			profile.setTown(fields[4]);
			profile.setRegionName(fields[5]);
			profile.setPostalCode(fields[6]); 
			profile.setCountryCode(fields[7]);
			profile.setCcNumber(fields[8]);
			profile.setCcExpMonth(fields[9]);
			profile.setCcExpYear(fields[10]);
			profile.setCcSecurityCode(fields[11]);
			profile.setPhone(fields[12]);
			profile.setEmailAddress(fields[13]);

			profileData.add(profile);

		}

		reader.close();
		tblProfile.setEditable(true);
		Callback<TableColumn<Profile, String>, TableCell<Profile, String>> cellFactory
		= (TableColumn<Profile, String> param) -> new EditingCell();

		tblcolProfileNumber.setCellValueFactory(new PropertyValueFactory<>("profileNumber"));
		tblcolFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		tblcolFirstName.setCellFactory(cellFactory);
		tblcolFirstName.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setFirstName(t.getNewValue());

				});
		tblcolLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		tblcolLastName.setCellFactory(cellFactory);
		tblcolLastName.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setLastName(t.getNewValue());

				});
		tblcolLine1.setCellValueFactory(new PropertyValueFactory<>("line1"));
		tblcolLine1.setCellFactory(cellFactory);
		tblcolLine1.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setLine1(t.getNewValue());

				});
		tblcolLine2.setCellValueFactory(new PropertyValueFactory<>("line2"));
		tblcolLine2.setCellFactory(cellFactory);
		tblcolLine2.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setLine2(t.getNewValue());

				});
		tblcolTown.setCellValueFactory(new PropertyValueFactory<>("town"));
		tblcolTown.setCellFactory(cellFactory);
		tblcolTown.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setTown(t.getNewValue());

				});
	//	tblcolCountryCode.setCellFactory(ComboBoxTableCell.forTableColumn(lista_e_shteteve));
		tblcolRegionName.setCellValueFactory(new PropertyValueFactory<>("regionName"));
		tblcolRegionName.setCellFactory(ComboBoxTableCell.forTableColumn(new ProfileControllerAdd().regionNameList));
	//	tblcolRegionName.setCellFactory(cellFactory);
	//	tblcolRegionName.setOnEditCommit(
	//			(TableColumn.CellEditEvent<Profile, String> t) -> {
	//				((Profile) t.getTableView().getItems()
	//						.get(t.getTablePosition().getRow()))
	//				.setRegionName(t.getNewValue());

	//			});
		tblcolPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
		tblcolPostalCode.setCellFactory(cellFactory);
		tblcolPostalCode.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setPostalCode(t.getNewValue());

				});
		tblcolCountryName.setCellValueFactory(new PropertyValueFactory<>("countryCode"));
		tblcolCountryName.setCellFactory(cellFactory);
		tblcolCountryName.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setCountryCode(t.getNewValue());

				});
		tblcolCCSecurityCode.setCellValueFactory(new PropertyValueFactory<>("ccSecurityCode"));
		tblcolCCSecurityCode.setCellFactory(cellFactory);
		tblcolCCSecurityCode.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setCcSecurityCode(t.getNewValue());

				});
		tblcolCcNumber.setCellValueFactory(new PropertyValueFactory<>("ccNumber"));
		tblcolCcNumber.setCellFactory(cellFactory);
		tblcolCcNumber.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setCcNumber(t.getNewValue());

				});
		tblcolCcExpMonth.setCellValueFactory(new PropertyValueFactory<>("ccExpMonth"));
		tblcolCcExpMonth.setCellFactory(cellFactory);
		tblcolCcExpMonth.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setCcExpMonth(t.getNewValue());

				});
		tblcolCcExpYear.setCellValueFactory(new PropertyValueFactory<>("ccExpYear"));
		tblcolCcExpYear.setCellFactory(cellFactory);
		tblcolCcExpYear.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setCcExpYear(t.getNewValue());

				});
		tblcolPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		tblcolPhone.setCellFactory(cellFactory);
		tblcolPhone.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setPhone(t.getNewValue());

				});
		tblcolEmail.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
		tblcolEmail.setCellFactory(cellFactory);
		tblcolEmail.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setEmailAddress(t.getNewValue());
					
				});
		tblcolDelete.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Profile, Boolean>, 
				ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Profile, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});

		//Adding the Button to the cell
		tblcolDelete.setCellFactory(
				new Callback<TableColumn<Profile, Boolean>, TableCell<Profile, Boolean>>() {

					@Override
					public TableCell<Profile, Boolean> call(TableColumn<Profile, Boolean> p) {
						return new ButtonCell();
					}

				});

		tblProfile.setItems(profileData);
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
					for(int j=1; j<cols.size() - 1; j++) {
						TableColumn<Profile, ?> col = cols.get(j);
						String cellValue = col.getCellData(profileData.get(i)).toString();
						cellValue = cellValue.toLowerCase();
						if(cellValue.contains(txtSearch.textProperty().get().toLowerCase())) {
							tableItems.add(profileData.get(i));
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
		if(!ProfileControllerAdd.isCancel) 
			fillData();
	}

	@FXML
	private void updateCsv() throws IOException, SQLException {
		deleteCSV();
		createCSV();
		fillData();
	}
	
	private void deleteCSV() {
		File f = new File(System.getProperty("user.home") + "/Desktop/profile.csv");
		if(f.delete()) {
			System.out.println("Success!");
		}
		else 
			System.out.println("Failed!");
	}
	
	private void createCSV() {
		try {
		File f = new File(System.getProperty("user.home") + "/Desktop/profile.csv");
		FileWriter fileWriter = new FileWriter(f);

		String text = "";
		String header = "FirstName" + "," + "LastName" + "," + "Line1" + "," + "Line2"  + "," + "Town" + "," + "RegionName" + 
					"," +	"PostalCode" + "," + "CountryName" + "," + "CCNumber" + "," + "CCExpiryMonth"  + "," + "CCExpiryYear" + "," + "CCSecurityCode" +
					"," + "PhoneNumber" + "," + "EmailAddress" + "," + "\n" ;


		fileWriter.write(header);
		for(int i=0; i<profileData.size(); i++){

			text =  profileData.get(i).getFirstName()+ "," + profileData.get(i).getLastName() + "," + profileData.get(i).getLine1()+ "," 
					+ profileData.get(i).getLine2() + "," + profileData.get(i).getTown() + "," + profileData.get(i).getRegionName()+ "," 
					+ profileData.get(i).getPostalCode() + "," + profileData.get(i).getCountryCode()+ "," 
							+ profileData.get(i).getCcNumber() + "," + profileData.get(i).getCcExpMonth() + "," + profileData.get(i).getCcExpYear() + "," 
					+ profileData.get(i).getCcSecurityCode() + "," + profileData.get(i).getPhone() + "," + profileData.get(i).getEmailAddress() + "," + "\n";
			fileWriter.write(text);
		}

		fileWriter.close();

	} catch (Exception ex) {
		System.out.println(ex.getMessage());
	}
	}

	class EditingCell extends TableCell<Profile, String> {

		private TextField textField;

		private EditingCell() {
		}

		@Override
		public void startEdit() {
			if (!isEmpty()) {
				super.startEdit();
				createTextField();
				setText(null);
				setGraphic(textField);
				textField.requestFocus();
				textField.selectAll();
			}
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();

			setText((String) getItem());
			setGraphic(null);
		}

		@Override
		public void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);

			if (empty) {
				setText(item);
				setGraphic(null);
			} else {
				if (isEditing()) {
					if (textField != null) {
						textField.setText(getString());
						//	                        setGraphic(null);
					}
					setText(null);
					setGraphic(textField);
				} else {
					setText(getString());
					setGraphic(null);
				}
			}
		}

		private void createTextField() {
			textField = new TextField(getString());
			textField.setStyle("-fx-text-fill : black;");
			textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
			textField.setOnAction((e) -> commitEdit(textField.getText()));
			textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
				if (!newValue) {
					commitEdit(textField.getText());
				}
			});
		}

		private String getString() {
			return getItem() == null ? "" : getItem();
		}
	}

	private class ButtonCell extends TableCell<Profile, Boolean> {
		final Button cellButton = new Button("");
		
		ButtonCell(){
			cellButton.setMaxWidth(30);
			cellButton.setCursor(Cursor.HAND);
			cellButton.getStyleClass().add("delete");
			//Action when the button is pressed
			cellButton.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent t) {
					// get Selected Item
					Profile currentProfile = (Profile) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
					//remove selected item from the table list
					profileData.remove(currentProfile);
				}
			});
		}

		//Display button if the row is not empty
		@Override
		protected void updateItem(Boolean t, boolean empty) {
			super.updateItem(t, empty);
			if(!empty){
				setGraphic(cellButton);
			}
		}
	}
}

