package hr.fer.opp.projekt.client.admin;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
		
	public SettingsController() {
	}
	
	@FXML
	private void initialize() {
//		TreeItem<String> root = new TreeItem<String>("Kategorije");
//		kategorije.setRoot(root);
//		root.getChildren().add(new TreeItem<String>("slikarstvo"));
//		root.getChildren().add(new TreeItem<String>("kiparstvo"));
//		root.getChildren().add(new TreeItem<String>("sta jos postoji"));
//		root.getChildren().add(new TreeItem<String>("neznam"));
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
		
	}
}
