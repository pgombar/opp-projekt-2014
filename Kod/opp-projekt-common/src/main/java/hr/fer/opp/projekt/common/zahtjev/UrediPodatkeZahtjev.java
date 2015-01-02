package hr.fer.opp.projekt.common.zahtjev;

import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.Serializable;

public final class UrediPodatkeZahtjev implements Zahtjev, Serializable {
    private static final long serialVersionUID = -4870075412240218110L;

    private final Korisnik umjetnik;

    public UrediPodatkeZahtjev(Korisnik umjetnik) {
        this.umjetnik = umjetnik;
    }

    public Korisnik getUmjetnik() {
        return umjetnik;
    }
}
