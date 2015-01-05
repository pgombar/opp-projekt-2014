package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Umjetnina;

import java.awt.Image;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public final class Podaci {
    public static final Umjetnina UMJETNINA_1 = new Umjetnina(1, "Najbolja", "Tehnika", new Date(2014, 12, 25, 12, 24));
    public static final Umjetnina UMJETNINA_2 = new Umjetnina(2, "Superiska", "Tehnikalija", new Date(2013, 12, 25, 12, 24));
    public static final List<Umjetnina> UMJETNINE = Arrays.asList(UMJETNINA_1, UMJETNINA_2);
    
    public static final Korisnik KORISNIK_1 = new Korisnik(1, "Pero", "Peric", "pperic", "pero", "pero@peric.com",
            "123-456-789", "Adresa 1", "Moj osobni status", "mehanicar", "slikarstvo", "slikarenje", UMJETNINE, null, true, false);
    public static final Korisnik KORISNIK_2 = new Korisnik(2, "Ivo", "Ivic", "iivic", "ivo", "ivo@ivic.com",
            "321-654-987", "Adresa 5", "Ja sam najbolji", "vozac", "kiparstvo", "kiparenje", UMJETNINE, null, false, false);
}
