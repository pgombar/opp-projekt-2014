package hr.fer.opp.projekt.common.zahtjev;

import java.io.Serializable;
import java.util.Date;

public final class UkrcajFotografijuUmjetnineZahtjev implements Zahtjev, Serializable {
    private static final long serialVersionUID = 4797035126929496928L;

    private final byte[] slika;

    private final String naziv;

    private final String tehnika;

    private final Date datumNastanka;

    public UkrcajFotografijuUmjetnineZahtjev(byte[] slika, String naziv, String tehnika, Date datumNastanka) {
        this.slika = slika;
        this.naziv = naziv;
        this.tehnika = tehnika;
        this.datumNastanka = datumNastanka;
    }

    public byte[] getSlika() {
        return slika;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getTehnika() {
        return tehnika;
    }

    public Date getDatumNastanka() {
        return datumNastanka;
    }
}
