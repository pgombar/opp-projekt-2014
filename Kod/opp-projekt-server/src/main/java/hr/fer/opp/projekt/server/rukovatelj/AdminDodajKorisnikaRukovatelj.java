package hr.fer.opp.projekt.server.rukovatelj;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.AdminDodajKorisnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.AdminDodajKorisnikaZahtjev;
import org.springframework.stereotype.Component;

@Component
public final class AdminDodajKorisnikaRukovatelj implements RukovateljZahtjevom<AdminDodajKorisnikaZahtjev, AdminDodajKorisnikaOdgovor> {
    @Override
    public AdminDodajKorisnikaOdgovor handle(AdminDodajKorisnikaZahtjev zahtjev, ConnectionToClient client,
                                             Korisnik active) {
        Korisnik korisnik = new Korisnik("", "", zahtjev.getKorisnickoIme(),
                zahtjev.getZaporka(), zahtjev.getEmail(), "", "", "", "", null, null, null, null, false);

        return new AdminDodajKorisnikaOdgovor(korisnik);
    }
}
