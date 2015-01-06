package hr.fer.opp.projekt.server;

import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Podgrana;
import hr.fer.opp.projekt.common.model.Umjetnina;
import hr.fer.opp.projekt.server.repository.GranaRepository;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import hr.fer.opp.projekt.server.repository.PodgranaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

@Component
public class InicijalizacijaBaze {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final KorisnikRepository korisnikRepository;

    private final GranaRepository granaRepository;

    private final PodgranaRepository podgranaRepository;

    @Autowired
    public InicijalizacijaBaze(KorisnikRepository korisnikRepository, GranaRepository granaRepository, PodgranaRepository podgranaRepository) {
        this.korisnikRepository = korisnikRepository;
        this.granaRepository = granaRepository;
        this.podgranaRepository = podgranaRepository;
    }

    @PostConstruct
    @Transactional
    public void run() {
        if (isEmpty()) {
            addGrane();
            addPodgrane();
            addKorisnike();
        }
    }

    private boolean isEmpty() {
        return this.granaRepository.count() == 0;
    }

    private void addGrane() {
        granaRepository.save(new Grana("Grana 1"));
        granaRepository.save(new Grana("Grana 2"));
    }

    private void addPodgrane() {
        podgranaRepository.save(new Podgrana(grana(1), "Podgrana 1"));
        podgranaRepository.save(new Podgrana(grana(2), "Podgrana 2"));
    }

    private void addKorisnike() {
        korisnikRepository.save(new Korisnik("Mirko", "Mirkovic", "mmirkovic", "mirkec", "mirko@gmail.com", "091472827",
                "Ilica 10", "Nisam zgodan al sam nezgodan", "soboslikar", grana(2), podgrana(1),
                new ArrayList<Umjetnina>(), slika("doge.jpg"), true));

        korisnikRepository.save(new Korisnik("Ankica", "Ancic", "aancic", "ankica", "ankica@gmail.com", "091472827",
                "Ilica 10", "Kaj buljis", "soboslikar", grana(1), podgrana(1), new ArrayList<Umjetnina>(),
                slika("doge.jpg"), true));
    }

    private Grana grana(long id) {
        return granaRepository.findOne(id);
    }

    private Podgrana podgrana(long id) {
        return podgranaRepository.findOne(id);
    }

    private BufferedImage slika(String ime) {
        InputStream resource = getClass().getClassLoader().getResourceAsStream(ime);

        try {
            return ImageIO.read(resource);
        } catch (IOException e) {
            LOGGER.warn("Slika {0} ne postoji.", ime);
            return null;
        }
    }
}
