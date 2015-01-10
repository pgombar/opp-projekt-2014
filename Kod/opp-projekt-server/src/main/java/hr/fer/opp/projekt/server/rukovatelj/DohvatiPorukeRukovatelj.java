package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.DohvatiPorukeOdgovor;
import hr.fer.opp.projekt.common.zahtjev.DohvatiPorukeZahtjev;
import hr.fer.opp.projekt.server.repository.PorukaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lloseng.ocsf.server.ConnectionToClient;

@Component
public final class DohvatiPorukeRukovatelj implements RukovateljZahtjevom<DohvatiPorukeZahtjev, DohvatiPorukeOdgovor> {
    private final PorukaRepository porukaRepository;

    @Autowired
    public DohvatiPorukeRukovatelj(PorukaRepository porukaRepository) {
        this.porukaRepository = porukaRepository;
    }

    @Override
    public DohvatiPorukeOdgovor handle(DohvatiPorukeZahtjev zahtjev, ConnectionToClient client, Korisnik active) {
        return new DohvatiPorukeOdgovor(porukaRepository.getPoruke(zahtjev.getKorisnik(), zahtjev.getIdZadnjePoruke()));
    }
}
