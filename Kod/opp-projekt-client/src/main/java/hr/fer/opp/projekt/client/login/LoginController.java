package hr.fer.opp.projekt.client.login;

import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.common.odgovor.LoginOdgovor;
import hr.fer.opp.projekt.common.zahtjev.LoginZahtjev;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
		if(odgovor.isUspjeh()) {
			System.out.println("Logiram korisnka " + odgovor.getKorisnik().getKorisnickoIme());
			mainApp.login(odgovor.getKorisnik());
		} else {
			// tu treba pravi error dialog xD koji ne radi jer verzija kurac palac
		    Stage stage = new Stage();
		    StackPane root = new StackPane();
		    root.getChildren().add(new Label(odgovor.getPorukaGreske()));
		    stage.setScene(new Scene(root));
		    stage.show();
		}
	}
	
	@FXML
	private void handleRegister() {
		mainApp.showRegistration();
	}

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
