package hr.fer.opp.projekt.client.admin;

import hr.fer.opp.projekt.client.communication.EventChannel;
import hr.fer.opp.projekt.client.communication.OcsfEventChannel;
import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.client.profile.ProfileController;
import hr.fer.opp.projekt.common.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import hr.fer.opp.projekt.common.model.Umjetnina;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.lloseng.ocsf.client.ObservableClient;

public class AdminApp extends MainApp {

	private Stage stage;
	private BorderPane root;
	private EventChannel channel;

	private AdminController mainController;
	private UserListController userListController;

	private List<Korisnik> svi = new ArrayList<Korisnik>();
	private List<Korisnik> omiljeni = new ArrayList<Korisnik>();
	private List<Korisnik> blokirani = new ArrayList<Korisnik>();

	@Override
	public void start(Stage stage) throws Exception {
		final ObservableClient client = new ObservableClient("0.0.0.0", 5000);
		client.openConnection();

		this.channel = new OcsfEventChannel(client);

		this.stage = stage;

		Umjetnina umjetnina1 = new Umjetnina(1, "Najbolja", "Tehnika", new Date(2014, 12, 25, 12, 24));
		Umjetnina umjetnina2 = new Umjetnina(2, "Superiska", "Tehnikalija", new Date(2013, 12, 25, 12, 24));

		List<Umjetnina> umjetnine = Arrays.asList(umjetnina1, umjetnina2);

		this.stage.setTitle("Umjetnine");

		Grana slikarstvo = new Grana(1, "Slikarstvo");
		Podgrana slikarenje = new Podgrana(1, "Slikarenje");
	/*	svi.add(new Korisnik(1, "Pero", "Peric", "pperic", "pero", "pero@peric.com", "123-456-789", "Adresa 1",
				"Moj osobni status", "mehanicar", slikarstvo, slikarenje, umjetnine, null, true, false));

		svi.add(new Korisnik(1, "Perica", "Peric", "pperic", "pero", "pero@peric.com", "123-456-789", "Adresa 1",
				"Moj osobni status", "mehanicar", slikarstvo, slikarenje, umjetnine, null, true, false));

		blokirani.add(new Korisnik(1, "Bosko", "Peric", "pperic", "pero", "pero@peric.com", "123-456-789", "Adresa 1",
				"Moj osobni status", "mehanicar", slikarstvo, slikarenje, umjetnine, null, true, false));

		blokirani.add(new Korisnik(1, "Blokic", "Peric", "pperic", "pero", "pero@peric.com", "123-456-789", "Adresa 1",
				"Moj osobni status", "mehanicar", slikarstvo, slikarenje, umjetnine, null, true, false));

		omiljeni.add(new Korisnik(1, "Omiljko", "Peric", "pperic", "pero", "pero@peric.com", "123-456-789", "Adresa 1",
				"Moj osobni status", "mehanicar", slikarstvo, slikarenje, umjetnine, null, true, false));

		omiljeni.add(new Korisnik(1, "Omiljac", "Peric", "pperic", "pero", "pero@peric.com", "123-456-789", "Adresa 1",
				"Moj osobni status", "mehanicar", slikarstvo, slikarenje, umjetnine, null, true, false));*/

		initRootLayout();

	}

	private void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("fxml/admin/AdminLayout.fxml"));
			root = (BorderPane) loader.load();
			mainController = loader.getController();
			mainController.setMainApp(this);

			root.getStylesheets().add(this.getClass().getClassLoader().getResource("menu.css").toExternalForm());

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

			showUserList();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showUserList() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("fxml/admin/UserListLayout.fxml"));
			Parent userList = (Parent) loader.load();

			// Set person overview into the center of root layout.
			root.setCenter(userList);

			// Give the controller access to the main app.
			userListController = loader.getController();
			userListController.setMainApp(this);
			showAll();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showProfile(long id) {
		Stage stage = new Stage();
		StackPane root = new StackPane();
		root.getChildren().add(new Label("Odabrao si korisnika koji ima id " + id));
		stage.setScene(new Scene(root, 300, 250));
		stage.show();
	}

	public void showSettings() {
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("fxml/admin/SettingsLayout.fxml"));
			AnchorPane newRoot = (AnchorPane) loader.load();
//			mainController = loader.getController();
//			mainController.setMainApp(this);

//			newRoot.getStylesheets().add(this.getClass().getClassLoader().getResource("menu.css").toExternalForm());

			Scene scene = new Scene(newRoot);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void close() {
		stage.close();
	}

	public void showAll() {
		userListController.setList(svi);
	}

	public EventChannel getChannel() {
		return channel;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
