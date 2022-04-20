package acme.features.patron.patronageReport;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageReportRepository extends AbstractRepository {
	
	@Query("select pr from PatronageReport pr where pr.patronage.id = :patronageId")
	Collection<PatronageReport> findManyPatronagesReportByPatronageId(int patronageId);
	
	@Query("select pr from PatronageReport pr where pr.id = :id")
	PatronageReport findOneById(int id);

}