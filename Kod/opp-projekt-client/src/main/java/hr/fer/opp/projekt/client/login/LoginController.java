package hr.fer.opp.projekt.client.login;

import hr.fer.opp.projekt.client.Controller;
import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.common.odgovor.LoginOdgovor;
import hr.fer.opp.projekt.common.zahtjev.LoginZahtjev;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Controller {

	private MainApp mainApp;
	@FXML
	private Button loginButton;
	@FXML
	private TextField korisnickoIme;
	@FXML
	private PasswordField zaporka;
	@FXML
	private Label greska;
	
	public LoginController() {
	}
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void handleLogin() {
		LoginZahtjev zahtjev = new LoginZahtjev(korisnickoIme.getText(), zaporka.getText());
		LoginOdgovor odgovor = mainApp.getChannel().sendAndWait(zahtjev);
		if(odgovor.isUspjeh()) {
			System.out.println("Logiram korisnka " + odgovor.getKorisnik().getKorisnickoIme());
			mainApp.login(odgovor.getKorisnik());
		} else {
			greska.setText(odgovor.getPorukaGreske());
		}
	}
	
	@FXML
	private void handleRegister() {
		mainApp.showRegistration();
	}

    public void setMainApp(Application mainApp) {
        this.mainApp = (MainApp) mainApp;
    }
}
