package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;

public final class LogoutZahtjev extends Zahtjev implements Serializable {
    private static final long serialVersionUID = 1990808277981170449L;

    public static final LogoutZahtjev INSTANCE = new LogoutZahtjev();

    private LogoutZahtjev() {

    }
}
