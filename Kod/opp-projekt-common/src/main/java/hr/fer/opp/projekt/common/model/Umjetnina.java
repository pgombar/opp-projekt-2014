package hr.fer.opp.projekt.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public final class Umjetnina implements Serializable {

	private static final long serialVersionUID = -435238936817101076L;

	@Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String tehnika;

    @Column(nullable = false)
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
