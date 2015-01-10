package hr.fer.opp.projekt.server.repository;

import java.util.List;

import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Poruka;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PorukaRepository extends CrudRepository<Poruka, Long> {
	@Query("select u from Poruka u where u.korisnikDo = :korisnik and u.id > :id")
    List<Poruka> getPoruke(@Param("korisnik") Korisnik korisnik, @Param("id") long id);
}
