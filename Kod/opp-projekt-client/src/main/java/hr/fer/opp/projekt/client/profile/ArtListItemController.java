package hr.fer.opp.projekt.client.profile;

import hr.fer.opp.projekt.client.Controller;
import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.common.model.Umjetnina;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ArtListItemController implements Controller {

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
	}

	public void setUmjetnina(Umjetnina umjetnina) {
		Image image = SwingFXUtils.toFXImage(umjetnina.getSlika(), null);
		slika.setImage(image);
		ime.setText(umjetnina.getIme());
		tehnika.setText(umjetnina.getTehnika());
		datumNastanka.setText(umjetnina.getDatumNastanka().toString());
	}

    public void setMainApp(Application mainApp) {
        this.mainApp = (MainApp) mainApp;
    }
}
