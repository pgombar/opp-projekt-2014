package hr.fer.opp.projekt.common.model;

import hr.fer.opp.projekt.common.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;

@Entity
public final class Poruka implements Serializable {
	private static final long serialVersionUID = 8387986578686041475L;

	@Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String poruka;

    @Column(nullable = false)
    @ManyToOne
    private Korisnik korisnikOd;

    @Column(nullable = false)
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
