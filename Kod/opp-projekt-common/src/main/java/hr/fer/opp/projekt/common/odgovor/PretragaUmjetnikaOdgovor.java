package hr.fer.opp.projekt.common.odgovor;

import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.Serializable;
import java.util.List;

public final class PretragaUmjetnikaOdgovor implements Odgovor, Serializable {
    private static final long serialVersionUID = -8391964189928958646L;

    private final List<Korisnik> rezultati;

    public PretragaUmjetnikaOdgovor(List<Korisnik> rezultati) {
        this.rezultati = rezultati;
    }

    public List<Korisnik> getRezultati() {
        return rezultati;
    }
}
