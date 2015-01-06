package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;

public final class DohvatiSifrarnikeZahtjev implements Zahtjev, Serializable {
    private static final long serialVersionUID = -3450923713721050555L;

    public static final DohvatiSifrarnikeZahtjev INSTANCE = new DohvatiSifrarnikeZahtjev();

    private DohvatiSifrarnikeZahtjev() {
    }
}
