package tn.esprit.boostra.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.Contract;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long>{
	
	
	//non renouvelement 
	@Query("select c from Contract c where c.endDate <= ?1 and c.statut = true")
	List<Contract> listContarctExpiried(Date date);
	
	//fama renouvelement 
	//@Query("select c from Contrat c where c.endDate < ?1 and c.renewing = true")
	//List<Contract> listContarctExpiriedR(Date date);
	
	

}
