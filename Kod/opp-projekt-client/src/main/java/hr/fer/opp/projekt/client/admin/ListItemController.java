package hr.fer.opp.projekt.client.admin;

import hr.fer.opp.projekt.common.model.Korisnik;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ListItemController {

	private AdminApp mainApp;
	@FXML
	private Label korisnickoIme;
	@FXML
	private Label imePrezime;
	@FXML
	private Label status;
	@FXML
	private ImageView slika;
	
	public ListItemController() {
	}
	
	@FXML
	private void initialize() {
        Image img = new Image("https://yt3.ggpht.com/-7zFDHK5X45w/AAAAAAAAAAI/AAAAAAAAAAA/QJfHeLTEZwE/s100-c-k-no/photo.jpg");
        slika.setImage(img);	
	}

    public void setMainApp(AdminApp adminApp) {
        this.mainApp = adminApp;
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
	}

	public void setSlika(ImageView slika) {
		this.slika = slika;
	}
    
}
