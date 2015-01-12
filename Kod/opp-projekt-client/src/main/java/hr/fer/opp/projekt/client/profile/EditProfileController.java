package hr.fer.opp.projekt.client.profile;

import hr.fer.opp.projekt.client.Controller;
import hr.fer.opp.projekt.client.login.ErrorController;
import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Podgrana;
import hr.fer.opp.projekt.common.odgovor.UrediPodatkeOdgovor;
import hr.fer.opp.projekt.common.zahtjev.UrediPodatkeZahtjev;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditProfileController implements Controller {

	private MainApp mainApp;

	@FXML
	private TextField korisnickoIme;
	@FXML
	private PasswordField zaporka;
	@FXML
	private TextField ime;
	@FXML
	private TextField prezime;
	@FXML
	private TextField zvanje;
	@FXML
	private TextField mail;
	@FXML
	private TextField adresa;
	@FXML
	private TextField telefon;
	@FXML
	private ComboBox<Grana> grana;
	@FXML
	private ComboBox<Podgrana> podgrana;
	@FXML
	private Button spremi;
	@FXML
	private Button odustani;
	private Korisnik korisnik;
	ObservableList<Grana> grane = FXCollections.observableArrayList();
	ObservableList<Podgrana> podgrane = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		korisnickoIme.setEditable(false);
	}

	@FXML
	private void handleSpremi() {
		Korisnik promjena = new Korisnik(korisnik);
		promjena.setIme(ime.getText());
		promjena.setPrezime(prezime.getText());
		promjena.setZvanje(zvanje.getText());
		promjena.setEmail(mail.getText());
		promjena.setAdresa(adresa.getText());
		promjena.setTelefon(telefon.getText());
		promjena.setZaporka(zaporka.getText());
		promjena.setGrana(grana.getSelectionModel().getSelectedItem());
		promjena.setPodgrana(podgrana.getSelectionModel().getSelectedItem());
		UrediPodatkeOdgovor odgovor = mainApp.getChannel().sendAndWait(new UrediPodatkeZahtjev(promjena));

		if(!odgovor.getGreske().isEmpty()) {
			String greske = "";
			for(String greska : odgovor.getGreske()) {
				greske = greske + greska + "\n";
			}
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(this.getClass().getClassLoader().getResource("fxml/register/ErrorLayout.fxml"));
				Parent root = (Parent) loader.load();

				ErrorController controller = loader.getController();
				controller.setMainApp(this.mainApp);
				controller.setText(greske);

				Stage stage = new Stage();
				stage.setResizable(false);
				stage.setTitle("Pogreška");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		} else {
			Stage stage = (Stage) spremi.getScene().getWindow();
			stage.close();
		}
	}

	@FXML
	private void handleOdustani() {
		Stage stage = (Stage) spremi.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void handleOdabirGrane() {
		podgrane.clear();
		podgrane.addAll(grana.getSelectionModel().getSelectedItem().getPodgrane());
		podgrana.setItems(podgrane);
		podgrana.getSelectionModel().clearSelection();
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
		korisnickoIme.setText(korisnik.getKorisnickoIme());
		ime.setText(korisnik.getIme());
		prezime.setText(korisnik.getPrezime());
		mail.setText(korisnik.getEmail());
		telefon.setText(korisnik.getTelefon());
		adresa.setText(korisnik.getAdresa());
		zvanje.setText(korisnik.getZvanje());
		zaporka.setText(korisnik.getZaporka());
		selectGrana();
	}
	
	private void selectGrana() {
		if(korisnik.getGrana() == null) return;
		for(Grana g : grane) {
			if(g.getId() == korisnik.getGrana().getId()) {
				grana.getSelectionModel().select(g);
				break;
			}
		}
		handleOdabirGrane();
		if(korisnik.getPodgrana() == null) return;
		for(Podgrana g : podgrane) {
			if(g.getId() == korisnik.getPodgrana().getId()) {
				podgrana.getSelectionModel().select(g);
				return;
			}
		}
	}

    public void setMainApp(Application mainApp) {
        this.mainApp = (MainApp) mainApp;
		grane.addAll(this.mainApp.getGrane());
		grana.setItems(grane);
	}

}
