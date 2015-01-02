package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Umjetnina;

import java.util.Date;

public final class Podaci {
    public static final Korisnik KORISNIK_1 = new Korisnik(1, "Pero", "Peric", "pperic", "pero", "pero@peric.com",
            "123-456-789", "Adresa 1", "Moj osobni status", true, false);
    public static final Korisnik KORISNIK_2 = new Korisnik(2, "Ivo", "Ivic", "iivic", "ivo", "ivo@ivic.com",
            "321-654-987", "Adresa 5", "Ja sam najbolji", false, false);

    public static final Umjetnina UMJETNINA_1 = new Umjetnina(1, "Najbolja", "Tehnika", new Date(2014, 12, 25, 12, 24));
}
