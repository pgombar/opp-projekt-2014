package hr.fer.opp.projekt.server.rukovatelj;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.ObrisiBlokiranogUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.ObrisiBlokiranogUmjetnikaZahtjev;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public final class ObrisiBlokiranogUmjetnikaRukovatelj implements RukovateljZahtjevom<ObrisiBlokiranogUmjetnikaZahtjev, ObrisiBlokiranogUmjetnikaOdgovor> {
    @Override
    public ObrisiBlokiranogUmjetnikaOdgovor handle(ObrisiBlokiranogUmjetnikaZahtjev zahtjev, ConnectionToClient client,
                                                   Korisnik active) {
        return new ObrisiBlokiranogUmjetnikaOdgovor(Arrays.asList(Podaci.KORISNIK_1, Podaci.KORISNIK_2));
    }
}
