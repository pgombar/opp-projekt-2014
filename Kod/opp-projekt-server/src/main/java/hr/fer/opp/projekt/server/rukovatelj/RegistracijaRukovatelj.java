package hr.fer.opp.projekt.server.rukovatelj;

import java.util.ArrayList;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Umjetnina;
import hr.fer.opp.projekt.common.odgovor.RegistracijaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.RegistracijaZahtjev;

import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class RegistracijaRukovatelj implements RukovateljZahtjevom<RegistracijaZahtjev, RegistracijaOdgovor> {
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public RegistracijaRukovatelj(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public RegistracijaOdgovor handle(RegistracijaZahtjev zahtjev, ConnectionToClient client, Korisnik active) {
        Korisnik korisnik = new Korisnik(zahtjev.getIme(), zahtjev.getPrezime(), zahtjev.getKorisnickoIme(),
                zahtjev.getZaporka(), zahtjev.getEmail(), zahtjev.getTelefon(), zahtjev.getAdresa(), "",
                zahtjev.getZvanje(), null, null, null, null, false);

        Korisnik saved = korisnikRepository.save(korisnik);

        return new RegistracijaOdgovor(saved);
    }
}
