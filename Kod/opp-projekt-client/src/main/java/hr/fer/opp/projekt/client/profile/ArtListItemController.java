package hr.fer.opp.projekt.client.profile;

import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.common.model.Umjetnina;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ArtListItemController {

	private MainApp mainApp;
	@FXML
	private Label ime;
	@FXML
	private Label tehnika;
	@FXML
	private Label datumNastanka;
	@FXML
	private ImageView slika;
	
	public ArtListItemController() {
	}
	
	@FXML
	private void initialize() {
        Image img = new Image("https://yt3.ggpht.com/-7zFDHK5X45w/AAAAAAAAAAI/AAAAAAAAAAA/QJfHeLTEZwE/s100-c-k-no/photo.jpg");
        slika.setImage(img);	
	}
	
	public void setUmjetnina(Umjetnina umjetnina) {
		ime.setText(umjetnina.getIme());
		tehnika.setText(umjetnina.getTehnika());
		datumNastanka.setText(umjetnina.getDatumNastanka().toString());
	}

	public void setSlika(ImageView slika) {
		this.slika = slika;
	}

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
