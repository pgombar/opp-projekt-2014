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

    @Column
    @Lob
    @Basic(fetch = FetchType.EAGER)
    private Blob slikaBlob;

    @ManyToOne(fetch =  FetchType.EAGER)
    private Korisnik korisnik;

    @Transient
    private byte[] slika;

    protected Umjetnina() {

    }

    public Umjetnina(String ime, String tehnika, Date datumNastanka, BufferedImage slika, Korisnik korisnik) {
        this.ime = ime;
        this.tehnika = tehnika;
        this.datumNastanka = datumNastanka;
        this.korisnik = korisnik;

        setSlika(slika);
    }

    @PostLoad
    public void postLoad() {
        if (slikaBlob != null) {
            this.slika = ImageUtil.loadBlob(slikaBlob);
        }
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

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public BufferedImage getSlika() {
        if (slika == null) {
            return null;
        } else {
            return ImageUtil.byteArrayToImage(this.slika);
        }
    }

    public void setSlika(BufferedImage slika) {
        if (slika == null) {
            this.slika = null;
            this.slikaBlob = null;
        } else {
            this.slika = ImageUtil.imageToByteArray(slika);

            try {
                this.slikaBlob = new SerialBlob(this.slika);
            } catch (SQLException e) {
                this.slikaBlob = null;
                this.slika = null;
            }
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeLong(id);
        out.writeObject(ime);
        out.writeObject(tehnika);
        out.writeObject(datumNastanka);
        out.writeObject(korisnik);
        out.writeObject(slika);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        id = in.readLong();
        ime = (String) in.readObject();
        tehnika = (String) in.readObject();
        datumNastanka = (Date) in.readObject();
        korisnik = (Korisnik) in.readObject();
        slika = (byte[]) in.readObject();
    }
}
