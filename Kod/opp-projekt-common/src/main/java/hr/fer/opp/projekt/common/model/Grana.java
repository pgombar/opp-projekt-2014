package hr.fer.opp.projekt.common.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

@Entity
public final class Grana implements Serializable {

	private static final long serialVersionUID = -4032344855503822896L;

	@Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String ime;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "grana")
    private List<Podgrana> podgrane;

    protected Grana() {

    }

    public Grana(String ime, Podgrana... podgrane) {
        this.ime = ime;
        this.podgrane = Arrays.asList(podgrane);
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return ime;
    }

}
