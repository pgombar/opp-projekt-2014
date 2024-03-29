package hr.fer.opp.projekt.server;

import hr.fer.opp.projekt.common.model.Grana;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Podgrana;
import hr.fer.opp.projekt.common.model.Umjetnina;
import hr.fer.opp.projekt.server.repository.GranaRepository;
import hr.fer.opp.projekt.server.repository.KorisnikRepository;
import hr.fer.opp.projekt.server.repository.PodgranaRepository;
import hr.fer.opp.projekt.server.repository.UmjetninaRepository;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InicijalizacijaBaze {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final KorisnikRepository korisnikRepository;

    private final GranaRepository granaRepository;

    private final PodgranaRepository podgranaRepository;

    private final UmjetninaRepository umjetninaRepository;


    @Autowired
    public InicijalizacijaBaze(KorisnikRepository korisnikRepository, GranaRepository granaRepository, PodgranaRepository podgranaRepository,
    		UmjetninaRepository umjetninaRepository) {
        this.korisnikRepository = korisnikRepository;
        this.granaRepository = granaRepository;
        this.podgranaRepository = podgranaRepository;
        this.umjetninaRepository = umjetninaRepository;
    }

    @PostConstruct
    @Transactional
    public void run() {
        if (isEmpty()) {
            addGrane();
            addPodgrane();
            addKorisnike();
            addUmjetnine();
        }
    }

    private boolean isEmpty() {
        return this.granaRepository.count() == 0;
    }

    private void addGrane() {
        granaRepository.save(new Grana("Slikarstvo"));
        granaRepository.save(new Grana("Kiparstvo"));
        granaRepository.save(new Grana("Grafika"));
    }

    private void addPodgrane() {
    	
        podgranaRepository.save(new Podgrana(grana(1), "Ulje na platnu"));
        podgranaRepository.save(new Podgrana(grana(1), "Akvarel"));
        podgranaRepository.save(new Podgrana(grana(1), "Gvaš"));
        podgranaRepository.save(new Podgrana(grana(1), "Tempera"));
        
        podgranaRepository.save(new Podgrana(grana(2), "Glina"));
        podgranaRepository.save(new Podgrana(grana(2), "Drvo"));
        podgranaRepository.save(new Podgrana(grana(2), "Kamen"));
        podgranaRepository.save(new Podgrana(grana(2), "Mramor"));
        
        podgranaRepository.save(new Podgrana(grana(3), "Drvorez"));
        podgranaRepository.save(new Podgrana(grana(3), "Linorez"));
        podgranaRepository.save(new Podgrana(grana(3), "Bakropis"));
        podgranaRepository.save(new Podgrana(grana(3), "Litografija"));
     
    }

    private void addKorisnike() {
    	
    	korisnikRepository.save(new Korisnik("Ankica", "Ancic", "aancic", "ankica", "ankica@gmail.com", "091232827",
                "Pod mostom 12", "Kaj buljis?", "Soboslikarica", grana(2), podgrana(5),
                new ArrayList<Umjetnina>(), slika("profpic/doge.jpg"), true));
    	
        korisnikRepository.save(new Korisnik("Mirko", "Broz", "mirko", "mirkec", "mirko@gmail.com", "091472827",
                "Ilica 10", "Hvala ti, Slavko! Spasio si mi život!", "Kurir", grana(2), podgrana(5),
                new ArrayList<Umjetnina>(), slika("profpic/Mirko_i_Slavko.jpg"), true));

        korisnikRepository.save(new Korisnik("Slavko", "Broz", "slavko", "slavkec", "slavko@gmail.com", "091472828",
                "Ilica 10", "Mirko, pazi metak!", "Kurir", grana(2), podgrana(6),
                new ArrayList<Umjetnina>(), slika("profpic/Mirko_i_Slavko.jpg"), true));

        korisnikRepository.save(new Korisnik("Ivica", "Rob", "ivo", "pivo", "ivek@gmail.com", "091472829",
                "Kod bakice", "", "Student elektrotehnike", grana(1), podgrana(1),
                new ArrayList<Umjetnina>(), null, false));
        
        korisnikRepository.save(new Korisnik("Marica", "Rob", "mara", "pivo", "marek@gmail.com", "091472830",
                "Kod bakice", "", "Doktor elektrotehnike", grana(1), podgrana(2),
                new ArrayList<Umjetnina>(), null, false));
        
        korisnikRepository.save(new Korisnik("Matija", "Folnegović", "folnz", "zzor", "fol@gmail.com", "091472831",
                "Brdo 123", "", "Javanturist", grana(1), podgrana(3),
                new ArrayList<Umjetnina>(), null, false));
        
        korisnikRepository.save(new Korisnik("Kolin", "NE", "da", "ne", "mail@gmail.com", "091472832",
                "Pod lipom 34", "", "", grana(1), podgrana(3),
                new ArrayList<Umjetnina>(), null, false));
        
        korisnikRepository.save(new Korisnik("Zdrav", "Coigne", "igre", "rijecima", "zrs@gmail.com", "091472833",
                "Pod lipom 35", "Kako je Antun izgubio Razum?", "Facebook admin", grana(3), podgrana(6),
                new ArrayList<Umjetnina>(), slika("profpic/zdravcoigne.jpg"), false));

        korisnikRepository.save(new Korisnik("Vladimir", "Nadzor", "big", "brother", "vladek@gmail.com", "091472834",
                "Ulica pjesnika", "", "Zaštitar", grana(2), podgrana(7),
                new ArrayList<Umjetnina>(), slika("profpic/nadzornik.jpg"), false));

        korisnikRepository.save(new Korisnik("Selma", "Bobić", "hrvatske", "željeznice", "selma@gmail.com", "091472835",
                "", "Ne naginjem se kroz prozor", "Studentica", grana(2), podgrana(6),
                new ArrayList<Umjetnina>(), null, false));
        
        korisnikRepository.save(new Korisnik("Djelo", "Hadziselimovic", "dobar", "odabir", "hrt@gmail.com", "091472836",
                "", "Nije Larin, vec je moj izbor!", "pokemon trener", grana(1), podgrana(3),
                new ArrayList<Umjetnina>(), null, false));

        korisnikRepository.save(new Korisnik("Eden", "Hazard", "najsamcija", "natusve", "chelsea@murinjo.com", "091472837",
                "Shed End", "", "zadnji vezni", grana(1), podgrana(2),
                new ArrayList<Umjetnina>(), slika("profpic/hazard.jpg"), false));

        korisnikRepository.save(new Korisnik("Josip", "Jelacic", "Hrvatskate?", "zovezove", "ustanak@gmail.com", "091472838",
                "Trg Republike 1", "Banana?", "Ban", grana(2), podgrana(6),
                new ArrayList<Umjetnina>(), null, false));

        korisnikRepository.save(new Korisnik("Brody", "Quint", "morski", "password", "ocean@gmail.com", "091472839",
                "Otok Hvar", "Trebal bu nam veci brod!", "Capo", grana(3), podgrana(9),
                new ArrayList<Umjetnina>(), slika("profpic/jaws.jpg"), false));

        korisnikRepository.save(new Korisnik("Slaven", "Belupo", "sbelupo", "belupos", "sb@gmail.com", "091472840",
                "Koprivnica 13", "", "Umjetnik", grana(3), podgrana(10),
                new ArrayList<Umjetnina>(), null, false));

        korisnikRepository.save(new Korisnik("Gregor", "Samsa", "krkhhhrk", "hhrkrhhr", "soba@kuca.com", "091472841",
                "", "krhhrkrhkhrhr rkhrkhrkrhkrh", "Trgovacki putnik", grana(3), podgrana(11),
                new ArrayList<Umjetnina>(), slika("profpic/gregor.jpg"), false));

        korisnikRepository.save(new Korisnik("Majka", "Hrabrost", "njezina", "djeca", "profit@supersport.com", "091472842",
                "-", "", "Ratni profiter", grana(3), podgrana(12),
                new ArrayList<Umjetnina>(), null, false));

        korisnikRepository.save(new Korisnik("Dean", "Kotiga", "najdrazaknjiga", "lovacuzitu", "lov@gmail.com", "091472843",
                "Pod lipom 12", "gotcha", "Lovac", grana(3), podgrana(6),
                new ArrayList<Umjetnina>(), slika("profpic/kotiga.jpeg"), false));

        korisnikRepository.save(new Korisnik("Imenko", "Prezimenko", "korisninckoime", "korisnickipassword", "imeprez@gmail.com", "091472844",
                "Ulica, Grad, Broj", "", "Slikar", grana(1), podgrana(1),
                new ArrayList<Umjetnina>(), null, false));

        korisnikRepository.save(new Korisnik("Goran", "Karan", "samo", "jako", "gk@gmail.com", "091472845",
                "Pod lipom 32", "", "Graficar", grana(4), podgrana(12),
                new ArrayList<Umjetnina>(), null, false));

        korisnikRepository.save(new Korisnik("Miljenko", "Kokot", "evoti", "glas", "hrt@gmail.com", "091472846",
                "Pod lipom 37", "", "Borac pjetlovima", grana(2), podgrana(7),
                new ArrayList<Umjetnina>(), null, false));

        korisnikRepository.save(new Korisnik("RTL", "Dva", "moja", "televizija", "rtl2@rtl.com", "091472847",
                "Pod lipom 39", "", "Kipar", grana(2), podgrana(8),
                new ArrayList<Umjetnina>(), null, false));

        korisnikRepository.save(new Korisnik("Marina", "Hrvatska", "skrivam", "suze", "mar@gmail.com", "091472848",
                "Iza vrata", "", "Kiparica", grana(2), podgrana(6),
                new ArrayList<Umjetnina>(), null, false));

        korisnikRepository.save(new Korisnik("Eleanor", "Rigby", "pobirem", "rizu", "crkva@gmail.com", "091472849",
                "Pod lipom 40", "All the lonely people...", "dr. sc.", grana(4), podgrana(9),
                new ArrayList<Umjetnina>(), null, false));

        korisnikRepository.save(new Korisnik("Rubin", "Carter", "nickname", "hurricane", "rhurc@gmail.com", "091472850",
                "Pod lipom 41", "", "Boksač", grana(3), podgrana(10),
                new ArrayList<Umjetnina>(), slika("profpic/hurrican.jpg"), false));

        korisnikRepository.save(new Korisnik("Neil", "Armstrong", "prvi", "covjek", "nasa@gmail.com", "091472851",
                "Mjesec", "valiki, mali korak", "Astronaut", grana(3), podgrana(12),
                new ArrayList<Umjetnina>(), null, false));

        korisnikRepository.save(new Korisnik("Mislav", "Bago", "mbago", "bago", "jmbago@gmail.com", "091472852",
                "Pod lipom 12", "", "", grana(3), podgrana(11),
                new ArrayList<Umjetnina>(), null, false));

        korisnikRepository.save(new Korisnik("Profesor", "Baltazar", "balt", "balt", "zar@gmail.com", "091472853",
                "Pod lipom 351", "", "Ke(ram)micar", grana(1), podgrana(1),
                new ArrayList<Umjetnina>(), null, false));

        korisnikRepository.save(new Korisnik("Dominik", "Spomenik", "domine", "domine", "muzej@gmail.com", "091472854",
                "Pod lipom 352", "Imam super ideju!", "Nocni cuvar", grana(3), podgrana(9),
                new ArrayList<Umjetnina>(), slika("profpic/spomenik.jpg"), false));

        korisnikRepository.save(new Korisnik("Vatroslav", "pl. Zajc", "tramvajska", "stanica", "htz@gmail.com", "091472855",
                "Pod lipom 354", "", "", grana(2), podgrana(6),
                new ArrayList<Umjetnina>(), null, false));

        korisnikRepository.save(new Korisnik("Božo", "Težak", "vladam", "kemijom", "zrs@gmail.com", "091472833",
                "Pod lipom 35", "Maštam da ideje!", "Programer", grana(1), podgrana(4),
                new ArrayList<Umjetnina>(), null, false));
        
    }
    
    private void addUmjetnine() {
    	umjetninaRepository.save(new Umjetnina("slika", "Slikarstvo", new Date(), slika("artwork/slika1.jpeg"), korisnik(5)));
    	umjetninaRepository.save(new Umjetnina("slika", "Slikarstvo", new Date(), slika("artwork/slika2.jpg"), korisnik(5)));
    	umjetninaRepository.save(new Umjetnina("slika", "Slikarstvo", new Date(), slika("artwork/slika3.jpg"), korisnik(4)));
    	umjetninaRepository.save(new Umjetnina("slika", "Slikarstvo", new Date(), slika("artwork/slika4.jpg"), korisnik(11)));
    	umjetninaRepository.save(new Umjetnina("slika", "Slikarstvo", new Date(), slika("artwork/slika5.jpg"), korisnik(11)));
    	umjetninaRepository.save(new Umjetnina("slika", "Slikarstvo", new Date(), slika("artwork/slika6.jpg"), korisnik(4)));
    	umjetninaRepository.save(new Umjetnina("kip", "Kiparstvo", new Date(), slika("artwork/kip1.jpg"), korisnik(1)));
    	umjetninaRepository.save(new Umjetnina("kip", "Kiparstvo", new Date(), slika("artwork/kip2.jpg"), korisnik(2)));
    	umjetninaRepository.save(new Umjetnina("kip", "Kiparstvo", new Date(), slika("artwork/kip3.jpg"), korisnik(2)));
    	umjetninaRepository.save(new Umjetnina("kip", "Kiparstvo", new Date(), slika("artwork/kip4.jpg"), korisnik(3)));
    	umjetninaRepository.save(new Umjetnina("kip", "Kiparstvo", new Date(), slika("artwork/kip5.jpg"), korisnik(3)));
    	umjetninaRepository.save(new Umjetnina("kip", "Kiparstvo", new Date(), slika("artwork/kip6.jpg"), korisnik(3)));
    	umjetninaRepository.save(new Umjetnina("linorez", "Grafika", new Date(), slika("artwork/linorez1.jpg"), korisnik(14)));
    	umjetninaRepository.save(new Umjetnina("linorez", "Grafika", new Date(), slika("artwork/linorez2.jpg"), korisnik(13)));
    	umjetninaRepository.save(new Umjetnina("linorez", "Grafika", new Date(), slika("artwork/linorez3.jpg"), korisnik(14)));
    	umjetninaRepository.save(new Umjetnina("linorez", "Grafika", new Date(), slika("artwork/linorez4.jpeg"), korisnik(14)));
    	umjetninaRepository.save(new Umjetnina("linorez", "Grafika", new Date(), slika("artwork/linorez5.jpeg"), korisnik(8)));
   }
    
    private Korisnik korisnik(long id) {
    	return korisnikRepository.findOne(id);
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
            LOGGER.warn("Slika " + ime + " ne postoji.", ime);
            return null;
        }
    }
}
