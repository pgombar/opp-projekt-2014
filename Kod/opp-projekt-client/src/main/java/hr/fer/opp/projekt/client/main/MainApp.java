package hr.fer.opp.projekt.client.main;

import hr.fer.opp.projekt.client.communication.EventChannel;
import hr.fer.opp.projekt.client.communication.OcsfEventChannel;
import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hr.fer.opp.projekt.common.model.Umjetnina;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.lloseng.ocsf.client.ObservableClient;

public class MainApp extends Application {

	private Stage stage;
	private BorderPane root;
	private EventChannel channel;
	
	private MainController mainController;
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
		
		this.stage.setTitle("Umjetnine");
		svi.add(new Korisnik(2, "Ivo", "Ivic", "iivic", "ivo", "ivo@ivic.com",
	            "321-654-987", "Adresa 5", "Ja sam najbolji", "", null, null, new ArrayList<Umjetnina>(), null, false, false));

		svi.add(new Korisnik(2, "Ivica", "Ivic", "iivic", "ivo", "ivo@ivic.com",
	            "321-654-987", "Adresa 5", "Ja sam najbolji", "", null, null, new ArrayList<Umjetnina>(), null, false, false));
		
		blokirani.add(new Korisnik(2, "Blokic", "Ivic", "iivic", "ivo", "ivo@ivic.com",
	            "321-654-987", "Adresa 5", "Ja sam najbolji", "", null, null, new ArrayList<Umjetnina>(), null, false, false));

		blokirani.add(new Korisnik(2, "Blokanda", "Ivic", "iivic", "ivo", "ivo@ivic.com",
	            "321-654-987", "Adresa 5", "Ja sam najbolji", "", null, null, new ArrayList<Umjetnina>(), null, false, false));
		
		omiljeni.add(new Korisnik(2, "Omiljko", "Ivic", "iivic", "ivo", "ivo@ivic.com",
	            "321-654-987", "Adresa 5", "Ja sam najbolji", "", null, null, new ArrayList<Umjetnina>(), null, false, false));

		omiljeni.add(new Korisnik(2, "Omiljda", "Ivic", "iivic", "ivo", "ivo@ivic.com",
	            "321-654-987", "Adresa 5", "Ja sam najbolji", "", null, null, new ArrayList<Umjetnina>(), null, false, false));
		
		initRootLayout();
		
	}
	
	private void initRootLayout() {
        try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("fxml/main/MainLayout.fxml"));
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
	        loader.setLocation(this.getClass().getClassLoader().getResource("fxml/main/UserListLayout.fxml"));
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
	
	public void showAll() {
        userListController.setList(svi);
	}
	
	public void showBlocked() {
		userListController.setList(blokirani);
	}
	
	public void showFavorite() {
		userListController.setList(omiljeni);
	}
	
	public EventChannel getChannel() {
		return channel;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
