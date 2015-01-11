package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.UkloniPorukeKorisnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.UkloniPorukeKorisnikaZahtjev;
import hr.fer.opp.projekt.server.repository.PorukaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lloseng.ocsf.server.ConnectionToClient;

@Component
public final class UkloniPorukeKorisnikaRukovatelj implements RukovateljZahtjevom<UkloniPorukeKorisnikaZahtjev, UkloniPorukeKorisnikaOdgovor> {
    private final PorukaRepository porukaRepository;

    @Autowired
    public UkloniPorukeKorisnikaRukovatelj(PorukaRepository porukaRepository) {
        this.porukaRepository = porukaRepository;
    }

    @Override
    public UkloniPorukeKorisnikaOdgovor handle(UkloniPorukeKorisnikaZahtjev zahtjev, ConnectionToClient client, Korisnik active) {
    	porukaRepository.clearPoruke(zahtjev.getKorisnik());
    	return UkloniPorukeKorisnikaOdgovor.INSTANCE;
    }
}
