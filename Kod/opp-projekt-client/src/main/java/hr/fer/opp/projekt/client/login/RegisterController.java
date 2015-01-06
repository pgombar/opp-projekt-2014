package hr.fer.opp.projekt.client.login;

import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Podgrana;
import hr.fer.opp.projekt.common.odgovor.RegistracijaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.RegistracijaZahtjev;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RegisterController {

	private MainApp mainApp;

	@FXML
	private TextField korisnickoIme;
	@FXML
	private TextField zaporka;
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
	private Button zavrsi;
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void handleZavrsi() {
		System.out.println(grana.getSelectionModel().getSelectedItem().getId());
		RegistracijaOdgovor odgovor = mainApp.getChannel().sendAndWait(new RegistracijaZahtjev(ime.getText(), prezime.getText(), korisnickoIme.getText(), zaporka.getText(),
				mail.getText(), telefon.getText(), adresa.getText(), zvanje.getText(), 
				grana.getSelectionModel().getSelectedItem().getId(), podgrana.getSelectionModel().getSelectedItem().getId()));
	
		if(odgovor.getKorisnik() == null) {
			// tu treba pravi error dialog xD koji ne radi jer verzija kurac palac
		    Stage stage = new Stage();
		    StackPane root = new StackPane();
		    root.getChildren().add(new Label(odgovor.getGreske().get(0)));
		    stage.setScene(new Scene(root));
		    stage.show();
		} else {
			System.out.println("Uspjesna registracija korisnika");
		    Stage stage = (Stage) zavrsi.getScene().getWindow();
		    stage.close();		
		}
	}
	
	@FXML
	private void handleOdabirGrane() {
		ObservableList<Podgrana> podgrane = FXCollections.observableArrayList();
		podgrane.addAll(mainApp.getPodgrane().get(grana.getSelectionModel().getSelectedIndex()));
		podgrana.setItems(podgrane);
		podgrana.getSelectionModel().select(0);
	}
    

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		ObservableList<Grana> grane = FXCollections.observableArrayList();
		System.out.println(mainApp);
		grane.addAll(mainApp.getGrane());
		grana.setItems(grane);
		grana.getSelectionModel().select(0);
	}

}