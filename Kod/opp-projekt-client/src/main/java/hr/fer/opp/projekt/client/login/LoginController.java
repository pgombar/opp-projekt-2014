package hr.fer.opp.projekt.client.login;

import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.common.odgovor.LoginOdgovor;
import hr.fer.opp.projekt.common.zahtjev.LoginZahtjev;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	private MainApp mainApp;
	@FXML
	private Button loginButton;
	@FXML
	private TextField korisnickoIme;
	@FXML
	private PasswordField zaporka;
	
	public LoginController() {
	}
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void handleLogin() {
		LoginZahtjev zahtjev = new LoginZahtjev(korisnickoIme.getText(), zaporka.getText());
		LoginOdgovor odgovor = mainApp.getChannel().sendAndWait(zahtjev);
		System.out.println(odgovor);
	}

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
