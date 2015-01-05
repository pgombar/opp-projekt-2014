package hr.fer.opp.projekt.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public final class Podgrana {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String ime;

    public Podgrana(long id, String ime) {
        this.id = id;
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
}
