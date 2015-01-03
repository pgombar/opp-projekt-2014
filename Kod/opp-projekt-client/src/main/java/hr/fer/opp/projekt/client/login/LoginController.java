package hr.fer.opp.projekt.client.login;

import hr.fer.opp.projekt.common.odgovor.LoginOdgovor;
import hr.fer.opp.projekt.common.zahtjev.LoginZahtjev;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	private LoginApp loginApp;
	@FXML
	private Button loginButton;
	@FXML
	private TextField korisnickoImeText;
	@FXML
	private PasswordField zaporkaText;
	
	public LoginController() {
	}
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void handleLogin() {
		LoginZahtjev zahtjev = new LoginZahtjev(korisnickoImeText.getText(), zaporkaText.getText());
		LoginOdgovor odgovor = loginApp.getChannel().sendAndWait(zahtjev);
		//System.out.println(odgovor);
	}

    public void setMainApp(LoginApp loginApp) {
        this.loginApp = loginApp;
    }
}
