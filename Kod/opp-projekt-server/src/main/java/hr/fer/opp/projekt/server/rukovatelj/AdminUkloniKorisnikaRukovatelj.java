package hr.fer.opp.projekt.server.rukovatelj;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.AdminUkloniKorisnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.AdminUkloniKorisnikaZahtjev;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class AdminUkloniKorisnikaRukovatelj implements RukovateljZahtjevom<AdminUkloniKorisnikaZahtjev, AdminUkloniKorisnikaOdgovor> {
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public AdminUkloniKorisnikaRukovatelj(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public AdminUkloniKorisnikaOdgovor handle(AdminUkloniKorisnikaZahtjev zahtjev, ConnectionToClient client,
                                              Korisnik active) {
        Korisnik korisnik = korisnikRepository.findOne(zahtjev.getId());

        if (korisnik == null) {
            return AdminUkloniKorisnikaOdgovor.NEUSPJEH;
        } else {
            korisnikRepository.delete(korisnik);
            return AdminUkloniKorisnikaOdgovor.USPJEH;
        }
    }
}
