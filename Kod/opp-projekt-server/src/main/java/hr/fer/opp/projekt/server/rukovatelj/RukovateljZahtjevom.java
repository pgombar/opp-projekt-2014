package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.odgovor.Odgovor;
import hr.fer.opp.projekt.common.zahtjev.Zahtjev;

public interface RukovateljZahtjevom<T extends Zahtjev, S extends Odgovor> {
    S handle(T zahtjev);
}
