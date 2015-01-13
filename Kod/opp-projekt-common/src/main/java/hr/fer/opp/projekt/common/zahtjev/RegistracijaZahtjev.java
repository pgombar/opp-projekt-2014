package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;

public final class RegistracijaZahtjev extends Zahtjev implements Serializable {
    private static final long serialVersionUID = -5884335992897462654L;

    private final String ime;

    private final String prezime;

    private final String korisnickoIme;

    private final String zaporka;

    private final String email;

    private final String telefon;
    
    private String adresa;
 
    private String zvanje;
    
    private long grana;
    
    private long podgrana;


    public RegistracijaZahtjev(String ime, String prezime,
			String korisnickoIme, String zaporka, String email, String telefon,
			String adresa, String zvanje, long grana, long podgrana) {
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.zaporka = zaporka;
		this.email = email;
		this.telefon = telefon;
		this.adresa = adresa;
		this.zvanje = zvanje;
		this.grana = grana;
		this.podgrana = podgrana;
	}

	public String getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
    }

    public String getZaporka() {
        return zaporka;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getIme() {
        return ime;
    }

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public long getGrana() {
		return grana;
	}

	public void setGrana(long grana) {
		this.grana = grana;
	}

	public long getPodgrana() {
		return podgrana;
	}

	public void setPodgrana(long podgrana) {
		this.podgrana = podgrana;
	}
}
