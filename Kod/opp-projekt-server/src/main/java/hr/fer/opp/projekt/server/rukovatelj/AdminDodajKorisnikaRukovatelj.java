package hr.fer.opp.projekt.server.rukovatelj;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.AdminDodajKorisnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.AdminDodajKorisnikaZahtjev;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import hr.fer.opp.projekt.server.util.Validacija;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class AdminDodajKorisnikaRukovatelj implements RukovateljZahtjevom<AdminDodajKorisnikaZahtjev, AdminDodajKorisnikaOdgovor> {
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public AdminDodajKorisnikaRukovatelj(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public AdminDodajKorisnikaOdgovor handle(AdminDodajKorisnikaZahtjev zahtjev, ConnectionToClient client,
                                             Korisnik active) {
        Validacija validacija = validiraj(zahtjev);
        if (validacija.imaGreske()) {
            return new AdminDodajKorisnikaOdgovor(validacija.getGreske());
        } else {
            Korisnik korisnik = new Korisnik("", "", zahtjev.getKorisnickoIme(),
                    zahtjev.getZaporka(), zahtjev.getEmail(), "", "", "", "", null, null, null, null, false);

            Korisnik saved = korisnikRepository.save(korisnik);

            return new AdminDodajKorisnikaOdgovor(saved);
        }
    }

    private Validacija validiraj(AdminDodajKorisnikaZahtjev zahtjev) {
        Validacija validacija = new Validacija();

        validacija.nijePrazan(zahtjev.getKorisnickoIme(), "Korisničko ime ne smije biti prazno.");
        validacija.nijePrazan(zahtjev.getZaporka(), "Zaporka ne smije biti prazna.");
        validacija.nijePrazan(zahtjev.getEmail(), "E-mail adresa ne smije biti prazna.");

        if (!validacija.imaGreske()) {
            Korisnik existing =korisnikRepository.findByKorisnickoIme(zahtjev.getKorisnickoIme());

            if (existing != null) {
                validacija.dodaj("Korisnik s korisničkim imenom %s već postoji.", zahtjev.getKorisnickoIme());
            }
        }

        return validacija;
    }
}
