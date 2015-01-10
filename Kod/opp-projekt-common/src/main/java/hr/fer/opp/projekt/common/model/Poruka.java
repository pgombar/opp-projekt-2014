package hr.fer.opp.projekt.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public final class Poruka implements Serializable {
	
	private static final long serialVersionUID = 8387986578686041475L;

	@Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String poruka;

    @ManyToOne
    private Korisnik korisnikOd;

    @ManyToOne
    private Korisnik korisnikDo;

    protected Poruka() {

    }

	public Poruka(String poruka, Korisnik korisnikOd, Korisnik korisnikDo) {
		this.poruka = poruka;
		this.korisnikOd = korisnikOd;
		this.korisnikDo = korisnikDo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPoruka() {
		return poruka;
	}

	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}

	public Korisnik getKorisnikOd() {
		return korisnikOd;
	}

	public void setKorisnikOd(Korisnik korisnikOd) {
		this.korisnikOd = korisnikOd;
	}

	public Korisnik getKorisnikDo() {
		return korisnikDo;
	}

	public void setKorisnikDo(Korisnik korisnikDo) {
		this.korisnikDo = korisnikDo;
	}
}
