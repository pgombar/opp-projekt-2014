package hr.fer.opp.projekt.common.odgovor;

import hr.fer.opp.projekt.common.model.Poruka;

import java.io.Serializable;
import java.util.List;

public final class DohvatiPorukeOdgovor implements Odgovor, Serializable {

	private static final long serialVersionUID = -1665243794422968871L;

	private List<Poruka> poruke;

	public DohvatiPorukeOdgovor(List<Poruka> poruke) {
		this.poruke = poruke;
	}

	public List<Poruka> getPoruke() {
		return poruke;
	}

	public void setPoruke(List<Poruka> poruke) {
		this.poruke = poruke;
	}
}
