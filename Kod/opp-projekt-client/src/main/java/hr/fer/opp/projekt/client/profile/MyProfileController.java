package hr.fer.opp.projekt.client.profile;

import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Umjetnina;
import hr.fer.opp.projekt.common.odgovor.DohvatiUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.DohvatiUmjetnikaZahtjev;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MyProfileController {

	private MainApp mainApp;

	@FXML
	private Label korisnickoIme;
	@FXML
	private Label imePrezime;
	@FXML
	private Label status;
	@FXML
	private Label email;
	@FXML
	private Label telefon;
	@FXML
	private Label adresa;
	@FXML
	private Label zvanje;
	@FXML
	private Label grana;
	@FXML
	private Label podgrana;
	@FXML
	private ImageView slika;
	@FXML
	private Button promijeniPodatke;
	@FXML
	private Button promijeniSliku;
	@FXML
	private TextField imeUmjetnine;
	@FXML
	private TextField tehnika;
	@FXML
	private TextField datumNastanka;
	@FXML
	private TextField datoteka;
	@FXML
	private Button ukrcaj;
	@FXML
	private ListView<Umjetnina> listView;
	private ObservableList<Umjetnina> data = FXCollections.observableArrayList();
	
	private Korisnik korisnik;
	
	@FXML
	private void initialize() {
		Image img = new Image(this.getClass().getClassLoader().getResource("doge.jpg").toExternalForm());
		slika.setImage(img);
		
		List<Umjetnina> umjetnine = new ArrayList<Umjetnina>();
		umjetnine.add(new Umjetnina("Najbolja", "Tehnika", new Date(2014, 12, 25, 12, 24), null, null));
		umjetnine.add(new Umjetnina("Jos bolja", "Tehnika", new Date(2014, 12, 25, 12, 24), null, null));
		umjetnine.add(new Umjetnina("Super", "Tehnika", new Date(2014, 12, 25, 12, 24), null, null));

		this.setList(umjetnine);
		
		
		listView.setItems(data);
		listView.setCellFactory(new Callback<ListView<Umjetnina>, ListCell<Umjetnina>>(){
			 
            @Override
            public ListCell<Umjetnina> call(ListView<Umjetnina> p) {
            	ListCell<Umjetnina> cell = new ListCell<Umjetnina>() {
            		  @Override
                      protected void updateItem(Umjetnina t, boolean bln) {
                          super.updateItem(t, bln);
                          if(t != null) {
	           	   	        try {
	           		   	        FXMLLoader loader = new FXMLLoader();
	           		   	        loader.setLocation(this.getClass().getClassLoader().getResource("fxml/profile/ArtListItemLayout.fxml"));
	           					Parent userList = (Parent) loader.load();
	           			        ArtListItemController controller = loader.getController();
	           			        controller.setMainApp(MyProfileController.this.mainApp);
	           			        controller.setUmjetnina(t);
	           			        
	           			        setGraphic(userList);
	           				} catch (IOException e) {
	           					e.printStackTrace();
	           				}
	
	                      } 
            		  }
            	};
            	cell.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                    	
                    	Umjetnina umjetnina = listView.getSelectionModel().getSelectedItem();
                		Stage stage = new Stage();
                		stage.setTitle(umjetnina.getIme());
                		Scene scene = new Scene(new AnchorPane());
            			stage.setScene(scene);
            			stage.show();
                    }
                });
            	return cell;
            }
        });
		
		datoteka.setEditable(false);
	}
    
    public void setList(List<Umjetnina> umjetnine) {
    	data.clear();
    	data.addAll(umjetnine);
    }
    
    public void add(Umjetnina umjetnina) {
    	data.add(umjetnina);
    }

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme.setText(korisnickoIme);
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime.setText(imePrezime);
	}

	public void setStatus(String status) {
		this.status.setText(status);
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
		korisnickoIme.setText(korisnik.getKorisnickoIme());
		imePrezime.setText(korisnik.getIme() + " " + korisnik.getPrezime());
		status.setText(korisnik.getOsobniStatus());
		email.setText(korisnik.getEmail());
		telefon.setText(korisnik.getTelefon());
		adresa.setText(korisnik.getAdresa());
		zvanje.setText(korisnik.getZvanje());
		grana.setText(korisnik.getGrana().getIme());
		podgrana.setText(korisnik.getPodgrana().getIme());
        if(korisnik.getSlika() != null) slika.setImage(SwingFXUtils.toFXImage(korisnik.getSlika(), null));
		setList(korisnik.getUmjetnine());
	}

	public void setSlika(ImageView slika) {
		this.slika = slika;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	public void handlePromijeniPodatke() {
	       try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(this.getClass().getClassLoader().getResource("fxml/profile/EditProfileLayout.fxml"));
				Parent root = (Parent) loader.load();
				root.getStylesheets().add(this.getClass().getClassLoader().getResource(mainApp.getSkin()).toExternalForm());
				EditProfileController controller = loader.getController();
				controller.setMainApp(this.mainApp);
				controller.setKorisnik(korisnik);
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
	       DohvatiUmjetnikaOdgovor odgovor = mainApp.getChannel().sendAndWait(new DohvatiUmjetnikaZahtjev(korisnik.getId()));
	       setKorisnik(odgovor.getUmjetnik());
	}
	
	@FXML
	public void handlePromijeniSliku() {
	}
	
	@FXML
	public void handleUkrcaj() {
		
	}
	
	@FXML
	public void handleBrowse() {
		
	}
}