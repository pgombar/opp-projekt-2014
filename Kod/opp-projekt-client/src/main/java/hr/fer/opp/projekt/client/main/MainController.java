package hr.fer.opp.projekt.client.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class MainController {

	private MainApp mainApp;
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
	
	public MainController() {
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

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

	@FXML
	private void handleMojProfil() {
		mainApp.showProfile(0);
	}
	
	@FXML
	private void handlePrikaziOmiljene() {
		mainApp.showFavorite();
	}
	
	@FXML
	private void handlePrikaziBlokirane() {
		mainApp.showBlocked();
	}
	
	@FXML
	private void handlePrikaziSve() {
		mainApp.showAll();
	}
	
	@FXML
	private void handleOdjava() {
		
	}
}
