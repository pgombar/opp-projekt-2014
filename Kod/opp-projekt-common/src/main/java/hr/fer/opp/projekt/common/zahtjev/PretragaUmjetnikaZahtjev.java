package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;

public final class PretragaUmjetnikaZahtjev implements Zahtjev, Serializable {
    private static final long serialVersionUID = -406254014462036980L;

    private final String ime;

    private final String prezime;

    private final String korisnickoIme;

    public PretragaUmjetnikaZahtjev(String ime, String prezime, String korisnickoIme) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }
}
