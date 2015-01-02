package hr.fer.opp.projekt.common.model;

public final class Podgrana {
    private long id;

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
