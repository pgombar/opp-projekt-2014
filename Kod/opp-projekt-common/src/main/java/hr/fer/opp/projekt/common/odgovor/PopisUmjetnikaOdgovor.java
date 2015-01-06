package hr.fer.opp.projekt.common.odgovor;

import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.Serializable;
import java.util.List;

public final class PopisUmjetnikaOdgovor implements Odgovor, Serializable {

    private static final long serialVersionUID = -480988111181905433L;

    private final List<Korisnik> rezultati;

    public PopisUmjetnikaOdgovor(List<Korisnik> rezultati) {
        this.rezultati = rezultati;
    }

    public List<Korisnik> getRezultati() {
        return rezultati;
    }

}
