package hr.fer.opp.projekt.client.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsController {

	private AdminApp mainApp;
	@FXML
	private Button odustani;
	@FXML
	private Button spremi;
	@FXML
	private TextField ip;
	@FXML
	private TextField port;
	@FXML
	private TextField brojKorisnika;
	@FXML
	private Label greska;

	public SettingsController() {
	}

	@FXML
	private void initialize() {
		greska.setText("");
	}

	public void setMainApp(AdminApp adminApp) {
		this.mainApp = adminApp;
	}

	@FXML
	private void handleOdustani() {
		Stage stage = (Stage) odustani.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void handleSpremi() {
		if (ip.getText().equals("") || port.getText().equals("") || brojKorisnika.getText().equals("")) {
			greska.setText("Sva polja moraju biti popunjena!");
			return;
		}
		if (!checkIp()) {
			greska.setText("Nevaljana IP adresa.");
			return;
		}
		if (!checkPort()) {
			greska.setText("Nevaljan port.");
			return;
		}
		if (!checkbrojKorisnika()) {
			greska.setText("Nevaljan broj korisnika.");
			return;
		}

		try {
			String content = "application.host=" + ip.getText() + "\n" + "application.port=" + port.getText() + "\n"
					+ "application.maxUsers=" + brojKorisnika.getText();
			File config = new File("application.properties");

			// application.properties u opp-projekt-server resources oblika
			// application.host=localhost
			// application.port=4242

			FileWriter fw = new FileWriter(config);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage stage = (Stage) odustani.getScene().getWindow();
		stage.close();
	}

	private boolean checkIp() {
		String ipAddress = ip.getText();
		int count = 0;
		for (int i = 0; i < ipAddress.length(); i++) {
			if (ipAddress.charAt(i) == '.')
				++count;
		}
		if (count != 3) {
			return false;
		}

		String[] split = ipAddress.split("\\.");
		for (String s : split) {
			try {
				int n = Integer.parseInt(s);
				if (n < 0 || n > 255)
					return false;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;
	}

	private boolean checkbrojKorisnika() {
		try {
			int n = Integer.parseInt(brojKorisnika.getText());
			return (n > 0);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean checkPort() {
		try {
			int p = Integer.parseInt(port.getText());
			return (p >= 0 && p <= 65536);
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
