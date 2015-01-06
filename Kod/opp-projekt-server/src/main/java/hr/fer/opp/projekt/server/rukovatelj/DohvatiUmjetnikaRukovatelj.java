package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.odgovor.DohvatiUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.DohvatiUmjetnikaZahtjev;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class DohvatiUmjetnikaRukovatelj implements RukovateljZahtjevom<DohvatiUmjetnikaZahtjev, DohvatiUmjetnikaOdgovor> {
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public DohvatiUmjetnikaRukovatelj(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public DohvatiUmjetnikaOdgovor handle(DohvatiUmjetnikaZahtjev zahtjev) {
        return new DohvatiUmjetnikaOdgovor(korisnikRepository.findOne(zahtjev.getId()));
    }
}
