package tn.esprit.boostra.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.Partner;

@Repository
public interface PartnerRepository extends CrudRepository<Partner, Long>  {

}
