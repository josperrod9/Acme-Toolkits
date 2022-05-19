package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;


@Repository
public interface InventorPatronageReportRepository extends AbstractRepository{

	@Query("SELECT p FROM PatronageReport p WHERE p.id =:id")
	PatronageReport findPatronageReportById(int id);

	@Query("SELECT i FROM Inventor i WHERE i.id =:id")
	Inventor findInventorById(int id);	
	
	@Query("SELECT p FROM Patronage p WHERE p.id =:id")
	Patronage findPatronageById(int id);

	@Query("SELECT p FROM Patronage p WHERE p.code =:code")
	Patronage findPatronageByCode(String code);

	@Query("SELECT p.automaticSequenceNumber FROM PatronageReport p")
	Collection<String> findAllAutomaticSequenceNumberOfPatronagesReports();

	@Query("SELECT p FROM PatronageReport p where p.patronage.id=:masterId")
	Collection<PatronageReport> findPatronageReportsByPatronageId(int masterId);
	
	
}
