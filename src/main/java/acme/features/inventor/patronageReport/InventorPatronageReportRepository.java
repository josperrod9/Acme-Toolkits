package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.PatronageReport;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface InventorPatronageReportRepository extends AbstractRepository{
	
	@Query("SELECT p FROM PatronageReport p WHERE p.patronage.inventor.id = :id")
	Collection<PatronageReport> findAllInventorPatronageReports(int id);

	@Query("SELECT p FROM PatronageReport p WHERE p.id =:id")
	PatronageReport findPatronageReportById(int id);
	

}
