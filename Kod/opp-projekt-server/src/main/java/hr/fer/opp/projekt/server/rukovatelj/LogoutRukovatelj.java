package hr.fer.opp.projekt.server.rukovatelj;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.LogoutOdgovor;
import hr.fer.opp.projekt.common.zahtjev.LogoutZahtjev;
import hr.fer.opp.projekt.server.communication.EventServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class LogoutRukovatelj implements RukovateljZahtjevom<LogoutZahtjev,LogoutOdgovor> {
    private final EventServer eventServer;

    @Autowired
    public LogoutRukovatelj(EventServer eventServer) {
        this.eventServer = eventServer;
    }

    @Override
    public LogoutOdgovor handle(LogoutZahtjev zahtjev, ConnectionToClient client, Korisnik active) {
        eventServer.removeKorisnik(client);

        return LogoutOdgovor.INSTANCE;
    }
}
