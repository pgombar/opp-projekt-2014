package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;

public final class AdminDodajKorisnikaZahtjev implements Zahtjev, Serializable {
    private static final long serialVersionUID = -6837487801027298727L;

    private final String ime;

    private final String prezime;

    private final String korisnickoIme;

    private final String zaporka;

    private final String email;

    private final String telefon;

    public AdminDodajKorisnikaZahtjev(String ime, String prezime, String korisnickoIme, String zaporka, String email, String telefon) {
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
