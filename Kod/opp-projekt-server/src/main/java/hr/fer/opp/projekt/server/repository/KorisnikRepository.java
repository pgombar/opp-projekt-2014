package hr.fer.opp.projekt.server.repository;

import hr.fer.opp.projekt.common.model.Korisnik;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KorisnikRepository extends CrudRepository<Korisnik, Long> {
    @Query("select u from Korisnik u where u.korisnickoIme like '%:korisnickoIme%' and " +
            "u.ime like '%:ime%' and u.prezime like '%:prezime%'")
    List<Korisnik> pretrazi(@Param("korisnickoIme") String korisnickoIme, @Param("ime") String ime,
                            @Param("prezime") String prezime);
}
