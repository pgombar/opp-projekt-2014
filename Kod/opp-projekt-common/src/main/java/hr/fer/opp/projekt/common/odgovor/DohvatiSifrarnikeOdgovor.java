package hr.fer.opp.projekt.common.odgovor;

import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Podgrana;

import java.io.Serializable;
import java.util.List;

public final class DohvatiSifrarnikeOdgovor implements Odgovor, Serializable {
    private static final long serialVersionUID = -1793849402266833165L;

    private final List<Grana> grane;

    private final List<Podgrana> podgrane;

    public DohvatiSifrarnikeOdgovor(List<Grana> grane, List<Podgrana> podgrane) {
        this.grane = grane;
        this.podgrane = podgrane;
    }

    public List<Grana> getGrane() {
        return grane;
    }

    public List<Podgrana> getPodgrane() {
        return podgrane;
    }
}
