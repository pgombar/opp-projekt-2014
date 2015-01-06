package hr.fer.opp.projekt.server.rukovatelj;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.odgovor.UkrcajFotografijuUmjetnineOdgovor;
import hr.fer.opp.projekt.common.zahtjev.UkrcajFotografijuUmjetnineZahtjev;
import org.springframework.stereotype.Component;

@Component
public final class UkrcajFotografijuUmjetnineRukovatelj implements RukovateljZahtjevom<UkrcajFotografijuUmjetnineZahtjev, UkrcajFotografijuUmjetnineOdgovor> {
    @Override
    public UkrcajFotografijuUmjetnineOdgovor handle(UkrcajFotografijuUmjetnineZahtjev zahtjev,
                                                    ConnectionToClient client, Korisnik active) {
        return new UkrcajFotografijuUmjetnineOdgovor(Podaci.UMJETNINA_1);
    }
}
