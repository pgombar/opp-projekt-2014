package hr.fer.opp.projekt.common.odgovor;

import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.Serializable;
import java.util.List;

public final class DodajBlokiranogUmjetnikaOdgovor implements Odgovor, Serializable {
    private static final long serialVersionUID = -1585523171529687046L;

    private final List<Korisnik> blokiraniUmjetnici;

    public DodajBlokiranogUmjetnikaOdgovor(List<Korisnik> blokiraniUmjetnici) {
        this.blokiraniUmjetnici = blokiraniUmjetnici;
    }

    public List<Korisnik> getBlokiraniUmjetnici() {
        return blokiraniUmjetnici;
    }
}
