package hr.fer.opp.projekt.common.odgovor;

import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class UrediPodatkeOdgovor implements Odgovor, Serializable {
    private static final long serialVersionUID = 4279733802714465993L;

    private final Korisnik korisnik;

    private final List<String> greske;

    public UrediPodatkeOdgovor(Korisnik korisnik) {
        this.korisnik = korisnik;
        this.greske = new ArrayList<>();
    }

    public UrediPodatkeOdgovor(List<String> greske) {
        this.korisnik = null;
        this.greske = greske;
    }

    public List<String> getGreske() {
        return greske;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }
}
