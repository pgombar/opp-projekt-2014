package hr.fer.opp.projekt.common.odgovor;

import hr.fer.opp.projekt.common.model.Umjetnina;

import java.io.Serializable;

public final class UkrcajFotografijuUmjetnineOdgovor implements Odgovor, Serializable {
    private static final long serialVersionUID = -6697459585501226340L;

    private final Umjetnina umjetnina;

    public UkrcajFotografijuUmjetnineOdgovor(Umjetnina umjetnina) {
        this.umjetnina = umjetnina;
    }

    public Umjetnina getUmjetnina() {
        return umjetnina;
    }
}
