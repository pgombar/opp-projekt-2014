package hr.fer.opp.projekt.common.odgovor;

import java.io.Serializable;

public class LogoutOdgovor implements Odgovor, Serializable {
    private static final long serialVersionUID = -3619781530899323189L;

    public static final LogoutOdgovor INSTANCE = new LogoutOdgovor();

    private LogoutOdgovor() {
    }
}
