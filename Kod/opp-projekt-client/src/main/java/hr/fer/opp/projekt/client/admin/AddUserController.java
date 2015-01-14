package hr.fer.opp.projekt.client.admin;

import hr.fer.opp.projekt.client.Controller;
import hr.fer.opp.projekt.common.odgovor.AdminDodajKorisnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.AdminDodajKorisnikaZahtjev;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddUserController implements Controller {

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
		this.mainApp.getSvi().add(odgovor.getKorisnik());
		this.mainApp.getUserListController().setList(this.mainApp.getSvi());
	}

	@FXML
	private void handleOdustani() {
		Stage stage = (Stage) spremi.getScene().getWindow();
		stage.close();
	}

	public void setMainApp(Application mainApp) {
		this.mainApp = (AdminApp) mainApp;
	}

}
