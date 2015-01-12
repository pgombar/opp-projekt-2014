package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;

public final class AdminDodajKorisnikaZahtjev extends Zahtjev implements Serializable {
    private static final long serialVersionUID = -6837487801027298727L;

    private final String korisnickoIme;

    private final String zaporka;

    private final String email;

    public AdminDodajKorisnikaZahtjev(String korisnickoIme, String zaporka, String email) {
        this.korisnickoIme = korisnickoIme;
        this.zaporka = zaporka;
        this.email = email;
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
}
