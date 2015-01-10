package hr.fer.opp.projekt.common.model;

import hr.fer.opp.projekt.common.util.ImageUtil;

import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public final class Korisnik implements Serializable {

    private static final long serialVersionUID = -4322708051779590572L;

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String ime;

    @Column
    private String prezime;

    @Column(nullable = false, unique = true)
    private String korisnickoIme;

    @Column(nullable = false)
    private String zaporka;

    @Column(nullable = false)
    private String email;

    @Column
    private String telefon;

    @Column
    private String adresa;

    @Column
    private String osobniStatus;

    @Column
    private String zvanje;

    @ManyToOne
    private Grana grana;

    @ManyToOne
    private Podgrana podgrana;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "korisnik")
    private List<Umjetnina> umjetnine;

    @Column
    @Lob
    @Basic(fetch = FetchType.EAGER)
    private Blob slikaBlob;

    @Column
    private boolean online;

    @Column
    private Date zadnjiPutAktivan;

    @Transient
    private byte[] slika;

    @Column
    private boolean admin;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "OmiljeniUmjetnici",
            joinColumns = @JoinColumn(name = "korisnikId"), inverseJoinColumns = @JoinColumn(name = "omiljeniId"))
    private List<Korisnik> omiljeniUmjetnici;

    @ManyToMany
    @JoinTable(name = "OmiljeniUmjetnici",
            joinColumns = @JoinColumn(name = "omiljeniId"), inverseJoinColumns = @JoinColumn(name = "korisnikId"))
    private List<Korisnik> omiljeniUmjetniciOd;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "BlokiraniUmjetnici",
            joinColumns = @JoinColumn(name = "korisnikId"), inverseJoinColumns = @JoinColumn(name = "blokiraniId"))
    private List<Korisnik> blokiraniUmjetnici;

    @ManyToMany
    @JoinTable(name = "BlokiraniUmjetnici",
            joinColumns = @JoinColumn(name = "blokiraniId"), inverseJoinColumns = @JoinColumn(name = "korisnikId"))
    private List<Korisnik> blokiraniUmjetniciOd;

    protected Korisnik() {
    }

    public Korisnik(String ime, String prezime, String korisnickoIme,
                    String zaporka, String email, String telefon, String adresa,
                    String osobniStatus, String zvanje, Grana grana, Podgrana podgrana,
                    List<Umjetnina> umjetnine, BufferedImage slika, boolean admin) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.zaporka = zaporka;
        this.email = email;
        this.telefon = telefon;
        this.adresa = adresa;
        this.osobniStatus = osobniStatus;
        this.zvanje = zvanje;
        this.grana = grana;
        this.podgrana = podgrana;
        this.umjetnine = umjetnine;
        this.admin = admin;

        setSlika(slika);
    }

    @PostLoad
    public void postLoad() {
        if (slikaBlob != null) {
            this.slika = ImageUtil.loadBlob(slikaBlob);
        }
    }

    public void merge(Korisnik korisnikZahtjev) {
        this.admin = korisnikZahtjev.admin;
        this.adresa = korisnikZahtjev.adresa;
        this.email = korisnikZahtjev.email;
        this.grana = korisnikZahtjev.grana;
        this.ime = korisnikZahtjev.ime;
        this.korisnickoIme = korisnikZahtjev.korisnickoIme;
        this.osobniStatus = korisnikZahtjev.osobniStatus;
        this.podgrana = korisnikZahtjev.podgrana;
        this.prezime = korisnikZahtjev.prezime;
        this.telefon = korisnikZahtjev.telefon;
        this.zaporka = korisnikZahtjev.zaporka;
        this.zvanje = korisnikZahtjev.zvanje;
        setSlika(korisnikZahtjev.getSlika());
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

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getZaporka() {
        return zaporka;
    }

    public void setZaporka(String zaporka) {
        this.zaporka = zaporka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getOsobniStatus() {
        return osobniStatus;
    }

    public void setOsobniStatus(String osobniStatus) {
        this.osobniStatus = osobniStatus;
    }

    public boolean isOnline() {
        return online && new Date().getTime() - this.zadnjiPutAktivan.getTime() < 5 * 60 * 1000;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getZvanje() {
        return zvanje;
    }

    public void setZvanje(String zvanje) {
        this.zvanje = zvanje;
    }

    public Grana getGrana() {
        return grana;
    }

    public void setGrana(Grana grana) {
        this.grana = grana;
    }

    public Podgrana getPodgrana() {
        return podgrana;
    }

    public void setPodgrana(Podgrana podgrana) {
        this.podgrana = podgrana;
    }

    public List<Umjetnina> getUmjetnine() {
        return umjetnine;
    }

    public void setUmjetnine(List<Umjetnina> umjetnine) {
        this.umjetnine = umjetnine;
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

    public List<Korisnik> getOmiljeniUmjetnici() {
        return omiljeniUmjetnici;
    }

    public void setOmiljeniUmjetnici(List<Korisnik> omiljeniUmjetnici) {
        this.omiljeniUmjetnici = omiljeniUmjetnici;
    }

    public List<Korisnik> getBlokiraniUmjetnici() {
        return blokiraniUmjetnici;
    }

    public void setBlokiraniUmjetnici(List<Korisnik> blokiraniUmjetnici) {
        this.blokiraniUmjetnici = blokiraniUmjetnici;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Korisnik korisnik = (Korisnik) o;

        return Objects.equals(id, korisnik.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeLong(id);
        out.writeObject(ime);
        out.writeObject(prezime);
        out.writeObject(korisnickoIme);
        out.writeObject(zaporka);
        out.writeObject(email);
        out.writeObject(telefon);
        out.writeObject(adresa);
        out.writeObject(osobniStatus);
        out.writeObject(zvanje);
        out.writeObject(grana);
        out.writeObject(podgrana);
        out.writeObject(umjetnine);
        out.writeBoolean(online);
        out.writeObject(zadnjiPutAktivan);
        out.writeObject(slika);
        out.writeBoolean(admin);
        out.writeObject(omiljeniUmjetnici);
        out.writeObject(blokiraniUmjetnici);
    }

    @SuppressWarnings(value = "unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        id = in.readLong();
        ime = (String) in.readObject();
        prezime = (String) in.readObject();
        korisnickoIme = (String) in.readObject();
        zaporka = (String) in.readObject();
        email = (String) in.readObject();
        telefon = (String) in.readObject();
        adresa = (String) in.readObject();
        osobniStatus = (String) in.readObject();
        zvanje = (String) in.readObject();
        grana = (Grana) in.readObject();
        podgrana = (Podgrana) in.readObject();
        umjetnine = (List<Umjetnina>) in.readObject();
        online = in.readBoolean();
        zadnjiPutAktivan = (Date) in.readObject();
        slika = (byte[]) in.readObject();
        admin = in.readBoolean();
        omiljeniUmjetnici = (List<Korisnik>) in.readObject();
        blokiraniUmjetnici = (List<Korisnik>) in.readObject();
    }
}
