package hr.fer.opp.projekt.client.admin;

import hr.fer.opp.projekt.client.communication.EventChannel;
import hr.fer.opp.projekt.client.communication.OcsfEventChannel;
import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Podgrana;
import hr.fer.opp.projekt.common.odgovor.DohvatiSifrarnikeOdgovor;
import hr.fer.opp.projekt.common.odgovor.PopisUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.odgovor.PretragaUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.DohvatiSifrarnikeZahtjev;
import hr.fer.opp.projekt.common.zahtjev.PopisUmjetnikaZahtjev;
import hr.fer.opp.projekt.common.zahtjev.PretragaUmjetnikaZahtjev;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import com.lloseng.ocsf.client.ObservableClient;

public class AdminApp extends Application {

	private Stage stage;
	private BorderPane root;
	private EventChannel channel;

	private AdminController mainController;
	private UserListController userListController;

	public UserListController getUserListController() {
		return userListController;
	}

	private List<Korisnik> svi;
	private List<Grana> grane;

	private String skin = "menu1.css";

	@Override
	public void start(Stage stage) throws Exception {
		String defaultAppFile = this.getClass().getClassLoader().getResource("application.properties").getFile();
		String appFile = "./application.properties";

		String ip = "";
		int port = 0;

		if (!Files.exists(Paths.get(appFile))) {
			appFile = defaultAppFile;
		}

		try (FileReader reader = new FileReader(appFile)) {
			Properties properties = new Properties();
			properties.load(reader);
			ip = properties.getProperty("application.host");
			port = Integer.parseInt(properties.getProperty("application.port"));
		} catch (IOException e) {
		}

		System.out.println(ip + " " + port);

		final ObservableClient client = new ObservableClient(ip, port);
		client.openConnection();
		this.channel = new OcsfEventChannel(client);

		this.stage = stage;
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
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

	public static void main(String[] args) {
		launch(args);
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
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					System.exit(0);
				}
			});
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
	
	public void refresh() {
		dohvatiPodatke();
		userListController.setList(svi);
	}

}
