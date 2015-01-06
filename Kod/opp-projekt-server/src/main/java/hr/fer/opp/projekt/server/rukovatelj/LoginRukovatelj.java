package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.LoginOdgovor;
import hr.fer.opp.projekt.common.zahtjev.LoginZahtjev;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginRukovatelj implements RukovateljZahtjevom<LoginZahtjev, LoginOdgovor> {
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public LoginRukovatelj(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public LoginOdgovor handle(LoginZahtjev zahtjev) {
        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(zahtjev.getKorisnickoIme());

        if (korisnik != null && korisnik.getZaporka().equals(zahtjev.getZaporka())) {
            return LoginOdgovor.success(korisnik);
        } else {
            return LoginOdgovor.fail("Pogrešno korisničko ime i/ili zaporka.");
        }
    }
}
