package hr.fer.opp.projekt.client.main;

import hr.fer.opp.projekt.client.Controller;
import hr.fer.opp.projekt.client.communication.OcsfEventChannel;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.lloseng.ocsf.client.ObservableClient;

public class ConnectController implements Controller {

	private MainApp mainApp;
	@FXML
	private Button spremi;
	@FXML
	private Button odustani;
	@FXML
	private TextField ip;
	@FXML
	private TextField port;
	@FXML
	private Label greska;

	public ConnectController() {
	}

	@FXML
	private void initialize() {
		greska.setText("");
		ip.setText("0.0.0.0");
		port.setText("5000");
	}

    public void setMainApp(Application mainApp) {
        this.mainApp = (MainApp) mainApp;
    }

	@FXML
	private void handleOdustani() {
		Stage stage = (Stage) odustani.getScene().getWindow();
		stage.close();
		// ubiti proces!
	}

	@FXML
	private void handleSpremi() {
		if (ip.getText().equals("") || port.getText().equals("")) {
			greska.setText("Sva polja moraju biti popunjena!");
			return;
		}
		if(!checkIp()) {
			greska.setText("Nevaljan format IP adrese!");
			return;
		}
		try {
			Integer.parseInt(port.getText());
		} catch (Exception e) {
			greska.setText("Port mora biti broj!");
			return;
		}
		
		String ipString = ip.getText();
		int portInt = Integer.parseInt(port.getText());
		
		connect(ipString, portInt);
	}
	
	private void connect(String ip, int port) {
		final ObservableClient client = new ObservableClient(ip, port);
		try {
			client.openConnection();
		} catch (IOException e) {
			greska.setText("Ne mogu se spojiti.");
			return;
		}
		this.mainApp.setChannel(new OcsfEventChannel(client));
		this.mainApp.showMain();
		handleOdustani();
//		this.channel = new OcsfEventChannel(client);
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

}
