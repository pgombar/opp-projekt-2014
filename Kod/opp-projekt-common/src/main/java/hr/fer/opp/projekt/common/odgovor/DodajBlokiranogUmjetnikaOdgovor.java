package hr.fer.opp.projekt.common.odgovor;

import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.Serializable;
import java.util.List;

public final class DodajBlokiranogUmjetnikaOdgovor implements Odgovor, Serializable {
    private static final long serialVersionUID = -1585523171529687046L;

    private final List<Korisnik> omiljeniUmjetnici;

    public DodajBlokiranogUmjetnikaOdgovor(List<Korisnik> omiljeniUmjetnici) {
        this.omiljeniUmjetnici = omiljeniUmjetnici;
    }

    public List<Korisnik> getOmiljeniUmjetnici() {
        return omiljeniUmjetnici;
    }
}
