package hr.fer.opp.projekt.client.login;

import java.io.IOException;

import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.client.profile.MyProfileController;
import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Podgrana;
import hr.fer.opp.projekt.common.odgovor.RegistracijaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.RegistracijaZahtjev;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RegisterController {

	private MainApp mainApp;

	@FXML
	private TextField korisnickoIme;
	@FXML
	private PasswordField zaporka;
	@FXML
	private TextField ime;
	@FXML
	private TextField prezime;
	@FXML
	private TextField zvanje;
	@FXML
	private TextField mail;
	@FXML
	private TextField adresa;
	@FXML
	private TextField telefon;
	@FXML
	private ComboBox<Grana> grana;
	@FXML
	private ComboBox<Podgrana> podgrana;
	@FXML
	private Button zavrsi;
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void handleZavrsi() {
		RegistracijaOdgovor odgovor = mainApp.getChannel().sendAndWait(new RegistracijaZahtjev(ime.getText(), prezime.getText(), korisnickoIme.getText(), zaporka.getText(),
				mail.getText(), telefon.getText(), adresa.getText(), zvanje.getText(), 
				grana.getSelectionModel().getSelectedItem().getId(), podgrana.getSelectionModel().getSelectedItem().getId()));
	
		if(!odgovor.getGreske().isEmpty()) {
			String greske = "";
			for(String greska : odgovor.getGreske()) {
				greske = greske + greska + "\n";
			}
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(this.getClass().getClassLoader().getResource("fxml/register/ErrorLayout.fxml"));
				Parent root = (Parent) loader.load();

				ErrorController controller = loader.getController();
				controller.setMainApp(this.mainApp);
				controller.setText(greske);

				Stage stage = new Stage();
				stage.setResizable(false);
				stage.setTitle("Pogreška");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		} else {
			Stage stage = (Stage) zavrsi.getScene().getWindow();
			stage.close();		
		}
	}
	
	@FXML
	private void handleOdabirGrane() {
		ObservableList<Podgrana> podgrane = FXCollections.observableArrayList();
		podgrane.addAll(grana.getSelectionModel().getSelectedItem().getPodgrane());
		podgrana.setItems(podgrane);
		podgrana.getSelectionModel().select(0);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		ObservableList<Grana> grane = FXCollections.observableArrayList();
		grane.addAll(mainApp.getGrane());
		grana.setItems(grane);
		grana.getSelectionModel().select(0);
	}

}
