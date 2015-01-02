package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;

public final class DodajOmiljenogUmjetnikaZahtjev implements Zahtjev, Serializable {
    private static final long serialVersionUID = 1981754062924024442L;

    private final long umjetnik;

    private final long omiljeniUmjetnik;

    public DodajOmiljenogUmjetnikaZahtjev(long umjetnik, long omiljeniUmjetnik) {
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
