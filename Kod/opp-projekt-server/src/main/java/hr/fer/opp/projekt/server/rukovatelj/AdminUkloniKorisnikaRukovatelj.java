package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.odgovor.AdminUkloniKorisnikaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.AdminUkloniKorisnikaZahtjev;
import org.springframework.stereotype.Component;

@Component
public final class AdminUkloniKorisnikaRukovatelj implements RukovateljZahtjevom<AdminUkloniKorisnikaZahtjev, AdminUkloniKorisnikaOdgovor> {
    @Override
    public AdminUkloniKorisnikaOdgovor handle(AdminUkloniKorisnikaZahtjev zahtjev) {
        return AdminUkloniKorisnikaOdgovor.USPJEH;
    }
}
