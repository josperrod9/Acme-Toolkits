/*
 * AdministratorDashboardRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("SELECT a.retailPrice.currency, a.technology, avg(a.retailPrice.amount) FROM Artefact a WHERE a.type = 1 GROUP BY a.retailPrice.currency, a.technology")
	List<String> averageRetailPriceOfComponentsGroupedByTechnologyAndCurrency();

	@Query("SELECT a.retailPrice.currency, a.technology, stddev(a.retailPrice.amount) FROM Artefact a WHERE a.type = 1 GROUP BY a.retailPrice.currency, a.technology")
	List<String> deviationRetailPriceOfComponentsGroupedByTechnologyAndCurrency();

	@Query("SELECT a.retailPrice.currency, a.technology, min(a.retailPrice.amount) FROM Artefact a WHERE a.type = 1 GROUP BY a.retailPrice.currency, a.technology")
	List<String> minimumRetailPriceOfComponentsGroupedByTechnologyAndCurrency();

	@Query("SELECT a.retailPrice.currency, a.technology, max(a.retailPrice.amount) FROM Artefact a WHERE a.type = 1 GROUP BY a.retailPrice.currency, a.technology")
	List<String> maximumRetailPriceOfComponentsGroupedByTechnologyAndCurrency();

	@Query("SELECT count(a) FROM Artefact a WHERE a.type = 0")
	Integer totalNumberOfTools();
	
	@Query("SELECT a.retailPrice.currency,avg(a.retailPrice.amount) FROM Artefact a where a.type=0 GROUP BY a.retailPrice.currency")
	List<String> averageRetailPriceOfToolsGroupedByCurrency();

	@Query("SELECT a.retailPrice.currency,stddev(a.retailPrice.amount) FROM Artefact a where a.type=0 GROUP BY a.retailPrice.currency")
	List<String> deviationRetailPriceOfToolsGroupedByCurrency();

	@Query("SELECT a.retailPrice.currency,min(a.retailPrice.amount) FROM Artefact a where a.type=0 GROUP BY a.retailPrice.currency")
	List<String> minimumRetailPriceOfToolsGroupedByCurrency();

	@Query("SELECT a.retailPrice.currency,max(a.retailPrice.amount) FROM Artefact a where a.type=0 GROUP BY a.retailPrice.currency")
	List<String> maximumRetailPriceOfToolsGroupedByCurrency();
	
	@Query("SELECT p.status,count(p) FROM Patronage p GROUP BY p.status")
	List<String> totalNumberofPatronagesGroupedByStatus();
	
	@Query("SELECT p.status, avg(p.budget.amount), p.status FROM Patronage p GROUP BY p.status")
	List<String> averageOfPatronagesGroupedByStatus();

	@Query("SELECT p.status, stddev(p.budget.amount), p.status FROM Patronage p GROUP BY p.status")
	List<String> deviationOfPatronagesGroupedByStatus();

	@Query("SELECT p.status, min(p.budget.amount), p.status FROM Patronage p GROUP BY p.status")
	List<String> minimumOfPatronagesGroupedByStatus();
	
	@Query("SELECT p.status, max(p.budget.amount), p.status FROM Patronage p GROUP BY p.status")
	List<String> maximumOfPatronagesGroupedByStatus();

}
