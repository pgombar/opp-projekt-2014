package hr.fer.opp.projekt.client.profile;

import hr.fer.opp.projekt.client.Controller;
import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Umjetnina;
import hr.fer.opp.projekt.common.odgovor.DohvatiUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.DohvatiUmjetnikaZahtjev;
import hr.fer.opp.projekt.common.zahtjev.UkrcajFotografijuUmjetnineZahtjev;
import hr.fer.opp.projekt.common.zahtjev.UrediPodatkeZahtjev;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.imageio.ImageIO;

public class MyProfileController implements Controller {

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
	private Button obrisiSliku;
	@FXML
	private TextField imeUmjetnine;
	@FXML
	private TextField tehnika;
	@FXML
	private TextField datoteka;
	@FXML
	private TextField direktorij;
	@FXML
	private Button ukrcaj;
	@FXML
	private Label greska;
	@FXML
	private ListView<Umjetnina> umjetnine;
	private ObservableList<Umjetnina> data = FXCollections.observableArrayList();

	private Korisnik korisnik;
	private File file;

	@FXML
	private void initialize() {

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
								controller.setMainApp(MyProfileController.this.mainApp);
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

		datoteka.setEditable(false);
		direktorij.setEditable(false);
	}

	public void setList(List<Umjetnina> umjetnine) {
		data.clear();
		data.addAll(umjetnine);
	}

	public void add(Umjetnina umjetnina) {
		data.add(umjetnina);
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
		
		if(mainApp.getFolder() != null)
			try {
				direktorij.setText(mainApp.getFolder().getCanonicalPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void setSlika(ImageView slika) {
		this.slika = slika;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handlePromijeniPodatke() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("fxml/profile/EditProfileLayout.fxml"));
			Parent root = (Parent) loader.load();
			root.getStylesheets().add(this.getClass().getClassLoader().getResource(mainApp.getSkin()).toExternalForm());
			EditProfileController controller = loader.getController();
			controller.setMainApp(this.mainApp);
			controller.setKorisnik(korisnik);
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setTitle("Uređivanje profila");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DohvatiUmjetnikaOdgovor odgovor = mainApp.getChannel().sendAndWait(
				new DohvatiUmjetnikaZahtjev(korisnik.getId()));
		setKorisnik(odgovor.getUmjetnik());
	}
	
	private void promijeniSliku(BufferedImage image) {
		try {
			Korisnik novi = new Korisnik(korisnik);
			novi.setSlika(image);
			UrediPodatkeZahtjev zahtjev = new UrediPodatkeZahtjev(novi);
			mainApp.getChannel().sendAndWait(zahtjev);

			DohvatiUmjetnikaZahtjev zahtjev2 = new DohvatiUmjetnikaZahtjev(novi.getId());
			DohvatiUmjetnikaOdgovor odgovor2 = mainApp.getChannel().sendAndWait(zahtjev2);

			setKorisnik(odgovor2.getUmjetnik());
			mainApp.setKorisnik(odgovor2.getUmjetnik());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handlePromijeniSliku() {
		browseFile();
		try {
			if(file != null)
				promijeniSliku(ImageIO.read(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleObrisiSliku() {
		promijeniSliku(null);
	}

	@FXML
	private void handleUkrcaj() {
		greska.setText("");
		if (imeUmjetnine.getText().equals("") || tehnika.getText().equals("") || datoteka.getText().equals("")) {
			greska.setText("Sva polja moraju biti popunjena!");
		} else {
			try {
				FileInputStream fis = new FileInputStream(file);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];

				for (int readNum; (readNum = fis.read(buf)) != -1;) {
					bos.write(buf, 0, readNum);
				}
				bos.flush();
				byte[] umjetnina = bos.toByteArray();

				UkrcajFotografijuUmjetnineZahtjev zahtjev = new UkrcajFotografijuUmjetnineZahtjev(umjetnina,
						imeUmjetnine.getText(), tehnika.getText(), new Date());
				mainApp.getChannel().sendAndWait(zahtjev);

				DohvatiUmjetnikaZahtjev zahtjev2 = new DohvatiUmjetnikaZahtjev(korisnik.getId());
				DohvatiUmjetnikaOdgovor odgovor2 = mainApp.getChannel().sendAndWait(zahtjev2);

				setKorisnik(odgovor2.getUmjetnik());
				mainApp.setKorisnik(odgovor2.getUmjetnik());
				
				imeUmjetnine.setText("");
				tehnika.setText("");
				datoteka.setText("");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void browseFile() {
		Stage stage = (Stage) datoteka.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		if(mainApp.getFolder() != null) fileChooser.setInitialDirectory(mainApp.getFolder());
		fileChooser.setTitle("Ukrcavanje slike");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.png", "*.jpg"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));
		File file = fileChooser.showOpenDialog(stage);
		if(file != null) this.file = file;
	}

	@FXML
	private void handleBrowse() {
		browseFile();
		if (file != null) {
			try {
				datoteka.setText(file.getCanonicalPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	private void handleFolder() {
		Stage stage = (Stage) datoteka.getScene().getWindow();
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Odabir direktorija");
		File direktorij = chooser.showDialog(stage);

		if(direktorij != null) {
			try {
				this.direktorij.setText(direktorij.getCanonicalPath());
				mainApp.setFolder(direktorij);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
    public void setMainApp(Application mainApp) {
        this.mainApp = (MainApp) mainApp;
    }
}
