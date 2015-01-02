package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;

public final class LoginZahtjev implements Zahtjev, Serializable {
    private static final long serialVersionUID = -7471217738657118945L;

    private final String korisnickoIme;

    private final String zaporka;

    public LoginZahtjev(final String korisnickoIme, final String zaporka) {
        this.korisnickoIme = korisnickoIme;
        this.zaporka = zaporka;
    }


    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getZaporka() {
        return zaporka;
    }

    @Override
    public String toString() {
        return String.format("Login Zahtjev: username=%s", korisnickoIme);
    }
}
