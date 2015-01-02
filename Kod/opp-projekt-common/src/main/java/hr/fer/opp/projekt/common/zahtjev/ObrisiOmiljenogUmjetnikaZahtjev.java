package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;

public final class ObrisiOmiljenogUmjetnikaZahtjev implements Zahtjev, Serializable {
    private static final long serialVersionUID = -9195481517628955512L;

    private final long umjetnik;

    private final long omiljeniUmjetnik;

    public ObrisiOmiljenogUmjetnikaZahtjev(long umjetnik, long omiljeniUmjetnik) {
        this.umjetnik = umjetnik;
        this.omiljeniUmjetnik = omiljeniUmjetnik;
    }

    public long getOmiljeniUmjetnik() {
        return omiljeniUmjetnik;
    }

    public long getUmjetnik() {
        return umjetnik;
    }
}
