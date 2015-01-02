package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;

public final class ObrisiBlokiranogUmjetnikaZahtjev implements Zahtjev, Serializable {
    private static final long serialVersionUID = -811726288871861997L;

    private final long umjetnik;

    private final long blokiraniUmjetnik;

    public ObrisiBlokiranogUmjetnikaZahtjev(long umjetnik, long blokiraniUmjetnik) {
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
