package hr.fer.opp.projekt.client.main;

import hr.fer.opp.projekt.client.communication.EventChannel;
import hr.fer.opp.projekt.client.communication.OcsfEventChannel;
import hr.fer.opp.projekt.client.login.LoginController;
import hr.fer.opp.projekt.client.login.RegisterController;
import hr.fer.opp.projekt.client.profile.MyProfileController;
import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Podgrana;
import hr.fer.opp.projekt.common.model.Poruka;
import hr.fer.opp.projekt.common.odgovor.DodajBlokiranogUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.odgovor.DodajOmiljenogUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.odgovor.DohvatiPorukeOdgovor;
import hr.fer.opp.projekt.common.odgovor.DohvatiSifrarnikeOdgovor;
import hr.fer.opp.projekt.common.odgovor.ObrisiBlokiranogUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.odgovor.ObrisiOmiljenogUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.odgovor.PopisUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.odgovor.PretragaUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.odgovor.UrediPodatkeOdgovor;
import hr.fer.opp.projekt.common.zahtjev.DodajBlokiranogUmjetnikaZahtjev;
import hr.fer.opp.projekt.common.zahtjev.DodajOmiljenogUmjetnikaZahtjev;
import hr.fer.opp.projekt.common.zahtjev.DohvatiPorukeZahtjev;
import hr.fer.opp.projekt.common.zahtjev.DohvatiSifrarnikeZahtjev;
import hr.fer.opp.projekt.common.zahtjev.LogoutZahtjev;
import hr.fer.opp.projekt.common.zahtjev.ObrisiBlokiranogUmjetnikaZahtjev;
import hr.fer.opp.projekt.common.zahtjev.ObrisiOmiljenogUmjetnikaZahtjev;
import hr.fer.opp.projekt.common.zahtjev.PopisUmjetnikaZahtjev;
import hr.fer.opp.projekt.common.zahtjev.PretragaUmjetnikaZahtjev;
import hr.fer.opp.projekt.common.zahtjev.UrediPodatkeZahtjev;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import com.lloseng.ocsf.client.ObservableClient;

public class MainApp extends Application {

	private static final int REFRESH_RATE = 3000;
	private Stage stage;
	private BorderPane root;
	private EventChannel channel;
	
	private MainController mainController;
	private UserListController userListController;
	
	private Korisnik korisnik;
	private List<Korisnik> svi;
	private List<Korisnik> omiljeni = new ArrayList<Korisnik>();
	private List<Korisnik> blokirani = new ArrayList<Korisnik>();
	private List<Grana> grane;
	private long idZadnjePoruke = -1;
	private Map<Long, ChatController> otvorenChat = new TreeMap<>();
	
	private String skin = "menu1.css";

	@Override
	public void start(Stage stage) throws Exception {
        final ObservableClient client = new ObservableClient("0.0.0.0", 5000);
        client.openConnection();
        this.channel = new OcsfEventChannel(client);
        stage.setResizable(false);
        
		this.stage = stage;
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        System.exit(0);
		    }
		});
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
	
	public String getSkin() {
		return skin;
	}
	
	private void initRootLayout() {
		dohvatiPodatke();
        try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("fxml/main/MainLayout.fxml"));
			root = (BorderPane) loader.load();
			mainController = loader.getController();
			mainController.setMainApp(this);
			
			root.getStylesheets().add(this.getClass().getClassLoader().getResource(skin).toExternalForm());

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
			showUserList();
			mainController.inicijaliziraj();

			primajChatPoruke();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void primajChatPoruke() {
		Timer t = new Timer();

		t.scheduleAtFixedRate(
				new TimerTask() {
					public void run() {
						DohvatiPorukeZahtjev zahtjev = new DohvatiPorukeZahtjev(MainApp.this.korisnik, MainApp.this.idZadnjePoruke);
						DohvatiPorukeOdgovor odgovor = channel.sendAndWait(zahtjev);
						if(!odgovor.getPoruke().isEmpty()) {
							List<Poruka> poruke = odgovor.getPoruke();
							for(final Poruka p : poruke) {
					            Platform.runLater(new Runnable() {
					                public void run() {
					                	chat(p.getKorisnikOd(), p.getPoruka());
					                }
					            });
								if(p.getId() > idZadnjePoruke) idZadnjePoruke = p.getId();
							}
						}
					}
				}, 0, REFRESH_RATE);
	}
	
	public void chat(Korisnik korisnik, String poruka) {
		ChatController chatController = otvorenChat.get(korisnik.getId());
		if(chatController != null) {
			if(!poruka.equals("")) {
				if(!chatController.getStage().isShowing()) {
					chatController.getStage().show();
				}
				chatController.addPoruka(poruka);
			}		
		} else {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(this.getClass().getClassLoader().getResource("fxml/chat/ChatLayout.fxml"));
				Parent root = (Parent) loader.load();
				ChatController newChatController = loader.getController();
				newChatController.setMainApp(this);
				newChatController.setKorisnik(korisnik);
				
				otvorenChat.put(korisnik.getId(), newChatController);
				if(!poruka.equals("")) {
						newChatController.addPoruka(poruka);
				}

				Stage stage = new Stage();
				newChatController.setStage(stage);
				stage.setScene(new Scene(root));
				stage.setResizable(false);
				stage.setTitle("Chat s " + korisnik.getKorisnickoIme());
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
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

				Stage stage = new Stage();
		        stage.setResizable(false);
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
	
	public void showProfile() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("fxml/profile/MyProfileLayout.fxml"));
			Parent profile = (Parent) loader.load();

			MyProfileController controller = loader.getController();
			controller.setMainApp(this);
			controller.setKorisnik(korisnik);
			profile.getStylesheets().add(this.getClass().getClassLoader().getResource(skin).toExternalForm());

			Stage stage = new Stage();
	        stage.setResizable(false);
			stage.setTitle(korisnik.getIme() + " " + korisnik.getPrezime());
			Scene scene = new Scene(profile);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showAll() {
		dohvatiPodatke();
        userListController.setList(svi);
	}
	
	public void showBlocked() {
		userListController.setList(blokirani);
	}
	
	public void showFavorite() {
		userListController.setList(omiljeni);
	}
	
	public void search(String search) {
		 PretragaUmjetnikaOdgovor odgovor = channel.sendAndWait(new PretragaUmjetnikaZahtjev(search, search, search));
		 userListController.setList(odgovor.getRezultati());
	}
	
	public void searchGrana(Grana grana) {
		List<Korisnik> search = new ArrayList<>();
		for(Korisnik k : svi) {
			if(k.getGrana().getId() == grana.getId())
				search.add(k);
		}
		userListController.setList(search);
	}
	
	public void searchPodgrana(Podgrana podgrana) {
		List<Korisnik> search = new ArrayList<>();
		for(Korisnik k : svi) {
			if(k.getPodgrana().getId() == podgrana.getId())
				search.add(k);
		}
		userListController.setList(search);
	}
	
	public EventChannel getChannel() {
		return channel;
	}
	
	public List<Grana> getGrane() {
		if(grane == null) {
			DohvatiSifrarnikeOdgovor odgovor = channel.sendAndWait(DohvatiSifrarnikeZahtjev.INSTANCE);
			grane = odgovor.getGrane();
		}
		return grane;
	}
	
	public boolean isBlokiran(Korisnik korisnik) {
		for(Korisnik k : blokirani)
			if(k.getId() == korisnik.getId()) return true;
		return false;
	}
	
	public boolean isOmiljen(Korisnik korisnik) {
		for(Korisnik k : omiljeni)
			if(k.getId() == korisnik.getId()) return true;
		return false;	
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
        channel.sendAndWait(LogoutZahtjev.INSTANCE);

        korisnik = null;
    	omiljeni = new ArrayList<Korisnik>();
    	blokirani = new ArrayList<Korisnik>();
    	idZadnjePoruke = -1;
    	otvorenChat = new TreeMap<>();
    	skin = "menu1.css";

		showLogin();
	}

	public void toggleBlock(Korisnik korisnik) {
		if(isOmiljen(korisnik)) return;
		if(isBlokiran(korisnik)) { 
			ObrisiBlokiranogUmjetnikaZahtjev zahtjev = new ObrisiBlokiranogUmjetnikaZahtjev(this.korisnik.getId(), korisnik.getId());
			ObrisiBlokiranogUmjetnikaOdgovor odgovor = channel.sendAndWait(zahtjev);
			blokirani = odgovor.getBlokiraniUmjetnici();
		} else {
			DodajBlokiranogUmjetnikaZahtjev zahtjev = new DodajBlokiranogUmjetnikaZahtjev(this.korisnik.getId(), korisnik.getId());
			DodajBlokiranogUmjetnikaOdgovor odgovor = channel.sendAndWait(zahtjev);
			blokirani = odgovor.getBlokiraniUmjetnici();
		}
	}

	public void toggleFavorite(Korisnik korisnik) {
		if(isBlokiran(korisnik)) return;
		if(isOmiljen(korisnik)) { 
			ObrisiOmiljenogUmjetnikaZahtjev zahtjev = new ObrisiOmiljenogUmjetnikaZahtjev(this.korisnik.getId(), korisnik.getId());
			ObrisiOmiljenogUmjetnikaOdgovor odgovor = channel.sendAndWait(zahtjev);
			omiljeni = odgovor.getOmiljeniUmjetnici();
		} else {
			DodajOmiljenogUmjetnikaZahtjev zahtjev = new DodajOmiljenogUmjetnikaZahtjev(this.korisnik.getId(), korisnik.getId());
			DodajOmiljenogUmjetnikaOdgovor odgovor = channel.sendAndWait(zahtjev);
			omiljeni = odgovor.getOmiljeniUmjetnici();
		}
	}

	public void toggleSkin() {
		if(skin.equals("menu1.css")) skin = "menu2.css"; else skin = "menu1.css";
		root.getStylesheets().clear();
		root.getStylesheets().add(this.getClass().getClassLoader().getResource(skin).toExternalForm());
	}
	
	public void changeStatus(String osobniStatus) {
		this.korisnik.setOsobniStatus(osobniStatus);
		UrediPodatkeZahtjev zahtjev = new UrediPodatkeZahtjev(korisnik);
		UrediPodatkeOdgovor odgovor = channel.sendAndWait(zahtjev);
		this.korisnik = odgovor.getKorisnik();
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
}
