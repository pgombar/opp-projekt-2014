package hr.fer.opp.projekt.client.admin;

import hr.fer.opp.projekt.client.communication.EventChannel;
import hr.fer.opp.projekt.client.communication.OcsfEventChannel;
import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Podgrana;
import hr.fer.opp.projekt.common.odgovor.DohvatiSifrarnikeOdgovor;
import hr.fer.opp.projekt.common.odgovor.PopisUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.odgovor.PretragaUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.DohvatiSifrarnikeZahtjev;
import hr.fer.opp.projekt.common.zahtjev.PopisUmjetnikaZahtjev;
import hr.fer.opp.projekt.common.zahtjev.PretragaUmjetnikaZahtjev;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.lloseng.ocsf.client.ObservableClient;

public class AdminApp extends MainApp {

	private Stage stage;
	private BorderPane root;
	private EventChannel channel;

	private AdminController mainController;
	private UserListController userListController;

	public UserListController getUserListController() {
		return userListController;
	}

	private List<Korisnik> svi;
	private List<Korisnik> omiljeni = new ArrayList<Korisnik>();
	private List<Korisnik> blokirani = new ArrayList<Korisnik>();
	private List<Grana> grane;

	private String skin = "menu1.css";

	@Override
	public void start(Stage stage) throws Exception {
		final ObservableClient client = new ObservableClient("0.0.0.0", 5000);
		client.openConnection();
		this.channel = new OcsfEventChannel(client);

		this.stage = stage;
		this.stage.setTitle("Administratorska aplikacija");

		initRootLayout();
	}

	private void dohvatiPodatke() {
		PopisUmjetnikaOdgovor odgovor = channel.sendAndWait(PopisUmjetnikaZahtjev.INSTANCE);
		svi = new ArrayList<Korisnik>();
		List<Korisnik> korisnici = odgovor.getRezultati();
		for (Korisnik k : korisnici) {
			svi.add(k);
		}
	}

	public String getSkin() {
		return skin;
	}

	private void initRootLayout() {
		dohvatiPodatke();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("fxml/admin/AdminLayout.fxml"));
			root = (BorderPane) loader.load();
			mainController = loader.getController();
			mainController.setMainApp(this);

			root.getStylesheets().add(this.getClass().getClassLoader().getResource(skin).toExternalForm());

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

			showUserList();
			mainController.inicijaliziraj();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showUserList() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("fxml/admin/UserListLayout.fxml"));
			Parent userList = (Parent) loader.load();

			root.setCenter(userList);

			userListController = loader.getController();
			userListController.setMainApp(this);
			mainController.setUserListController(userListController);
			showAll();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAll() {
		dohvatiPodatke();
		userListController.setList(svi);
	}

	public void search(String search) {
		PretragaUmjetnikaOdgovor odgovor = channel.sendAndWait(new PretragaUmjetnikaZahtjev(search, search, search));
		userListController.setList(odgovor.getRezultati());
	}

	public void searchGrana(Grana grana) {
		List<Korisnik> search = new ArrayList<>();
		for (Korisnik k : svi) {
			if (k.getGrana().getId() == grana.getId())
				search.add(k);
		}
		userListController.setList(search);
	}

	public void searchPodgrana(Podgrana podgrana) {
		List<Korisnik> search = new ArrayList<>();
		for (Korisnik k : svi) {
			if (k.getPodgrana().getId() == podgrana.getId())
				search.add(k);
		}
		userListController.setList(search);
	}

	public EventChannel getChannel() {
		return channel;
	}

	public List<Grana> getGrane() {
		if (grane == null) {
			DohvatiSifrarnikeOdgovor odgovor = channel.sendAndWait(DohvatiSifrarnikeZahtjev.INSTANCE);
			grane = odgovor.getGrane();
		}
		return grane;
	}

	public boolean isBlokiran(Korisnik korisnik) {
		for (Korisnik k : blokirani)
			if (k.getId() == korisnik.getId())
				return true;
		return false;
	}

	public boolean isOmiljen(Korisnik korisnik) {
		for (Korisnik k : omiljeni)
			if (k.getId() == korisnik.getId())
				return true;
		return false;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void toggleBlock(Korisnik korisnik) {
		// if(isOmiljen(korisnik)) return;
		// if(isBlokiran(korisnik)) {
		// ObrisiBlokiranogUmjetnikaZahtjev zahtjev = new
		// ObrisiBlokiranogUmjetnikaZahtjev(this.korisnik.getId(),
		// korisnik.getId());
		// ObrisiBlokiranogUmjetnikaOdgovor odgovor =
		// channel.sendAndWait(zahtjev);
		// blokirani = odgovor.getBlokiraniUmjetnici();
		// } else {
		// DodajBlokiranogUmjetnikaZahtjev zahtjev = new
		// DodajBlokiranogUmjetnikaZahtjev(this.korisnik.getId(),
		// korisnik.getId());
		// DodajBlokiranogUmjetnikaOdgovor odgovor =
		// channel.sendAndWait(zahtjev);
		// blokirani = odgovor.getBlokiraniUmjetnici();
		// }
	}

	public void toggleFavorite(Korisnik korisnik) {
		// if(isBlokiran(korisnik)) return;
		// if(isOmiljen(korisnik)) {
		// ObrisiOmiljenogUmjetnikaZahtjev zahtjev = new
		// ObrisiOmiljenogUmjetnikaZahtjev(this.korisnik.getId(),
		// korisnik.getId());
		// ObrisiOmiljenogUmjetnikaOdgovor odgovor =
		// channel.sendAndWait(zahtjev);
		// omiljeni = odgovor.getOmiljeniUmjetnici();
		// } else {
		// DodajOmiljenogUmjetnikaZahtjev zahtjev = new
		// DodajOmiljenogUmjetnikaZahtjev(this.korisnik.getId(),
		// korisnik.getId());
		// DodajOmiljenogUmjetnikaOdgovor odgovor =
		// channel.sendAndWait(zahtjev);
		// omiljeni = odgovor.getOmiljeniUmjetnici();
		// }
	}

	public void toggleSkin() {
		if (skin.equals("menu1.css"))
			skin = "menu2.css";
		else
			skin = "menu1.css";
		root.getStylesheets().clear();
		root.getStylesheets().add(this.getClass().getClassLoader().getResource(skin).toExternalForm());
	}

	public void showSettings() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("fxml/admin/SettingsLayout.fxml"));
			Parent profile = (Parent) loader.load();

			SettingsController controller = loader.getController();
			controller.setMainApp(this);
			profile.getStylesheets().add(this.getClass().getClassLoader().getResource(skin).toExternalForm());

			Stage stage = new Stage();
			stage.setTitle("Postavke poslužitelja");
			Scene scene = new Scene(profile);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void dodajKorisnika() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("fxml/admin/AddUserLayout.fxml"));
			Parent profile = (Parent) loader.load();

			AddUserController controller = loader.getController();
			controller.setMainApp(this);
			profile.getStylesheets().add(this.getClass().getClassLoader().getResource(skin).toExternalForm());

			Stage stage = new Stage();
			stage.setTitle("Novi korisnik");
			Scene scene = new Scene(profile);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
