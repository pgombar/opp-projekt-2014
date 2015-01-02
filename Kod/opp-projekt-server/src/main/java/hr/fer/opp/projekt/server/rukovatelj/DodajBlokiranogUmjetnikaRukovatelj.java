package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.odgovor.DodajBlokiranogUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.DodajBlokiranogUmjetnikaZahtjev;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public final class DodajBlokiranogUmjetnikaRukovatelj implements RukovateljZahtjevom<DodajBlokiranogUmjetnikaZahtjev, DodajBlokiranogUmjetnikaOdgovor> {
    @Override
    public DodajBlokiranogUmjetnikaOdgovor handle(DodajBlokiranogUmjetnikaZahtjev zahtjev) {
        return new DodajBlokiranogUmjetnikaOdgovor(Arrays.asList(Podaci.KORISNIK_1, Podaci.KORISNIK_2));
    }
}
