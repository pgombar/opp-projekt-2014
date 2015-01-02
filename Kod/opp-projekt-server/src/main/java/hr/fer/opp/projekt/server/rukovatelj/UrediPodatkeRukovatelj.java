package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.odgovor.UrediPodatkeOdgovor;
import hr.fer.opp.projekt.common.zahtjev.UrediPodatkeZahtjev;
import org.springframework.stereotype.Component;

@Component
public final class UrediPodatkeRukovatelj implements RukovateljZahtjevom<UrediPodatkeZahtjev, UrediPodatkeOdgovor> {
    @Override
    public UrediPodatkeOdgovor handle(UrediPodatkeZahtjev zahtjev) {
        return new UrediPodatkeOdgovor(zahtjev.getUmjetnik());
    }
}
