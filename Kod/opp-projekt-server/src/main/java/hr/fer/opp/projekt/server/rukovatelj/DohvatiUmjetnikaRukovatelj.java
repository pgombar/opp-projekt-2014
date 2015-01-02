package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.odgovor.DohvatiUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.DohvatiUmjetnikaZahtjev;
import org.springframework.stereotype.Component;

@Component
public final class DohvatiUmjetnikaRukovatelj implements RukovateljZahtjevom<DohvatiUmjetnikaZahtjev, DohvatiUmjetnikaOdgovor> {
    @Override
    public DohvatiUmjetnikaOdgovor handle(DohvatiUmjetnikaZahtjev zahtjev) {
        if (zahtjev.getId() == 1) return new DohvatiUmjetnikaOdgovor(Podaci.KORISNIK_1);
        else if (zahtjev.getId() == 2) return new DohvatiUmjetnikaOdgovor(Podaci.KORISNIK_2);
        else return new DohvatiUmjetnikaOdgovor(null);
    }
}
