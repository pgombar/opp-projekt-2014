package hr.fer.opp.projekt.client.main;

import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.PosaljiPorukuOdgovor;
import hr.fer.opp.projekt.common.zahtjev.PosaljiPorukuZahtjev;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ChatController {

	private MainApp mainApp;
	
	@FXML
	private TextArea pristiglePoruke;
	
	@FXML
	private TextArea poruka;
	
	@FXML
	private Button posalji;
	
	private Korisnik korisnik;
	private Stage stage;
	
	public ChatController() {
	}
	
	@FXML
	private void initialize() {
	}

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private void handlePosalji() {
    	if(poruka.getText().equals("")) return;
    	PosaljiPorukuZahtjev zahtjev = new PosaljiPorukuZahtjev(korisnik, poruka.getText());
    	mainApp.getChannel().sendAndWait(zahtjev);
    	addMojaPoruka(poruka.getText());
    	poruka.setText("");
    }
    
    public void addMojaPoruka(String poruka) {
    	String text = pristiglePoruke.getText();
    	text = text + "ja: " + poruka + "\n";
    	pristiglePoruke.setText(text);
    }
    
    public void addPoruka(String poruka) {
    	String text = pristiglePoruke.getText();
    	text = text + korisnik.getKorisnickoIme() + ": " + poruka + "\n";
    	pristiglePoruke.setText(text);
    }

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	public void addOdjava() {
    	String text = pristiglePoruke.getText();
    	text = text + "---------- Korisnik " + korisnik.getKorisnickoIme() + " se odjavio. ----------\n";
    	pristiglePoruke.setText(text);
    	poruka.setText("Nije moguće poslati poruku.");
    	poruka.setEditable(false);
    	posalji.setDisable(true);
	}
	
	public void obustavaKomunikacije() {
		if(pristiglePoruke.getText().equals("")) return;
    	PosaljiPorukuZahtjev zahtjev = new PosaljiPorukuZahtjev(korisnik, "");
    	mainApp.getChannel().sendAndWait(zahtjev);
   	}

	public void komunikacijaObustavljena() {
    	String text = pristiglePoruke.getText();
    	text = text + "-------- Korisnik " + korisnik.getKorisnickoIme() + " je obustavio komunikaciju. --------\n";
    	pristiglePoruke.setText(text);
    	poruka.setText("Nije moguće poslati poruku.");
    	poruka.setEditable(false);
    	posalji.setDisable(true);
	}
}
