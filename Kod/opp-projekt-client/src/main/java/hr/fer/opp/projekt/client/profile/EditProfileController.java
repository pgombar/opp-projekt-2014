package hr.fer.opp.projekt.client.profile;

import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Podgrana;
import hr.fer.opp.projekt.common.odgovor.UrediPodatkeOdgovor;
import hr.fer.opp.projekt.common.zahtjev.UrediPodatkeZahtjev;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class EditProfileController {

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
	private Button spremi;
	@FXML
	private Button odustani;
	private Korisnik korisnik;

	@FXML
	private void initialize() {
		korisnickoIme.setEditable(false);
	}

	@FXML
	private void handleSpremi() {
		korisnik.setIme(ime.getText());
		korisnik.setPrezime(prezime.getText());
		korisnik.setZvanje(zvanje.getText());
		korisnik.setEmail(mail.getText());
		korisnik.setAdresa(adresa.getText());
		korisnik.setTelefon(telefon.getText());
		korisnik.setGrana(grana.getSelectionModel().getSelectedItem());
		korisnik.setPodgrana(podgrana.getSelectionModel().getSelectedItem());
		UrediPodatkeOdgovor odgovor = mainApp.getChannel().sendAndWait(new UrediPodatkeZahtjev(korisnik));

		if (odgovor.getKorisnik() == null) {
			// tu treba pravi error dialog xD koji ne radi jer verzija kurac
			// palac
			Stage stage = new Stage();
			StackPane root = new StackPane();
			root.getChildren().add(new Label(odgovor.getGreske().get(0)));
			stage.setScene(new Scene(root));
			stage.show();
		} else {
			Stage stage = (Stage) spremi.getScene().getWindow();
			stage.close();
		}
	}

	@FXML
	private void handleOdustani() {
		Stage stage = (Stage) spremi.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void handleOdabirGrane() {
		ObservableList<Podgrana> podgrane = FXCollections.observableArrayList();
		podgrane.addAll(grana.getSelectionModel().getSelectedItem().getPodgrane());
		podgrana.setItems(podgrane);
		podgrana.getSelectionModel().select(0);
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
		korisnickoIme.setText(korisnik.getKorisnickoIme());
		ime.setText(korisnik.getIme());
		prezime.setText(korisnik.getPrezime());
		mail.setText(korisnik.getEmail());
		telefon.setText(korisnik.getTelefon());
		adresa.setText(korisnik.getAdresa());
		zvanje.setText(korisnik.getZvanje());
		grana.getSelectionModel().select(korisnik.getGrana());
		handleOdabirGrane();
		podgrana.getSelectionModel().select(korisnik.getPodgrana());
		zaporka.setText(korisnik.getZaporka());
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		ObservableList<Grana> grane = FXCollections.observableArrayList();
		grane.addAll(mainApp.getGrane());
		grana.setItems(grane);
		grana.getSelectionModel().select(0);
	}

}
