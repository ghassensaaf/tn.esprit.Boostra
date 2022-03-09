package tn.esprit.boostra.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.boostra.entity.Offer;

@Repository

public interface OfferRepository extends CrudRepository<Offer, Long> {}
