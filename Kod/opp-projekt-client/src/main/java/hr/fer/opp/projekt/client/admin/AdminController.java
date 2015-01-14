package hr.fer.opp.projekt.client.admin;

import hr.fer.opp.projekt.client.Controller;
import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Podgrana;
import hr.fer.opp.projekt.common.zahtjev.AdminUkloniKorisnikaZahtjev;

import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AdminController implements Controller {

	private AdminApp mainApp;
	private UserListController userListController;

	public void setUserListController(UserListController userListController) {
		this.userListController = userListController;
	}

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
	private Button settings;
	@FXML
	private Button dodajKorisnika;
	@FXML
	private Button obrisiKorisnika;
	@FXML
	private Button skin;

	public AdminController() {
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
			if (grana != null)
				return grana.toString();
			if (podgrana != null)
				return podgrana.toString();
			return string;
		}
	}

	@FXML
	private void initialize() {

		pretraga.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					handleTrazi();
				}
			}
		});

		kategorije.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Kategorija>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<Kategorija>> observable,
					TreeItem<Kategorija> old_val, TreeItem<Kategorija> new_val) {
				if (new_val.getValue().grana != null)
					mainApp.searchGrana(new_val.getValue().grana);
				else if (new_val.getValue().podgrana != null)
					mainApp.searchPodgrana(new_val.getValue().podgrana);
				else
					mainApp.showAll();
			}

		});
	}

	public void setMainApp(Application mainApp) {
		this.mainApp = (AdminApp) mainApp;
	}

	public void inicijaliziraj() {
		TreeItem<Kategorija> root = new TreeItem<>(new Kategorija("Pretraga po granama"));
		kategorije.setRoot(root);
		List<Grana> grane = mainApp.getGrane();
		for (int i = 0; i < grane.size(); ++i) {
			root.getChildren().add(new TreeItem<>(new Kategorija(grane.get(i))));
			for (int j = 0; j < grane.get(i).getPodgrane().size(); ++j)
				root.getChildren().get(i).getChildren()
						.add(new TreeItem<>(new Kategorija(grane.get(i).getPodgrane().get(j))));
		}
		imePrezime.setText("Administrator");
	}

	@FXML
	private void handleTrazi() {
		mainApp.search(pretraga.getText());
	}

	@FXML
	private void handleOdjava() {
		System.exit(0);
	}

	@FXML
	private void handleSettings() {
		this.mainApp.showSettings();
	}

	@FXML
	private void handleSkin() {
		mainApp.toggleSkin();
	}

	@FXML
	private void handleDodajKorisnika() {
		this.mainApp.dodajKorisnika();
	}

	@FXML
	private void handleObrisiKorisnika() {
		Korisnik korisnik = userListController.getKorisnici().getSelectionModel().getSelectedItem();
		if (korisnik == null)
			return;
		AdminUkloniKorisnikaZahtjev zahtjev = new AdminUkloniKorisnikaZahtjev(korisnik.getId());
		mainApp.getChannel().sendAndWait(zahtjev);
		
		this.mainApp.getSvi().remove(korisnik);
		this.mainApp.getUserListController().setList(this.mainApp.getSvi());
	}
}
