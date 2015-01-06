package hr.fer.opp.projekt.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public final class Grana implements Serializable {

	private static final long serialVersionUID = -4032344855503822896L;

	@Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String ime;


    protected Grana() {

    }

    public String toString() {
    	return ime;
    }
    
    public Grana(String ime) {
        this.ime = ime;
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

}
