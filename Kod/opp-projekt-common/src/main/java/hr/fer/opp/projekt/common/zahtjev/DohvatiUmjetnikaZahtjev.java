package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;

public final class DohvatiUmjetnikaZahtjev extends Zahtjev implements Serializable {
    private static final long serialVersionUID = -5811564540048791586L;

    private final long id;

    public DohvatiUmjetnikaZahtjev(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
