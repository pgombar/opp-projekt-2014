package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.AdminDodajKorisnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.AdminDodajKorisnikaZahtjev;
import org.springframework.stereotype.Component;

@Component
public final class AdminDodajKorisnikaRukovatelj implements RukovateljZahtjevom<AdminDodajKorisnikaZahtjev, AdminDodajKorisnikaOdgovor> {
    @Override
    public AdminDodajKorisnikaOdgovor handle(AdminDodajKorisnikaZahtjev zahtjev) {
        Korisnik korisnik = new Korisnik(-1, "", "", zahtjev.getKorisnickoIme(),
                zahtjev.getZaporka(), zahtjev.getEmail(), "", "", "", "", "", "", null, null, false, false);

        return new AdminDodajKorisnikaOdgovor(korisnik);
    }
}
