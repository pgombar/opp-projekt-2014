package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;

public final class RegistracijaZahtjev implements Zahtjev, Serializable {
    private static final long serialVersionUID = -5884335992897462654L;

    private final String ime;

    private final String prezime;

    private final String korisnickoIme;

    private final String zaporka;

    private final String email;

    private final String telefon;

    public RegistracijaZahtjev(String ime, String prezime, String korisnickoIme, String zaporka, String email, String telefon) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.zaporka = zaporka;
        this.email = email;
        this.telefon = telefon;
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
}
