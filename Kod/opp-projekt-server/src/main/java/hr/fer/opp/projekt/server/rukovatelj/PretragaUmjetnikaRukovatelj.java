package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.PretragaUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.PretragaUmjetnikaZahtjev;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public final class PretragaUmjetnikaRukovatelj implements RukovateljZahtjevom<PretragaUmjetnikaZahtjev, PretragaUmjetnikaOdgovor> {
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public PretragaUmjetnikaRukovatelj(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public PretragaUmjetnikaOdgovor handle(PretragaUmjetnikaZahtjev zahtjev) {
        List<Korisnik> rezultati = korisnikRepository.pretrazi(zahtjev.getKorisnickoIme(), zahtjev.getIme(),
                zahtjev.getPrezime());

        return new PretragaUmjetnikaOdgovor(rezultati);
    }
}
