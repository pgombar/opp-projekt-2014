package hr.fer.opp.projekt.common.odgovor;

import java.io.Serializable;

public class UkloniPorukeKorisnikaOdgovor implements Odgovor, Serializable {

	private static final long serialVersionUID = -7265086363530792924L;
	
	public static final UkloniPorukeKorisnikaOdgovor INSTANCE = new UkloniPorukeKorisnikaOdgovor();

    private UkloniPorukeKorisnikaOdgovor() {
    }
}
