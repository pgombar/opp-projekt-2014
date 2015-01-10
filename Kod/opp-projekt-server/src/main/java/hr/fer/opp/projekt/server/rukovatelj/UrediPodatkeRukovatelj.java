package hr.fer.opp.projekt.server.rukovatelj;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.UrediPodatkeOdgovor;
import hr.fer.opp.projekt.common.zahtjev.UrediPodatkeZahtjev;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import hr.fer.opp.projekt.server.util.Validacija;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class UrediPodatkeRukovatelj implements RukovateljZahtjevom<UrediPodatkeZahtjev, UrediPodatkeOdgovor> {
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public UrediPodatkeRukovatelj(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public UrediPodatkeOdgovor handle(UrediPodatkeZahtjev zahtjev, ConnectionToClient client, Korisnik active) {
        Korisnik korisnikZahtjev = zahtjev.getUmjetnik();

        Validacija validacija = validiraj(korisnikZahtjev);
        if (validacija.imaGreske()) {
            return new UrediPodatkeOdgovor(validacija.getGreske());
        } else {
            Korisnik existing = korisnikRepository.findOne(korisnikZahtjev.getId());
            existing.merge(korisnikZahtjev);

            Korisnik saved = korisnikRepository.save(existing);

            return new UrediPodatkeOdgovor(saved);
        }
    }

    private Validacija validiraj(Korisnik zahtjev) {
        Validacija validacija = new Validacija();

        validacija.nijePrazan(zahtjev.getKorisnickoIme(), "Korisničko ime ne smije biti prazno.");
        validacija.nijePrazan(zahtjev.getZaporka(), "Zaporka ne smije biti prazna.");
        validacija.nijePrazan(zahtjev.getEmail(), "E-mail adresa ne smije biti prazna.");

        return validacija;
    }
}
