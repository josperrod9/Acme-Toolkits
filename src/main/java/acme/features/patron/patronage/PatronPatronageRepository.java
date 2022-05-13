package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.Configuration;
import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository{
	
	@Query("select p from Patronage p where p.patron.id = :patronId")
	Collection<Patronage> findManyByPatronId(int patronId);

	@Query("select p from Patronage p where p.id = :id")
	Patronage findOneById(int id);
	
	@Query("select i from Inventor i where i.userAccount.username = :username")
	Inventor findOneInventorByUsername(String username);
	
	@Query("select p from Patron p where p.userAccount.username = :username")
	Patron findOnePatronByUsername(String username);
	
	@Query("select c from Configuration c")
	Configuration findConfiguration();
	

	@Query("select p from Patronage p where p.code = :code")
	Patronage findPatronageByCode(String code);
	
	@Query("select p from PatronageReport p where p.patronage.id = :id")
	Collection<PatronageReport> findPatronageReportByPatronageId(int id);
	
	@Query("select i.userAccount.username from Inventor i")
	Collection<String> findInventorsUsername();
}
