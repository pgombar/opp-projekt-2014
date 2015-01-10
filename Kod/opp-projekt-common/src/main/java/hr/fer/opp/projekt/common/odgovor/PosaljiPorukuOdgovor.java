package hr.fer.opp.projekt.common.odgovor;

import java.io.Serializable;

public class PosaljiPorukuOdgovor implements Odgovor, Serializable {

	private static final long serialVersionUID = -6098650859809849865L;
	
	public static final PosaljiPorukuOdgovor INSTANCE = new PosaljiPorukuOdgovor();

    private PosaljiPorukuOdgovor() {
    }
}
