package hr.fer.opp.projekt.common.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public final class Podgrana implements Serializable {

	private static final long serialVersionUID = 6820252631019283213L;

	@Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Grana grana;

    @Column(nullable = false)
    private String ime;

    protected Podgrana() {

    }

    public Podgrana(Grana grana, String ime) {
        this.grana = grana;
        this.ime = ime;
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

    public String toString() {
        return ime;
    }

}
