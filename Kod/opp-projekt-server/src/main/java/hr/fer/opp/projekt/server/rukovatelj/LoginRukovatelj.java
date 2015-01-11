package hr.fer.opp.projekt.server.rukovatelj;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.LoginOdgovor;
import hr.fer.opp.projekt.common.zahtjev.LoginZahtjev;
import hr.fer.opp.projekt.server.communication.EventServer;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginRukovatelj implements RukovateljZahtjevom<LoginZahtjev, LoginOdgovor> {
    private final EventServer eventServer;

    private final KorisnikRepository korisnikRepository;

    @Autowired
    public LoginRukovatelj(EventServer eventServer, KorisnikRepository korisnikRepository) {
        this.eventServer = eventServer;
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public LoginOdgovor handle(LoginZahtjev zahtjev, ConnectionToClient client, Korisnik active) {
        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(zahtjev.getKorisnickoIme());

        if (korisnik != null && korisnik.getZaporka().equals(zahtjev.getZaporka())) {
            if (eventServer.setKorisnik(client, korisnik)) {
                return LoginOdgovor.success(korisnik);
            } else {
                return LoginOdgovor.fail("Prekoračen maksimalni broj korisnika. Molimo, pokušajte kasnije.");
            }
        } else {
            return LoginOdgovor.fail("Pogrešno korisničko ime i/ili zaporka.");
        }
    }
}
