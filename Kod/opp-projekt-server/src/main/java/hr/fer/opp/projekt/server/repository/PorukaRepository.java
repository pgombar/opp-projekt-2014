package hr.fer.opp.projekt.server.repository;

import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Poruka;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface PorukaRepository extends CrudRepository<Poruka, Long> {
	@Query("select p from Poruka p where p.korisnikDo = :korisnik and p.id > :id")
    List<Poruka> getPoruke(@Param("korisnik") Korisnik korisnik, @Param("id") long id);

	@Modifying
	@Query("delete from Poruka p where p.korisnikDo = :korisnik")
    void clearPoruke(@Param("korisnik") Korisnik korisnik);
}
