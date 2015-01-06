package hr.fer.opp.projekt.server.rukovatelj;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.DodajBlokiranogUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.odgovor.ObrisiOmiljenogUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.ObrisiOmiljenogUmjetnikaZahtjev;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public final class ObrisiOmiljenogUmjetnikaRukovatelj implements RukovateljZahtjevom<ObrisiOmiljenogUmjetnikaZahtjev, ObrisiOmiljenogUmjetnikaOdgovor> {
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public ObrisiOmiljenogUmjetnikaRukovatelj(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public ObrisiOmiljenogUmjetnikaOdgovor handle(ObrisiOmiljenogUmjetnikaZahtjev zahtjev, ConnectionToClient client,
                                                  Korisnik active) {
        Korisnik umjetnik = korisnikRepository.findOne(zahtjev.getUmjetnik());
        Korisnik omiljeniUmjetnik = korisnikRepository.findOne(zahtjev.getOmiljeniUmjetnik());

        umjetnik.getOmiljeniUmjetnici().remove(omiljeniUmjetnik);
        Korisnik saved = korisnikRepository.save(umjetnik);

        return new ObrisiOmiljenogUmjetnikaOdgovor(saved.getOmiljeniUmjetnici());
    }
}
