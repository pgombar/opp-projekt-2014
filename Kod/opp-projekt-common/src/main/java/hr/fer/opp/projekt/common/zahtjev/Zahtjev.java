package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;
import java.util.UUID;

public abstract class Zahtjev implements Serializable {
	private static final long serialVersionUID = -7643135844851070565L;

	private final String zahtjevId;
	
	public Zahtjev() {
		 zahtjevId = UUID.randomUUID().toString();
	}

	public String getZahtjevId() {
		return zahtjevId;
	}
}
