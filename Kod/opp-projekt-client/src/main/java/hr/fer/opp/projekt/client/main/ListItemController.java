package hr.fer.opp.projekt.client.main;

import hr.fer.opp.projekt.client.Controller;
import hr.fer.opp.projekt.common.model.Korisnik;

import java.util.List;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ListItemController implements Controller {

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
	private Korisnik korisnik;

	public ListItemController() {
	}
	
	@FXML
	private void initialize() {
	}

    public void setMainApp(Application mainApp) {
        this.mainApp = (MainApp) mainApp;
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
	
	private boolean isBlokiranOd() {
		List<Korisnik> blokirani = korisnik.getBlokiraniUmjetnici();
		for(Korisnik k : blokirani)
			if(k.getId() == mainApp.getKorisnik().getId()) return true;
		return false;
	}
	
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
		korisnickoIme.setText(korisnik.getKorisnickoIme());
		imePrezime.setText(korisnik.getIme() + " " + korisnik.getPrezime());
		status.setText(korisnik.getOsobniStatus());
		if (korisnik.isOnline() && !mainApp.isBlokiran(korisnik) && !isBlokiranOd()) {
			online.setImage(new Image(this.getClass().getClassLoader().getResource("online.png").toExternalForm()));
		} else {
			online.setImage(new Image(this.getClass().getClassLoader().getResource("offline.png").toExternalForm()));
		}
		if(mainApp.isBlokiran(korisnik)) {
			blocked.setImage(new Image(this.getClass().getClassLoader().getResource("block-mini.png").toExternalForm()));
		} else {
			blocked.setImage(new Image(this.getClass().getClassLoader().getResource("not-block-mini.png").toExternalForm()));
		}
		if(mainApp.isOmiljen(korisnik)) {
			favorited.setImage(new Image(this.getClass().getClassLoader().getResource("fav-mini.png").toExternalForm()));
		} else {
			favorited.setImage(new Image(this.getClass().getClassLoader().getResource("not-fav-mini.png").toExternalForm()));
		}
		if (korisnik.getSlika() != null)
			slika.setImage(SwingFXUtils.toFXImage(korisnik.getSlika(), null));
		else
			slika.setImage(new Image(this.getClass().getClassLoader().getResource("default.jpg").toExternalForm()));
	}
    
}
