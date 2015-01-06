package hr.fer.opp.projekt.server.rukovatelj;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.Odgovor;
import hr.fer.opp.projekt.common.zahtjev.Zahtjev;

import java.sql.Connection;

public interface RukovateljZahtjevom<T extends Zahtjev, S extends Odgovor> {
    S handle(T zahtjev, ConnectionToClient client, Korisnik active);
}
