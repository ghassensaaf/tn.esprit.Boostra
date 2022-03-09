package tn.esprit.boostra.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import tn.esprit.boostra.entity.Reclamation;
import tn.esprit.boostra.entity.TypeRec;
import tn.esprit.boostra.entity.User;


public interface ReclamationRepository extends CrudRepository<Reclamation, Long>{
	@Query
	("Select r from Reclamation r  WHERE r.statuR='Non traiter' AND r.typeReclamation= ?1")
	List <Reclamation> TraiterSystemReclamation(TypeRec sys);

	List<Reclamation> findByUser(User user);
}
