package hr.fer.opp.projekt.common.model;

import java.util.Date;

public final class Umjetnina {
    private long id;

    private String ime;

    private String tehnika;

    private Date datumNastanka;

    public Umjetnina(long id, String ime, String tehnika, Date datumNastanka) {
        this.id = id;
        this.ime = ime;
        this.tehnika = tehnika;
        this.datumNastanka = datumNastanka;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getTehnika() {
        return tehnika;
    }

    public void setTehnika(String tehnika) {
        this.tehnika = tehnika;
    }

    public Date getDatumNastanka() {
        return datumNastanka;
    }

    public void setDatumNastanka(Date datumNastanka) {
        this.datumNastanka = datumNastanka;
    }
}
