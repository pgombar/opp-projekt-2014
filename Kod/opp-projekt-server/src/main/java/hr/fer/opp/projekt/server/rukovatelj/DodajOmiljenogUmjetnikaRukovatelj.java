package hr.fer.opp.projekt.server.rukovatelj;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.DodajBlokiranogUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.odgovor.DodajOmiljenogUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.DodajOmiljenogUmjetnikaZahtjev;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public final class DodajOmiljenogUmjetnikaRukovatelj implements RukovateljZahtjevom<DodajOmiljenogUmjetnikaZahtjev, DodajOmiljenogUmjetnikaOdgovor> {
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public DodajOmiljenogUmjetnikaRukovatelj(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public DodajOmiljenogUmjetnikaOdgovor handle(DodajOmiljenogUmjetnikaZahtjev zahtjev, ConnectionToClient client,
                                                 Korisnik active) {
        Korisnik umjetnik = korisnikRepository.findOne(zahtjev.getUmjetnik());
        Korisnik omiljeniUmjetnici = korisnikRepository.findOne(zahtjev.getOmiljeniUmjetnik());

        umjetnik.getOmiljeniUmjetnici().add(omiljeniUmjetnici);
        Korisnik saved = korisnikRepository.save(umjetnik);

        return new DodajOmiljenogUmjetnikaOdgovor(saved.getOmiljeniUmjetnici());
    }
}
