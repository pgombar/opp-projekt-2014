package hr.fer.opp.projekt.client.main;

import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Podgrana;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
	@FXML
	private Button trazi;
	@FXML
	private TextField pretraga;
	
	public MainController() {
	}
	
	@FXML
	private void initialize() {
		kategorije.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observable, 
            		TreeItem<String> old_val, TreeItem<String> new_val) {
            		if(new_val.getParent() != null) mainApp.searchGrana(new_val.getValue());
            }

        });
	}

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void prikaziGrane() {
		TreeItem<String> root = new TreeItem<>("Grane umjetnosti");
		kategorije.setRoot(root);
		List<Grana> grane = mainApp.getGrane();
		List<List<Podgrana>> podgrane = mainApp.getPodgrane();
		for(int i = 0; i < grane.size(); ++i) {
			root.getChildren().add(new TreeItem<>(grane.get(i).getIme()));
			for(int j = 0; j < podgrane.get(i).size(); ++j)
				root.getChildren().get(i).getChildren().add(new TreeItem<>(podgrane.get(i).get(j).getIme()));
		}
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
	
	@FXML
	private void handleKategorija() {
		
	}
	
	@FXML
	private void handleTrazi() {
		mainApp.search(pretraga.getText());
	}
}
