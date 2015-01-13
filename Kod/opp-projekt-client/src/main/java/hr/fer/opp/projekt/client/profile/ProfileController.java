package hr.fer.opp.projekt.client.profile;

import hr.fer.opp.projekt.client.Controller;
import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Umjetnina;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ProfileController implements Controller {

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
	private Button mail;
	@FXML
	private Button chat;
	@FXML
	private ImageView online;
	@FXML
	private ImageView blokiran;
	@FXML
	private ImageView omiljen;
	@FXML
	private Button dodajUBlokirane;
	@FXML
	private Button dodajUOmiljene;
	@FXML
	private ListView<Umjetnina> umjetnine;
	private ObservableList<Umjetnina> data = FXCollections.observableArrayList();

	private Korisnik korisnik;

	@FXML
	private void initialize() {
		Image img = new Image(this.getClass().getClassLoader().getResource("doge.jpg").toExternalForm());
		slika.setImage(img);

		mail.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("mail.png").toExternalForm()));
		chat.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("chat.png").toExternalForm()));

		umjetnine.setItems(data);
		umjetnine.setCellFactory(new Callback<ListView<Umjetnina>, ListCell<Umjetnina>>() {

			@Override
			public ListCell<Umjetnina> call(ListView<Umjetnina> p) {
				ListCell<Umjetnina> cell = new ListCell<Umjetnina>() {
					@Override
					protected void updateItem(Umjetnina t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							try {
								FXMLLoader loader = new FXMLLoader();
								loader.setLocation(this.getClass().getClassLoader()
										.getResource("fxml/profile/ArtListItemLayout.fxml"));
								Parent userList = (Parent) loader.load();
								ArtListItemController controller = loader.getController();
								controller.setMainApp(ProfileController.this.mainApp);
								controller.setUmjetnina(t);

								setGraphic(userList);
							} catch (IOException e) {
								e.printStackTrace();
							}

						}
					}
				};

				return cell;
			}
		});

		umjetnine.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Umjetnina>() {

			@Override
			public void changed(ObservableValue<? extends Umjetnina> observable, Umjetnina oldValue, Umjetnina newValue) {
	    		Umjetnina umjetnina = umjetnine.getSelectionModel().getSelectedItem();
    			if(umjetnina == null) return;

        		Stage stage = new Stage();
        		stage.setResizable(false);
        		stage.setTitle(umjetnina.getIme());
        		StackPane root = new StackPane();
        		root.getChildren().add(new ImageView(SwingFXUtils.toFXImage(umjetnina.getSlika(), null)));

    			stage.setScene(new Scene(root));
    			stage.show();
	    		umjetnine.getSelectionModel().clearSelection();
			}
		});
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

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
		korisnickoIme.setText(korisnik.getKorisnickoIme());
		imePrezime.setText(korisnik.getIme() + " " + korisnik.getPrezime());
		status.setText(korisnik.getOsobniStatus());
		email.setText(korisnik.getEmail());
		telefon.setText(korisnik.getTelefon());
		adresa.setText(korisnik.getAdresa());
		zvanje.setText(korisnik.getZvanje());
		if(korisnik.getGrana() != null) grana.setText(korisnik.getGrana().getIme());
		if(korisnik.getPodgrana() != null) podgrana.setText(korisnik.getPodgrana().getIme());
		if (korisnik.getSlika() != null)
			slika.setImage(SwingFXUtils.toFXImage(korisnik.getSlika(), null));
		else
			slika.setImage(new Image(this.getClass().getClassLoader().getResource("default.jpg").toExternalForm()));
		setList(korisnik.getUmjetnine());

		refreshIcons();
	}

	private boolean isBlokiranOd() {
		List<Korisnik> blokirani = korisnik.getBlokiraniUmjetnici();
		for(Korisnik k : blokirani)
			if(k.getId() == mainApp.getKorisnik().getId()) return true;
		return false;
	}
	
	private void refreshIcons() {
		if (korisnik.isOnline() && !mainApp.isBlokiran(korisnik) && !isBlokiranOd()) {
			online.setImage(new Image(this.getClass().getClassLoader().getResource("online.png").toExternalForm()));
		} else {
			online.setImage(new Image(this.getClass().getClassLoader().getResource("offline.png").toExternalForm()));
		}
		if (mainApp.isBlokiran(korisnik)) {
			blokiran.setImage(new Image(this.getClass().getClassLoader().getResource("block-mini.png").toExternalForm()));
			dodajUBlokirane.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("block-mini.png")
					.toExternalForm()));
		} else {
			blokiran.setImage(new Image(this.getClass().getClassLoader().getResource("not-block-mini.png")
					.toExternalForm()));
			dodajUBlokirane.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("not-block-mini.png")
					.toExternalForm()));
		}
		if (mainApp.isOmiljen(korisnik)) {
			omiljen
					.setImage(new Image(this.getClass().getClassLoader().getResource("fav-mini.png").toExternalForm()));
			dodajUOmiljene.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("fav-mini.png")
					.toExternalForm()));
		} else {
			omiljen.setImage(new Image(this.getClass().getClassLoader().getResource("not-fav-mini.png")
					.toExternalForm()));
			dodajUOmiljene.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("not-fav-mini.png")
					.toExternalForm()));
		}
	}

	public void setSlika(ImageView slika) {
		this.slika = slika;
	}

    public void setMainApp(Application mainApp) {
        this.mainApp = (MainApp) mainApp;
    }

	@FXML
	private void handleBlock() {
		mainApp.toggleBlock(korisnik);
		refreshIcons();
	}

	@FXML
	private void handleFavorite() {
		mainApp.toggleFavorite(korisnik);
		refreshIcons();
	}

	@FXML
	private void handleMail() {
		String recipient = korisnik.getEmail();
		String uriStr = String.format("mailto:%s", recipient);
		try {
			Desktop.getDesktop().browse(new URI(uriStr));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleChat() {
		if(korisnik.isOnline() && !mainApp.isBlokiran(korisnik) && !isBlokiranOd())
			mainApp.chat(korisnik, "");
		else
			handleMail();
	}

}
