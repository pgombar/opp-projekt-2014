package hr.fer.opp.projekt.common.model;

public final class Grana {
    private long id;

    private String ime;

    public Grana(long id, String ime) {
        this.id = id;
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
