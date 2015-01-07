package hr.fer.opp.projekt.client.main;

import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Podgrana;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	private TreeView<Kategorija> kategorije;
	@FXML
	private Button trazi;
	@FXML
	private TextField pretraga;
	@FXML
	private Label imePrezime;
	@FXML
	private Button skin;
	
	public MainController() {
	}
	
	private static class Kategorija {
		public Grana grana;
		public Podgrana podgrana;
		public String string;
		
		public Kategorija(Grana grana) {
			this.grana = grana;
		}
		
		public Kategorija(String string) {
			this.string = string;
		}
		
		public Kategorija(Podgrana podgrana) {
			this.podgrana = podgrana;
		}
		
		@Override
		public String toString() {
			if(grana != null) return grana.toString();
			if(podgrana != null) return podgrana.toString();
			return string;
		}
	}
	
	@FXML
	private void initialize() {
		kategorije.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Kategorija>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Kategorija>> observable, 
            		TreeItem<Kategorija> old_val, TreeItem<Kategorija> new_val) {
            	if(new_val.getValue().grana != null) 
            		mainApp.searchGrana(new_val.getValue().grana);
            	else if(new_val.getValue().podgrana != null)
            		mainApp.searchPodgrana(new_val.getValue().podgrana);
            	else
            		mainApp.showAll();
            }

        });
	}

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void inicijaliziraj() {
		TreeItem<Kategorija> root = new TreeItem<>(new Kategorija("Pretraga po granama"));
		kategorije.setRoot(root);
		List<Grana> grane = mainApp.getGrane();
		for(int i = 0; i < grane.size(); ++i) {
			root.getChildren().add(new TreeItem<>(new Kategorija(grane.get(i))));
			for(int j = 0; j < grane.get(i).getPodgrane().size(); ++j)
				root.getChildren().get(i).getChildren().add(new TreeItem<>(new Kategorija(grane.get(i).getPodgrane().get(j))));
		}
		imePrezime.setText(mainApp.getKorisnik().getIme() + " " + mainApp.getKorisnik().getPrezime());
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
		mainApp.logout();
	}

	@FXML
	private void handleTrazi() {
		mainApp.search(pretraga.getText());
	}
	
	@FXML
	private void handleSkin() {
		mainApp.toggleSkin();
	}
}
