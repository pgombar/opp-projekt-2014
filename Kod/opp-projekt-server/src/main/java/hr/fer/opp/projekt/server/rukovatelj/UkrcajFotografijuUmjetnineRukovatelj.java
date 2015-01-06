package hr.fer.opp.projekt.server.rukovatelj;

import com.lloseng.ocsf.server.ConnectionToClient;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Umjetnina;
import hr.fer.opp.projekt.common.odgovor.UkrcajFotografijuUmjetnineOdgovor;
import hr.fer.opp.projekt.common.util.ImageUtil;
import hr.fer.opp.projekt.common.zahtjev.UkrcajFotografijuUmjetnineZahtjev;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import hr.fer.opp.projekt.server.repository.UmjetninaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@Component
public final class UkrcajFotografijuUmjetnineRukovatelj implements RukovateljZahtjevom<UkrcajFotografijuUmjetnineZahtjev, UkrcajFotografijuUmjetnineOdgovor> {
    private final UmjetninaRepository umjetninaRepository;

    @Autowired
    public UkrcajFotografijuUmjetnineRukovatelj(UmjetninaRepository umjetninaRepository) {
        this.umjetninaRepository = umjetninaRepository;
    }


    @Override
    public UkrcajFotografijuUmjetnineOdgovor handle(UkrcajFotografijuUmjetnineZahtjev zahtjev,
                                                    ConnectionToClient client, Korisnik active) {
        BufferedImage slika = ImageUtil.byteArrayToImage(zahtjev.getSlika());
        Umjetnina umjetnina = new Umjetnina(zahtjev.getNaziv(), zahtjev.getTehnika(), zahtjev.getDatumNastanka(),
                slika, active);

        Umjetnina saved = umjetninaRepository.save(umjetnina);

        return new UkrcajFotografijuUmjetnineOdgovor(saved);
    }
}
