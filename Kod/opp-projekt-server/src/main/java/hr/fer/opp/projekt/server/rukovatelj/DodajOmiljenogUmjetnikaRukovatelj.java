package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.odgovor.DodajOmiljenogUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.DodajOmiljenogUmjetnikaZahtjev;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public final class DodajOmiljenogUmjetnikaRukovatelj implements RukovateljZahtjevom<DodajOmiljenogUmjetnikaZahtjev, DodajOmiljenogUmjetnikaOdgovor> {
    @Override
    public DodajOmiljenogUmjetnikaOdgovor handle(DodajOmiljenogUmjetnikaZahtjev zahtjev) {
        return new DodajOmiljenogUmjetnikaOdgovor(Arrays.asList(Podaci.KORISNIK_1, Podaci.KORISNIK_2));
    }
}
