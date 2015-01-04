package hr.fer.opp.projekt.common.model;

import java.io.Serializable;

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

    private boolean online;

    private boolean admin;

    public Korisnik(long id, String ime, String prezime, String korisnickoIme, String zaporka, String email, String telefon, String adresa, String osobniStatus, boolean online, boolean admin) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.zaporka = zaporka;
        this.email = email;
        this.telefon = telefon;
        this.adresa = adresa;
        this.osobniStatus = osobniStatus;
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
}
