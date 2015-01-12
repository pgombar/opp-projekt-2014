package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;

public final class AdminUkloniKorisnikaZahtjev extends Zahtjev implements Serializable {
    private static final long serialVersionUID = -8470116167883648995L;

    private final long id;

    public AdminUkloniKorisnikaZahtjev(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
