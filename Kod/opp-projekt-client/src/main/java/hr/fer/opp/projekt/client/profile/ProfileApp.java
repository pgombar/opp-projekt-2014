package hr.fer.opp.projekt.client.profile;

import hr.fer.opp.projekt.client.communication.EventChannel;
import hr.fer.opp.projekt.client.communication.OcsfEventChannel;
import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.IOException;

import com.lloseng.ocsf.client.ObservableClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProfileApp extends Application {

	private Stage stage;
	private AnchorPane root;
	private EventChannel channel;
	private ProfileController profileController;

	@Override
	public void start(Stage stage) throws Exception {
		final ObservableClient client = new ObservableClient("0.0.0.0", 5000);
		client.openConnection();

		this.channel = new OcsfEventChannel(client);

		this.stage = stage;

		this.stage.setTitle("Profile");
		initRootLayout();
	}

	private void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("fxml/profile/ProfileLayout.fxml"));
			root = (AnchorPane) loader.load();
			profileController = loader.getController();
			profileController.setProfileApp(this);
//			root.getStylesheets().add(MainApp.class.getResource("/menu.css").toExternalForm());
			root.getStylesheets().add(this.getClass().getClassLoader().getResource("menu.css").toExternalForm());

			Korisnik mirko = new Korisnik(4, "Mirko", "Mirkic", "kiki_bombon", "a", "mirko@mirkic.com", ";)", "Mirkonija", "Bok!!!",
					true, false);

			profileController.setKorisnik(mirko);

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public EventChannel getChannel() {
		return channel;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
