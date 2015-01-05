package hr.fer.opp.projekt.common.model;

import javax.persistence.*;
import java.awt.Image;
import java.io.Serializable;
import java.util.List;

@Entity
public final class Korisnik implements Serializable {

    private static final long serialVersionUID = -4322708051779590572L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;

    @Column(nullable = false)
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

    @OneToMany(fetch = FetchType.EAGER)
    private List<Umjetnina> umjetnine;

    @Transient
    private Image slika;

    @Transient
    private boolean online;

    @Column
    private boolean admin;

    protected Korisnik() {

    }

    public Korisnik(long id, String ime, String prezime, String korisnickoIme,
                    String zaporka, String email, String telefon, String adresa,
                    String osobniStatus, String zvanje, Grana grana, Podgrana podgrana,
                    List<Umjetnina> umjetnine, Image slika, boolean online,
                    boolean admin) {
        this.id = id;
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
        this.slika = slika;
        this.online = online;
        this.admin = admin;
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
        return online;
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

    public Image getSlika() {
        return slika;
    }

    public void setSlika(Image slika) {
        this.slika = slika;
    }

}
