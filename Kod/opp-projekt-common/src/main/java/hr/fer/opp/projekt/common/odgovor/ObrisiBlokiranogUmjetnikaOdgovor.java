package hr.fer.opp.projekt.common.odgovor;

import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.Serializable;
import java.util.List;

public class ObrisiBlokiranogUmjetnikaOdgovor implements Odgovor, Serializable {
    private static final long serialVersionUID = -6501310805416392781L;

    private final List<Korisnik> blokiraniUmjetnici;

    public ObrisiBlokiranogUmjetnikaOdgovor(List<Korisnik> blokiraniUmjetnici) {
        this.blokiraniUmjetnici = blokiraniUmjetnici;
    }

    public List<Korisnik> getBlokiraniUmjetnici() {
        return blokiraniUmjetnici;
    }

}
