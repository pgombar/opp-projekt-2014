package hr.fer.opp.projekt.common.model;

import java.awt.Image;
import java.io.Serializable;
import java.util.List;

public final class Korisnik implements Serializable {

	private static final long serialVersionUID = -4322708051779590572L;

	private long id;

    private String ime;

    private String prezime;

    private String korisnickoIme;

    private String zaporka;

    private String email;

    private String telefon;

    private String adresa;

    private String osobniStatus;
 
    private String zvanje;
    
    private String grana;
    
    private String podgrana;
    
    private List<Umjetnina> umjetnine;
    
    private Image slika;

    private boolean online;

    private boolean admin;

    public Korisnik(long id, String ime, String prezime, String korisnickoIme,
			String zaporka, String email, String telefon, String adresa,
			String osobniStatus, String zvanje, String grana, String podgrana,
			List<Umjetnina> umjetnine, Image slika, boolean online,
			boolean admin) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.zaporka = zaporka;
		this.email = email;
		this.telefon = telefon;
		this.adresa = adresa;
		this.osobniStatus = osobniStatus;
		this.zvanje = zvanje;
		this.grana = grana;
		this.podgrana = podgrana;
		this.umjetnine = umjetnine;
		this.slika = slika;
		this.online = online;
		this.admin = admin;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getZaporka() {
        return zaporka;
    }

    public void setZaporka(String zaporka) {
        this.zaporka = zaporka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getOsobniStatus() {
        return osobniStatus;
    }

    public void setOsobniStatus(String osobniStatus) {
        this.osobniStatus = osobniStatus;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public String getGrana() {
		return grana;
	}

	public void setGrana(String grana) {
		this.grana = grana;
	}

	public String getPodgrana() {
		return podgrana;
	}

	public void setPodgrana(String podgrana) {
		this.podgrana = podgrana;
	}

	public List<Umjetnina> getUmjetnine() {
		return umjetnine;
	}

	public void setUmjetnine(List<Umjetnina> umjetnine) {
		this.umjetnine = umjetnine;
	}

	public Image getSlika() {
		return slika;
	}

	public void setSlika(Image slika) {
		this.slika = slika;
	}
    
}
