package hr.fer.opp.projekt.common.odgovor;

import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.Serializable;

public final class DohvatiUmjetnikaOdgovor implements Odgovor, Serializable {
    private static final long serialVersionUID = 6239576624595175096L;

    private final Korisnik umjetnik;

    public DohvatiUmjetnikaOdgovor(Korisnik umjetnik) {
        this.umjetnik = umjetnik;
    }

    public Korisnik getUmjetnik() {
        return umjetnik;
    }
}
