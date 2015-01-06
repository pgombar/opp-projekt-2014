package hr.fer.opp.projekt.client.main;

import hr.fer.opp.projekt.common.model.Korisnik;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ListItemController {

	private MainApp mainApp;
	@FXML
	private Label korisnickoIme;
	@FXML
	private Label imePrezime;
	@FXML
	private Label status;
	@FXML
	private ImageView slika;
	@FXML
	private ImageView online;
	@FXML
	private ImageView blocked;
	@FXML
	private ImageView favorited;

	public ListItemController() {
	}
	
	@FXML
	private void initialize() {
        Image img = new Image(this.getClass().getClassLoader().getResource("doge.jpg").toExternalForm());
        slika.setImage(img);	
	}

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
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
		korisnickoIme.setText(korisnik.getKorisnickoIme());
		imePrezime.setText(korisnik.getIme() + " " + korisnik.getPrezime());
		status.setText(korisnik.getOsobniStatus());
		if(korisnik.isOnline()) {
			online.setImage(new Image(this.getClass().getClassLoader().getResource("online.png").toExternalForm()));
		} else {
			online.setImage(new Image(this.getClass().getClassLoader().getResource("offline.png").toExternalForm()));
		}
		if(mainApp.isBlokiran(korisnik)) {
			blocked.setImage(new Image(this.getClass().getClassLoader().getResource("block-mini.png").toExternalForm()));
		} else {
			blocked.setImage(new Image(this.getClass().getClassLoader().getResource("block-mini.png").toExternalForm()));
		}
		if(mainApp.isOmiljen(korisnik)) {
			favorited.setImage(new Image(this.getClass().getClassLoader().getResource("fav-mini.png").toExternalForm()));
		} else {
			favorited.setImage(new Image(this.getClass().getClassLoader().getResource("fav-mini.png").toExternalForm()));
		}
	}

	public void setSlika(ImageView slika) {
		this.slika = slika;
	}
    
}
