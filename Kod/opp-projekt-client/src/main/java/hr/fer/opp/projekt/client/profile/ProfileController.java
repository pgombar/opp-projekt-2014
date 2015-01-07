package hr.fer.opp.projekt.client.profile;

import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Umjetnina;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ProfileController {

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
	private GridPane grid1;
	@FXML
	private ImageView online;
	@FXML
	private ImageView blocked;
	@FXML
	private ImageView favorited;
	@FXML
	private Button block;
	@FXML
	private Button favorite;
	@FXML
	private ListView<Umjetnina> listView;
	private ObservableList<Umjetnina> data = FXCollections.observableArrayList();

	private Korisnik korisnik;

	@FXML
	private void initialize() {
		Image img = new Image(this.getClass().getClassLoader().getResource("doge.jpg").toExternalForm());
		slika.setImage(img);

		mail.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("mail.png").toExternalForm()));
		chat.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("chat.png").toExternalForm()));

		List<Umjetnina> umjetnine = new ArrayList<Umjetnina>();
		umjetnine.add(new Umjetnina("Najbolja", "Tehnika", new Date(2014, 12, 25, 12, 24), null, null));
		umjetnine.add(new Umjetnina("Jos bolja", "Tehnika", new Date(2014, 12, 25, 12, 24), null, null));
		umjetnine.add(new Umjetnina("Super", "Tehnika", new Date(2014, 12, 25, 12, 24), null, null));

		this.setList(umjetnine);

		listView.setItems(data);
		listView.setCellFactory(new Callback<ListView<Umjetnina>, ListCell<Umjetnina>>() {

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
				// cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
				//
				// @Override
				// public void handle(MouseEvent event) {
				//
				// Umjetnina umjetnina =
				// listView.getSelectionModel().getSelectedItem();
				// Stage stage = new Stage();
				// stage.setTitle(umjetnina.getIme());
				// Scene scene = new Scene(new AnchorPane());
				// stage.setScene(scene);
				// stage.show();
				// }
				// });
				return cell;
			}
		});

		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Umjetnina>() {

			@Override
			public void changed(ObservableValue<? extends Umjetnina> observable, Umjetnina oldValue, Umjetnina newValue) {
				if (listView.getSelectionModel().getSelectedItem() == null)
					return;
				Umjetnina umjetnina = listView.getSelectionModel().getSelectedItem();
				Stage stage = new Stage();
				stage.setTitle(umjetnina.getIme());
				Scene scene = new Scene(new AnchorPane());
				stage.setScene(scene);
				stage.show();

				listView.getSelectionModel().clearSelection();
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
		if (korisnik.getSlika() != null)
			slika.setImage(SwingFXUtils.toFXImage(korisnik.getSlika(), null));
		setList(korisnik.getUmjetnine());

		refreshIcons();
	}

	private void refreshIcons() {
		if (korisnik.isOnline()) {
			online.setImage(new Image(this.getClass().getClassLoader().getResource("online.png").toExternalForm()));
		} else {
			online.setImage(new Image(this.getClass().getClassLoader().getResource("offline.png").toExternalForm()));
		}
		if (mainApp.isBlokiran(korisnik)) {
			blocked.setImage(new Image(this.getClass().getClassLoader().getResource("block-mini.png").toExternalForm()));
			block.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("block-mini.png")
					.toExternalForm()));
		} else {
			blocked.setImage(new Image(this.getClass().getClassLoader().getResource("not-block-mini.png")
					.toExternalForm()));
			block.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("not-block-mini.png")
					.toExternalForm()));
		}
		if (mainApp.isOmiljen(korisnik)) {
			favorited
					.setImage(new Image(this.getClass().getClassLoader().getResource("fav-mini.png").toExternalForm()));
			favorite.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("fav-mini.png")
					.toExternalForm()));
		} else {
			favorited.setImage(new Image(this.getClass().getClassLoader().getResource("not-fav-mini.png")
					.toExternalForm()));
			favorite.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("not-fav-mini.png")
					.toExternalForm()));
		}
	}

	public void setSlika(ImageView slika) {
		this.slika = slika;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	public void handleBlock() {
		mainApp.toggleBlock(korisnik);
		refreshIcons();
	}

	@FXML
	public void handleFavorite() {
		mainApp.toggleFavorite(korisnik);
		refreshIcons();
	}

	@FXML
	public void handleMail() {
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
	public void handleChat() {

	}

}
