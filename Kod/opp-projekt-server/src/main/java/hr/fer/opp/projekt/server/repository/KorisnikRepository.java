package hr.fer.opp.projekt.server.repository;

import hr.fer.opp.projekt.common.model.Korisnik;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface KorisnikRepository extends CrudRepository<Korisnik, Long> {
}
