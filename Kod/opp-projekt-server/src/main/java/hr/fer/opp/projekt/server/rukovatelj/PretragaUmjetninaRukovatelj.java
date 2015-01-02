package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.odgovor.PretragaUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.PretragaUmjetnikaZahtjev;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public final class PretragaUmjetninaRukovatelj implements RukovateljZahtjevom<PretragaUmjetnikaZahtjev, PretragaUmjetnikaOdgovor> {
    @Override
    public PretragaUmjetnikaOdgovor handle(PretragaUmjetnikaZahtjev zahtjev) {
        return new PretragaUmjetnikaOdgovor(Arrays.asList(Podaci.KORISNIK_1, Podaci.KORISNIK_2));
    }
}
