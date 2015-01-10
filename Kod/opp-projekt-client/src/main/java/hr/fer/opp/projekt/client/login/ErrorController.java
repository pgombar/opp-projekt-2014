package hr.fer.opp.projekt.client.login;

import hr.fer.opp.projekt.client.main.MainApp;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorController {

	private MainApp mainApp;
	@FXML
	private Button ok;
	@FXML
	private Label greska;
	
	public ErrorController() {
	}
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void handleOK() {
		Stage stage = (Stage) ok.getScene().getWindow();
		stage.close();
	}

	public void setText(String text) {
		greska.setAlignment(Pos.CENTER);
		greska.setWrapText(true);
		greska.setText(text);
	}
	
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
