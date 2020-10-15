package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Profile;
import utils.Helpers;


public class ProfileController implements Initializable {

	@FXML private TableView<Profile> tblProfile;
	@FXML private TableColumn<Profile, String> tblcolProfileNumber, tblcolFirstName, tblcolLastName,
	tblcolLine1, tblcolLine2, tblcolTown, tblcolRegionName, tblcolPostalCode, tblcolCC,
	tblcolCCSecurityCode, tblcolPhone, tblcolEmail;
	@FXML private TableColumn<Profile, Profile> tblcolDelete;
	@FXML private JFXButton btnAdd, btnEdit;
	@FXML private JFXTextField txtSearch;
	public static Profile profileDataHolder = new Profile();
	public static boolean edit = false;

	ObservableList<Profile> profileData = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			fillData();
			searchTableview();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void fillData() throws SQLException {
		tblProfile.getItems().clear();
		profileData.addAll(ControlDAO.getControlDao().getProfileDao().viewProfiles());

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
		tblcolRegionName.setCellValueFactory(new PropertyValueFactory<>("regionName"));
		tblcolRegionName.setCellFactory(cellFactory);
		tblcolRegionName.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setRegionName(t.getNewValue());

				});
		tblcolPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
		tblcolPostalCode.setCellFactory(cellFactory);
		tblcolPostalCode.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setPostalCode(t.getNewValue());

				});
		tblcolCC.setCellValueFactory(new PropertyValueFactory<>("cc"));
		tblcolCC.setCellFactory(cellFactory);
		tblcolCC.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setCc(t.getNewValue());

				});
		tblcolCCSecurityCode.setCellValueFactory(new PropertyValueFactory<>("ccSecurityCode"));
		tblcolCCSecurityCode.setCellFactory(cellFactory);
		tblcolCCSecurityCode.setOnEditCommit(
				(TableColumn.CellEditEvent<Profile, String> t) -> {
					((Profile) t.getTableView().getItems()
							.get(t.getTablePosition().getRow()))
					.setCcSecurityCode(t.getNewValue());

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
		tblcolDelete.setStyle("-fx-alignment:center;");
		tblcolDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tblcolDelete.setCellFactory(param -> new TableCell<Profile, Profile>() {

			Button delete = new Button("");
			protected void updateItem(Profile p, boolean empty) {
				if (p == null) {
					setGraphic(null);
					return;
				}

				setGraphic(delete);
				Helpers.styleDeleteButton(delete);
				delete.setOnMouseClicked(event -> {
					JFXAlert alert = new JFXAlert((Stage) tblProfile.getScene().getWindow());
					JFXButton cancel = new JFXButton("Cancel");
					cancel.setStyle("-fx-background-color: #E64A19; -fx-text-fill: white;-fx-cursor: hand;");
					JFXButton confirm = new JFXButton("Confirm");
					confirm.setStyle("-fx-background-color: #00BCD4; -fx-text-fill: white;-fx-cursor: hand;");
					Helpers.alertDelete(alert, confirm, cancel, p.getFirstName(), "");	
					confirm.setOnAction(e-> {
						//						int selectedIndex = tblProfile.getSelectionModel().getSelectedIndex();
						//						tblProfile.getItems().remove(selectedIndex);

						alert.close();
					}); 

					cancel.setOnAction( e1 -> {
						alert.close();
					});
					Helpers.refreshFocusTable(tblProfile);
				});
			}
		});

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
					for(int j=1; j<cols.size(); j++) {
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
		fillData();
	}

	@FXML
	private void edit() throws IOException, SQLException {
		edit = true;
		if(tblProfile.getSelectionModel().getSelectedItem() != null)  
			getData();
		else
			Helpers.alert("Warning!", "Pick a row from the table!", AlertType.WARNING);
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
			textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
			textField.setOnAction((e) -> commitEdit(textField.getText()));
			textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
				if (!newValue) {
					System.out.println("Commiting " + textField.getText());
					commitEdit(textField.getText());
				}
			});
		}

		private String getString() {
			return getItem() == null ? "" : getItem();
		}
	}

}

