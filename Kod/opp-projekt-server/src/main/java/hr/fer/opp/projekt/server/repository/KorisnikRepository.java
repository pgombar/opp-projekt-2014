package hr.fer.opp.projekt.server.repository;

import hr.fer.opp.projekt.common.model.Korisnik;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
public interface KorisnikRepository extends CrudRepository<Korisnik, Long> {
    @Query("select u from Korisnik u where lower(u.korisnickoIme) like :korisnickoIme or lower(u.ime) like :ime or " +
            "lower(u.prezime) like :prezime")
    List<Korisnik> search(@Param("korisnickoIme") String korisnickoIme, @Param("ime") String ime,
                          @Param("prezime") String prezime);
    
    Korisnik findByKorisnickoIme(@Param("korisnickoIme") String korisnickoIme);

    @Modifying
    @Query("update Korisnik u set u.zadnjiPutAktivan = :zadnjiPutAktivan, online = TRUE where u.id = :id")
    void setZadnjiPutAktivanFor(@Param("id") long id, @Param("zadnjiPutAktivan") Date zadnjiPutAktivan);

    @Modifying
    @Query("update Korisnik u set u.online = False where u.id = :id")
    void setOnlineToFalseFor(@Param("id") long id);
}
