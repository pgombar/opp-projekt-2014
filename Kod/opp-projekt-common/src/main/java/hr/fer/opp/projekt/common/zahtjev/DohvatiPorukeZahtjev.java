package hr.fer.opp.projekt.common.zahtjev;

import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.Serializable;

public class DohvatiPorukeZahtjev extends Zahtjev implements Serializable {
	private static final long serialVersionUID = -7283436211621135083L;

	private long idZadnjePoruke;
		
	private Korisnik korisnik;
	
	public DohvatiPorukeZahtjev(Korisnik korisnik, long idZadnjePoruke) {
		this.idZadnjePoruke = idZadnjePoruke;
		this.korisnik = korisnik;
	}

	public long getIdZadnjePoruke() {
		return idZadnjePoruke;
	}

	public void setId(long idZadnjePoruke) {
		this.idZadnjePoruke = idZadnjePoruke;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
}
