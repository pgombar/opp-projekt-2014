package hr.fer.opp.projekt.server.rukovatelj;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.ObrisiBlokiranogUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.odgovor.ObrisiOmiljenogUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.ObrisiBlokiranogUmjetnikaZahtjev;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public final class ObrisiBlokiranogUmjetnikaRukovatelj implements RukovateljZahtjevom<ObrisiBlokiranogUmjetnikaZahtjev, ObrisiBlokiranogUmjetnikaOdgovor> {
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public ObrisiBlokiranogUmjetnikaRukovatelj(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public ObrisiBlokiranogUmjetnikaOdgovor handle(ObrisiBlokiranogUmjetnikaZahtjev zahtjev, ConnectionToClient client,
                                                   Korisnik active) {
        Korisnik umjetnik = korisnikRepository.findOne(zahtjev.getUmjetnik());
        Korisnik blokiraniUmjetnik = korisnikRepository.findOne(zahtjev.getBlokiraniUmjetnik());

        umjetnik.getBlokiraniUmjetnici().remove(blokiraniUmjetnik);
        Korisnik saved = korisnikRepository.save(umjetnik);

        return new ObrisiBlokiranogUmjetnikaOdgovor(saved.getBlokiraniUmjetnici());
    }
}
