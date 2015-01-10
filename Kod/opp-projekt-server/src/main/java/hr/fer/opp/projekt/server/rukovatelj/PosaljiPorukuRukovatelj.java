package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Poruka;
import hr.fer.opp.projekt.common.odgovor.PosaljiPorukuOdgovor;
import hr.fer.opp.projekt.common.zahtjev.PosaljiPorukuZahtjev;
import hr.fer.opp.projekt.server.repository.PorukaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lloseng.ocsf.server.ConnectionToClient;

@Component
public final class PosaljiPorukuRukovatelj implements RukovateljZahtjevom<PosaljiPorukuZahtjev, PosaljiPorukuOdgovor> {
    private final PorukaRepository porukaRepository;

    @Autowired
    public PosaljiPorukuRukovatelj(PorukaRepository porukaRepository) {
        this.porukaRepository = porukaRepository;
    }

    @Override
    public PosaljiPorukuOdgovor handle(PosaljiPorukuZahtjev zahtjev, ConnectionToClient client, Korisnik active) {
    	Poruka poruka = new Poruka(zahtjev.getPoruka(), active, zahtjev.getKorisnik());
    	porukaRepository.save(poruka);
    	return PosaljiPorukuOdgovor.INSTANCE;
    }
}
