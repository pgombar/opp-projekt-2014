package hr.fer.opp.projekt.client.main;

import hr.fer.opp.projekt.client.communication.EventChannel;
import hr.fer.opp.projekt.client.communication.OcsfEventChannel;
import hr.fer.opp.projekt.client.login.LoginController;
import hr.fer.opp.projekt.client.login.RegisterController;
import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Podgrana;
import hr.fer.opp.projekt.common.model.Umjetnina;
import hr.fer.opp.projekt.common.odgovor.PopisUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.odgovor.PretragaUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.PopisUmjetnikaZahtjev;
import hr.fer.opp.projekt.common.zahtjev.PretragaUmjetnikaZahtjev;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
	
	private Korisnik korisnik;
	private List<Korisnik> svi;
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

		showLogin();
	}
	
	private void dohvatiPodatke() {
        PopisUmjetnikaOdgovor odgovor = channel.sendAndWait(PopisUmjetnikaZahtjev.INSTANCE);
        svi = new ArrayList<Korisnik>();
        List<Korisnik> korisnici = odgovor.getRezultati();
        for(Korisnik k : korisnici)
        	if(k.getId() != korisnik.getId())
        		svi.add(k);
   }
	
	private void initRootLayout() {
		dohvatiPodatke();
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
			mainController.inicijaliziraj();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void showLogin() {
	       try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(this.getClass().getClassLoader().getResource("fxml/welcome/WelcomeLayout.fxml"));
				Parent root = (Parent) loader.load();
				LoginController controller = loader.getController();
				controller.setMainApp(this);
				
				root.getStylesheets().add(this.getClass().getClassLoader().getResource("welcome.css").toExternalForm());

				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void showRegistration() {
	       try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(this.getClass().getClassLoader().getResource("fxml/register/RegisterLayout.fxml"));
				Parent root = (Parent) loader.load();
				RegisterController controller = loader.getController();
				controller.setMainApp(this);
				
				//root.getStylesheets().add(this.getClass().getClassLoader().getResource("menu.css").toExternalForm());

				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private void showUserList() {
	    try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(this.getClass().getClassLoader().getResource("fxml/main/UserListLayout.fxml"));
	        Parent userList = (Parent) loader.load();

	        root.setCenter(userList);

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
		dohvatiPodatke();
        userListController.setList(svi);
	}
	
	public void showBlocked() {
		userListController.setList(korisnik.getBlokiraniUmjetnici());
	}
	
	public void showFavorite() {
		userListController.setList(korisnik.getOmiljeniUmjetnici());
	}
	
	public void search(String search) {
		 PretragaUmjetnikaOdgovor odgovor = channel.sendAndWait(new PretragaUmjetnikaZahtjev(search, search, search));
		 userListController.setList(odgovor.getRezultati());
	}
	
	public void searchGrana(String grana) {
		List<Korisnik> search = new ArrayList<>();
		for(Korisnik k : svi) {
			if(k.getGrana().equals(grana) || k.getPodgrana().equals(grana))
				search.add(k);
		}
		userListController.setList(search);
	}
	
	public EventChannel getChannel() {
		return channel;
	}
	
	public List<Grana> getGrane() {
		List<Grana> grane = Arrays.asList(new Grana("slikarstvo"), new Grana("kiparstvo"), new Grana("grafika"));
		return grane;
	}
	
	public boolean isBlokiran(Korisnik korisnik) {
		return blokirani.contains(korisnik);
	}
	
	public boolean isOmiljen(Korisnik korisnik) {
		return omiljeni.contains(korisnik);
	}
	
	public List<List<Podgrana>> getPodgrane() {
		List<Podgrana> podgrane = Arrays.asList(new Podgrana("podgrana 1"), new Podgrana("podgrana 2"));
		List<List<Podgrana>> ret = Arrays.asList(podgrane, podgrane, podgrane);
		return ret;
	}
	
	public Korisnik getKorisnik() {
		return korisnik;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void login(Korisnik korisnik) {
		this.korisnik = korisnik;
		initRootLayout();
	}

	public void logout() {
		showLogin();
	}

}
