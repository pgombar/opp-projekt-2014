package hr.fer.opp.projekt.client.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

public class AdminController {

	private AdminApp mainApp;
	@FXML
	private Button mojProfil;
	@FXML
	private Button prikaziSve;
	@FXML
	private Button prikaziOmiljene;
	@FXML
	private Button prikaziBlokirane;
	@FXML
	private Button odjava;
	@FXML
	private TreeView<String> kategorije;
	
	public AdminController() {
	}
	
	@FXML
	private void initialize() {
		TreeItem<String> root = new TreeItem<String>("Kategorije");
		kategorije.setRoot(root);
		root.getChildren().add(new TreeItem<String>("slikarstvo"));
		root.getChildren().add(new TreeItem<String>("kiparstvo"));
		root.getChildren().add(new TreeItem<String>("sta jos postoji"));
		root.getChildren().add(new TreeItem<String>("neznam"));
	}

    public void setMainApp(AdminApp adminApp) {
        this.mainApp = adminApp;
    }

	@FXML
	private void handleOdjava() {
		Stage stage = (Stage) odjava.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void handlePostavke() {
		mainApp.showSettings();
	}
	
	@FXML
	private void handleObrisi() {
		
	}
	
	@FXML
	private void handleDodaj() {
		
	}
}
