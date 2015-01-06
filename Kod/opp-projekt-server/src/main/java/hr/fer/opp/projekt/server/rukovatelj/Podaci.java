package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Podgrana;
import hr.fer.opp.projekt.common.model.Umjetnina;

import java.awt.Image;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public final class Podaci {
    public static final Umjetnina UMJETNINA_1 = new Umjetnina(1, "Najbolja", "Tehnika", new Date(2014, 12, 25, 12, 24));
    public static final Umjetnina UMJETNINA_2 = new Umjetnina(2, "Superiska", "Tehnikalija", new Date(2013, 12, 25, 12, 24));
    public static final List<Umjetnina> UMJETNINE = Arrays.asList(UMJETNINA_1, UMJETNINA_2);

    public static final Grana GRANA_1 = new Grana("slikarstvo");
    public static final Grana GRANA_2 = new Grana("kiparstvo");

    public static final Podgrana PODGRANA_1 = new Podgrana("slikarenje");
    public static final Podgrana PODGRANA_2 = new Podgrana("kiparenje");

    public static final Korisnik KORISNIK_1 = new Korisnik("Pero", "Peric", "pperic", "pero", "pero@peric.com",
            "123-456-789", "Adresa 1", "Moj osobni status", "mehanicar", GRANA_1, PODGRANA_1, UMJETNINE, null, true, false);
    public static final Korisnik KORISNIK_2 = new Korisnik("Ivo", "Ivic", "iivic", "ivo", "ivo@ivic.com",
            "321-654-987", "Adresa 5", "Ja sam najbolji", "vozac", GRANA_2, PODGRANA_2, UMJETNINE, null, false, false);
}
