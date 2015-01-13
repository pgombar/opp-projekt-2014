package hr.fer.opp.projekt.common.util;

import java.io.Serializable;

import hr.fer.opp.projekt.common.odgovor.Odgovor;
import hr.fer.opp.projekt.common.zahtjev.Zahtjev;

public final class ZahtjevOdgovor implements Serializable {
	private static final long serialVersionUID = 4829735337983783028L;

	private final Zahtjev zahtjev;
	
	private final Odgovor odgovor;

	public ZahtjevOdgovor(Zahtjev zahtjev, Odgovor odgovor) {
		this.zahtjev = zahtjev;
		this.odgovor = odgovor;
	}

	public Zahtjev getZahtjev() {
		return zahtjev;
	}

	public Odgovor getOdgovor() {
		return odgovor;
	}	
}
