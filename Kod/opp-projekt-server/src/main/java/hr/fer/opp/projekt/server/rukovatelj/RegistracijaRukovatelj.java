package hr.fer.opp.projekt.server.rukovatelj;

import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.RegistracijaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.RegistracijaZahtjev;
import org.springframework.stereotype.Component;

@Component
public final class RegistracijaRukovatelj implements RukovateljZahtjevom<RegistracijaZahtjev, RegistracijaOdgovor> {
    @Override
    public RegistracijaOdgovor handle(RegistracijaZahtjev zahtjev) {
        Korisnik korisnik = new Korisnik(-1, zahtjev.getIme(), zahtjev.getPrezime(), zahtjev.getKorisnickoIme(),
                zahtjev.getZaporka(), zahtjev.getEmail(), zahtjev.getTelefon(), "", "", false, false);

        return new RegistracijaOdgovor(korisnik);
    }
}
