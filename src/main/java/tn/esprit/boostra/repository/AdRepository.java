package tn.esprit.boostra.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.boostra.entity.Ad;
@Repository
public interface AdRepository extends CrudRepository<Ad, Long> {

  @Query(
      "Select a from Ad a where a.reseauxSociaux = ?1 and a.StartDate = CURRENT_DATE")
  List<Ad>
  test(String ReseauxSociaux);

  @Query("Select distinct a.reseauxSociaux from Ad a") List<String> test2();
}
