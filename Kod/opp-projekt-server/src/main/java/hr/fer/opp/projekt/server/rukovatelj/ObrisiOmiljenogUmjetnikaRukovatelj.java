package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.odgovor.ObrisiOmiljenogUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.ObrisiOmiljenogUmjetnikaZahtjev;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public final class ObrisiOmiljenogUmjetnikaRukovatelj implements RukovateljZahtjevom<ObrisiOmiljenogUmjetnikaZahtjev, ObrisiOmiljenogUmjetnikaOdgovor> {
    @Override
    public ObrisiOmiljenogUmjetnikaOdgovor handle(ObrisiOmiljenogUmjetnikaZahtjev zahtjev) {
        return new ObrisiOmiljenogUmjetnikaOdgovor(Arrays.asList(Podaci.KORISNIK_1, Podaci.KORISNIK_2));
    }
}
