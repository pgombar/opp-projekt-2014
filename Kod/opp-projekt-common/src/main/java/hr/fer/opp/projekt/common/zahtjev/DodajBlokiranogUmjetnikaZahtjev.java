package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;

public final class DodajBlokiranogUmjetnikaZahtjev implements Zahtjev, Serializable {
    private static final long serialVersionUID = 476691795505187092L;

    private final long umjetnik;

    private final long blokiraniUmjetnik;

    public DodajBlokiranogUmjetnikaZahtjev(long umjetnik, long blokiraniUmjetnik) {
        this.umjetnik = umjetnik;
        this.blokiraniUmjetnik = blokiraniUmjetnik;
    }

    public long getBlokiraniUmjetnik() {
        return blokiraniUmjetnik;
    }

    public long getUmjetnik() {
        return umjetnik;
    }
}
