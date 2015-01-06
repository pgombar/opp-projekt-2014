package hr.fer.opp.projekt.common.odgovor;

import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.Serializable;

public final class LoginOdgovor implements Odgovor, Serializable {
    private static final long serialVersionUID = -7194425896087464048L;

    private final boolean uspjeh;

    private final String porukaGreske;
    
    private final Korisnik korisnik;
    
    private LoginOdgovor(boolean uspjeh, String porukaGreske, Korisnik korisnik) {
        this.uspjeh = uspjeh;
        this.porukaGreske = porukaGreske;
        this.korisnik = korisnik;
    }

    public boolean isUspjeh() {
        return uspjeh;
    }

    public Korisnik getKorisnik() {
    	return korisnik;
    }
    
    public String getPorukaGreske() {
        return porukaGreske;
    }
    
    public static LoginOdgovor success(Korisnik korisnik) {
        return new LoginOdgovor(true, "", korisnik);
    }

    public static LoginOdgovor fail(String porukaGreske) {
        return new LoginOdgovor(false, porukaGreske, null);
    }
}

