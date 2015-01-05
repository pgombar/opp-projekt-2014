package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;

public final class PopisUmjetnikaZahtjev implements  Zahtjev, Serializable {
    private static final long serialVersionUID = -3557731750421895336L;

    public static final PopisUmjetnikaZahtjev INSTANCE = new PopisUmjetnikaZahtjev();

    public PopisUmjetnikaZahtjev() {

    }
}
