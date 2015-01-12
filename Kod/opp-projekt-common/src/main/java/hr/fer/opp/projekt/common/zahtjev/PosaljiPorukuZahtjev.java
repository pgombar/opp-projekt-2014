package hr.fer.opp.projekt.common.zahtjev;

import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.Serializable;

public class PosaljiPorukuZahtjev extends Zahtjev implements Serializable {
	private static final long serialVersionUID = 4631696044686932939L;

	private Korisnik korisnik;
	
	private String poruka;
	
	public PosaljiPorukuZahtjev(Korisnik korisnik, String poruka) {
		this.korisnik = korisnik;
		this.poruka = poruka;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public String getPoruka() {
		return poruka;
	}

	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}
}
