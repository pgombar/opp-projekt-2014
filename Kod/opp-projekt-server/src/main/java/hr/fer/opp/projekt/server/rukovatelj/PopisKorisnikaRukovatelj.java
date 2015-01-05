package hr.fer.opp.projekt.server.rukovatelj;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.PopisUmjetnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.PopisUmjetnikaZahtjev;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class PopisKorisnikaRukovatelj implements RukovateljZahtjevom<PopisUmjetnikaZahtjev, PopisUmjetnikaOdgovor> {
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public PopisKorisnikaRukovatelj(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public PopisUmjetnikaOdgovor handle(PopisUmjetnikaZahtjev zahtjev) {
        List<Korisnik> korisnici = ImmutableList.copyOf(korisnikRepository.findAll());

        return new PopisUmjetnikaOdgovor(korisnici);
    }
}
