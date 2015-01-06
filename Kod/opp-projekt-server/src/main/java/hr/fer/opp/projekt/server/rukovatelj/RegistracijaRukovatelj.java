package hr.fer.opp.projekt.server.rukovatelj;

import java.util.ArrayList;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Podgrana;
import hr.fer.opp.projekt.common.model.Umjetnina;
import hr.fer.opp.projekt.common.odgovor.RegistracijaOdgovor;
import hr.fer.opp.projekt.common.zahtjev.RegistracijaZahtjev;

import hr.fer.opp.projekt.server.repository.GranaRepository;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import hr.fer.opp.projekt.server.repository.PodgranaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class RegistracijaRukovatelj implements RukovateljZahtjevom<RegistracijaZahtjev, RegistracijaOdgovor> {
    private final GranaRepository granaRepository;

    private final KorisnikRepository korisnikRepository;

    private final PodgranaRepository podgranaRepository;

    @Autowired
    public RegistracijaRukovatelj(GranaRepository granaRepository, KorisnikRepository korisnikRepository, PodgranaRepository podgranaRepository) {
        this.granaRepository = granaRepository;
        this.korisnikRepository = korisnikRepository;
        this.podgranaRepository = podgranaRepository;
    }

    @Override
    public RegistracijaOdgovor handle(RegistracijaZahtjev zahtjev, ConnectionToClient client, Korisnik active) {
        Grana grana = granaRepository.findOne(zahtjev.getGrana());
        Podgrana podgrana = podgranaRepository.findOne(zahtjev.getPodgrana());

        Korisnik korisnik = new Korisnik(zahtjev.getIme(), zahtjev.getPrezime(), zahtjev.getKorisnickoIme(),
                zahtjev.getZaporka(), zahtjev.getEmail(), zahtjev.getTelefon(), zahtjev.getAdresa(), "",
                zahtjev.getZvanje(), grana, podgrana, new ArrayList<Umjetnina>(), null, false);

        Korisnik saved = korisnikRepository.save(korisnik);

        return new RegistracijaOdgovor(saved);
    }
}
