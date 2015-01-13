package hr.fer.opp.projekt.common.zahtjev;

import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.Serializable;

public class UkloniPorukeKorisnikaZahtjev extends Zahtjev implements Serializable {

	private static final long serialVersionUID = 2762187494602957134L;

	private Korisnik korisnik;
	
	public UkloniPorukeKorisnikaZahtjev(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
}
