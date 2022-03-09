package tn.esprit.boostra.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.GeoIP;

@Repository
public interface GeoIPRepository extends CrudRepository<GeoIP, Long>{

}
