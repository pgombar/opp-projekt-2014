package hr.fer.opp.projekt.common.odgovor;

import java.io.Serializable;

public final class AdminUkloniKorisnikaOdgovor implements Odgovor, Serializable {
    private static final long serialVersionUID = -7738330079365404793L;

    public static final AdminUkloniKorisnikaOdgovor NEUSPJEH = new AdminUkloniKorisnikaOdgovor(false);

    public static final AdminUkloniKorisnikaOdgovor USPJEH = new AdminUkloniKorisnikaOdgovor(true);

    private final boolean uspjeh;

    private AdminUkloniKorisnikaOdgovor(boolean uspjeh) {
        this.uspjeh = uspjeh;
    }

    public boolean isUspjeh() {
        return uspjeh;
    }
}