package hr.fer.opp.projekt.common.odgovor;

import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.Serializable;
import java.util.List;

public final class ObrisiOmiljenogUmjetnikaOdgovor implements Odgovor, Serializable {
    private static final long serialVersionUID = -6501310805416392781L;

    private final List<Korisnik> omiljeniUmjetnici;

    public ObrisiOmiljenogUmjetnikaOdgovor(List<Korisnik> omiljeniUmjetnici) {
        this.omiljeniUmjetnici = omiljeniUmjetnici;
    }

    public List<Korisnik> getOmiljeniUmjetnici() {
        return omiljeniUmjetnici;
    }
}
