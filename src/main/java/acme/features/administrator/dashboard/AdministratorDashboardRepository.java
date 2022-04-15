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
//	
//	@Query("")
//	Map<String,Double> averageRetailPriceOfToolsGroupedByCurrency();
//
//	@Query("")
//	Map<String,Double> deviationRetailPriceOfToolsGroupedByCurrency();
//
//	@Query("")
//	Map<String,Double> minimumRetailPriceOfToolsGroupedByCurrency();
//
//	@Query("")
//	Map<String,Double> maximumRetailPriceOfToolsGroupedByCurrency();
//	
//	@Query("")
//	Map<Status,Integer>	totalNumberofPatronagesGroupedByStatus();
//	
//	@Query("")
//	Map<Status,Double> averageOfPatronagesGroupedByStatus();
//
//	@Query("")
//	Map<Status,Double> deviationOfPatronagesGroupedByStatus();
//
//	@Query("")
//	Map<Status,Double> minimumOfPatronagesGroupedByStatus();
//	
//	@Query("")
//	Map<Status,Double> maimumOfPatronagesGroupedByStatus();

}
