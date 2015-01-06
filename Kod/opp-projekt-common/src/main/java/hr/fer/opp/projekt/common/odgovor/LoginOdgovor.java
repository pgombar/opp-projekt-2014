package hr.fer.opp.projekt.common.odgovor;

import java.io.Serializable;

public final class LoginOdgovor implements Odgovor, Serializable {
    private static final long serialVersionUID = -7194425896087464048L;

    private final boolean uspjeh;

    private final String porukaGreske;
    
    private LoginOdgovor(boolean uspjeh, String porukaGreske) {
        this.uspjeh = uspjeh;
        this.porukaGreske = porukaGreske;
    }

    public boolean isUspjeh() {
        return uspjeh;
    }

    public String getPorukaGreske() {
        return porukaGreske;
    }
    
    public static LoginOdgovor success() {
        return new LoginOdgovor(true, "");
    }

    public static LoginOdgovor fail(String porukaGreske) {
        return new LoginOdgovor(false, porukaGreske);
    }
}

