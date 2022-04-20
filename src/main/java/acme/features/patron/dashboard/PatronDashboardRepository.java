package acme.features.patron.dashboard;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository{
	
	@Query("SELECT count(p) FROM Patronage p WHERE p.status = 0 AND p.patron.id = :patronId")
	Integer numPatronageProposedByPatronId(int patronId);
	
	@Query("SELECT count(p) FROM Patronage p WHERE p.status = 1 AND p.patron.id = :patronId")
	Integer numPatronageAcceptedByPatronId(int patronId);
	
	@Query("SELECT count(p) FROM Patronage p WHERE p.status = 2 AND p.patron.id = :patronId")
	Integer numPatronageDeniedByPatronId(int patronId);
	
	@Query("SELECT p.budget.currency, avg(p.budget.amount), p.status FROM Patronage p WHERE p.patron.id = :patronId GROUP BY p.budget.currency, p.status")
	List<String> averageBudgetOfPatronagesGroupedByCurrencyAndStatusByPatronId(int patronId);
	
	@Query("SELECT p.budget.currency, stddev(p.budget.amount), p.status FROM Patronage p WHERE p.patron.id = :patronId GROUP BY p.budget.currency, p.status")
	List<String> deviationBudgetOfPatronagesGroupedByCurrencyAndStatusByPatronId(int patronId);
	
	@Query("SELECT p.budget.currency, min(p.budget.amount), p.status FROM Patronage p WHERE p.patron.id = :patronId GROUP BY p.budget.currency, p.status")
	List<String> minimumBudgetOfPatronagesGroupedByCurrencyAndStatusByPatronId(int patronId);
	
	@Query("SELECT p.budget.currency, max(p.budget.amount), p.status FROM Patronage p WHERE p.patron.id = :patronId GROUP BY p.budget.currency, p.status")
	List<String> maximumBudgetOfPatronagesGroupedByCurrencyAndStatusByPatronId(int patronId);
	
}