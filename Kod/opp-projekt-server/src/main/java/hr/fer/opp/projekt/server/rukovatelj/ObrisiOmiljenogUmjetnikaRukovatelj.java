package hr.fer.opp.projekt.server.rukovatelj;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.ObrisiOmiljenogUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.ObrisiOmiljenogUmjetnikaZahtjev;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public final class ObrisiOmiljenogUmjetnikaRukovatelj implements RukovateljZahtjevom<ObrisiOmiljenogUmjetnikaZahtjev, ObrisiOmiljenogUmjetnikaOdgovor> {
    @Override
    public ObrisiOmiljenogUmjetnikaOdgovor handle(ObrisiOmiljenogUmjetnikaZahtjev zahtjev, ConnectionToClient client,
                                                  Korisnik active) {
        return new ObrisiOmiljenogUmjetnikaOdgovor(Arrays.asList(Podaci.KORISNIK_1, Podaci.KORISNIK_2));
    }
}
