package hr.fer.opp.projekt.server.rukovatelj;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.DodajBlokiranogUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.DodajBlokiranogUmjetnikaZahtjev;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public final class DodajBlokiranogUmjetnikaRukovatelj implements RukovateljZahtjevom<DodajBlokiranogUmjetnikaZahtjev, DodajBlokiranogUmjetnikaOdgovor> {
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public DodajBlokiranogUmjetnikaRukovatelj(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public DodajBlokiranogUmjetnikaOdgovor handle(DodajBlokiranogUmjetnikaZahtjev zahtjev, ConnectionToClient client,
                                                  Korisnik active) {
        Korisnik umjetnik = korisnikRepository.findOne(zahtjev.getUmjetnik());
        Korisnik blokiraniUmjetnik = korisnikRepository.findOne(zahtjev.getBlokiraniUmjetnik());

        umjetnik.getBlokiraniUmjetnici().add(blokiraniUmjetnik);
        Korisnik saved = korisnikRepository.save(umjetnik);

        return new DodajBlokiranogUmjetnikaOdgovor(saved.getBlokiraniUmjetnici());
    }
}
