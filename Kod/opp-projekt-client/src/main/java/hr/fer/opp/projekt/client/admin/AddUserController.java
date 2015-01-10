package hr.fer.opp.projekt.client.admin;

import hr.fer.opp.projekt.common.odgovor.AdminDodajKorisnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.AdminDodajKorisnikaZahtjev;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AddUserController {

	private AdminApp mainApp;

	@FXML
	private TextField korisnickoIme;
	@FXML
	private PasswordField zaporka;
	@FXML
	private TextField mail;
	@FXML
	private Button spremi;
	@FXML
	private Button odustani;
	@FXML
	private Label greska;

	@FXML
	private void initialize() {
		greska.setText("");
		korisnickoIme.setText("");
		zaporka.setText("");
		mail.setText("");
	}

	@FXML
	private void handleSpremi() {
		AdminDodajKorisnikaZahtjev zahtjev = new AdminDodajKorisnikaZahtjev(korisnickoIme.getText(), zaporka.getText(),
				mail.getText());
		AdminDodajKorisnikaOdgovor odgovor = mainApp.getChannel().sendAndWait(zahtjev);
		
		if (!odgovor.getGreske().isEmpty()) {
			greska.setText(odgovor.getGreske().get(0));
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

	// public void setKorisnik(Korisnik korisnik) {
	// this.korisnik = korisnik;
	// korisnickoIme.setText(korisnik.getKorisnickoIme());
	// ime.setText(korisnik.getIme());
	// prezime.setText(korisnik.getPrezime());
	// mail.setText(korisnik.getEmail());
	// telefon.setText(korisnik.getTelefon());
	// adresa.setText(korisnik.getAdresa());
	// zvanje.setText(korisnik.getZvanje());
	// grana.getSelectionModel().select(korisnik.getGrana());
	// handleOdabirGrane();
	// podgrana.getSelectionModel().select(korisnik.getPodgrana());
	// zaporka.setText(korisnik.getZaporka());
	// }

	public void setMainApp(AdminApp mainApp) {
		this.mainApp = mainApp;
	}

}
