package hr.fer.opp.projekt.server.rukovatelj;

import com.google.common.collect.Lists;
import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Podgrana;
import hr.fer.opp.projekt.common.odgovor.DohvatiSifrarnikeOdgovor;
import hr.fer.opp.projekt.common.zahtjev.DohvatiSifrarnikeZahtjev;
import hr.fer.opp.projekt.server.repository.GranaRepository;
import hr.fer.opp.projekt.server.repository.PodgranaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class DohvatiSifrarnikeRukovatelj implements RukovateljZahtjevom<DohvatiSifrarnikeZahtjev, DohvatiSifrarnikeOdgovor> {
    private final GranaRepository granaRepository;

    private final PodgranaRepository podgranaRepository;

    @Autowired
    public DohvatiSifrarnikeRukovatelj(GranaRepository granaRepository, PodgranaRepository podgranaRepository) {
        this.granaRepository = granaRepository;
        this.podgranaRepository = podgranaRepository;
    }

    @Override
    public DohvatiSifrarnikeOdgovor handle(DohvatiSifrarnikeZahtjev zahtjev, ConnectionToClient client, Korisnik active) {
        List<Grana> grane = Lists.newArrayList(granaRepository.findAll());
        List<Podgrana> podgrane = Lists.newArrayList(podgranaRepository.findAll());

        return new DohvatiSifrarnikeOdgovor(grane, podgrane);
    }
}
