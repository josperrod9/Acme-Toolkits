/*
 * AdministratorDashboardShowService.java
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, AdministratorDashboard> interface ----------------


	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;

		final AdministratorDashboard result;
		
		final List<String> averageRetailPriceOfComponentsByTechnologyAndCurrencyList=this.repository.averageRetailPriceOfComponentsGroupedByTechnologyAndCurrency();
		final Map<Pair<String,String>,Double> averageRetailPriceOfComponentsGroupedByTechnologyAndCurrency=new HashMap<Pair<String,String>, Double>();
		
		for(final String item: averageRetailPriceOfComponentsByTechnologyAndCurrencyList) {
			final String[] split = item.split(",");
			final String currency = split[0];
			final String technology = split[1];
			final Pair<String,String> key = Pair.of(technology, currency);
			final Double average = Double.parseDouble(split[2]);
			averageRetailPriceOfComponentsGroupedByTechnologyAndCurrency.put(key, average);
		}
		
		final List<String> deviationRetailPriceOfComponentsByTechnologyAndCurrencyList=this.repository.deviationRetailPriceOfComponentsGroupedByTechnologyAndCurrency();
		final Map<Pair<String,String>,Double> deviationRetailPriceOfComponentsGroupedByTechnologyAndCurrency=new HashMap<Pair<String,String>, Double>();

		for(final String item: deviationRetailPriceOfComponentsByTechnologyAndCurrencyList) {
			final String[] split = item.split(",");
			final String currency = split[0];
			final String technology = split[1];
			final Pair<String,String> key = Pair.of(technology, currency);
			final Double deviation = Double.parseDouble(split[2]);
			deviationRetailPriceOfComponentsGroupedByTechnologyAndCurrency.put(key, deviation);
		}
		
		final List<String> minimumRetailPriceOfComponentsByTechnologyAndCurrencyList=this.repository.minimumRetailPriceOfComponentsGroupedByTechnologyAndCurrency();
		final Map<Pair<String,String>,Double> minimumRetailPriceOfComponentsGroupedByTechnologyAndCurrency=new HashMap<Pair<String,String>, Double>();

		for(final String item: minimumRetailPriceOfComponentsByTechnologyAndCurrencyList) {
			final String[] split = item.split(",");
			final String currency = split[0];
			final String technology = split[1];
			final Pair<String,String> key = Pair.of(technology, currency);
			final Double minimun = Double.parseDouble(split[2]);
			minimumRetailPriceOfComponentsGroupedByTechnologyAndCurrency.put(key, minimun);
		}
		
		final List<String> maximumRetailPriceOfComponentsByTechnologyAndCurrencyList=this.repository.maximumRetailPriceOfComponentsGroupedByTechnologyAndCurrency();
		final Map<Pair<String,String>,Double> maximumRetailPriceOfComponentsGroupedByTechnologyAndCurrency=new HashMap<Pair<String,String>, Double>();

		for(final String item: maximumRetailPriceOfComponentsByTechnologyAndCurrencyList) {
			final String[] split = item.split(",");
			final String currency = split[0];
			final String technology = split[1];
			final Pair<String,String> key = Pair.of(technology, currency);
			final Double maximum = Double.parseDouble(split[2]);
			maximumRetailPriceOfComponentsGroupedByTechnologyAndCurrency.put(key, maximum);
		}
		
		final Integer totalNumberOfTools = this.repository.totalNumberOfTools();
		
		
		result= new AdministratorDashboard();
		result.setAverageOfPatronagesGroupedByStatus(null);
		result.setAverageRetailPriceOfComponentsGroupedByTechnologyAndCurrency(averageRetailPriceOfComponentsGroupedByTechnologyAndCurrency);
		result.setAverageRetailPriceOfToolsGroupedByCurrency(null);
		result.setDeviationOfPatronagesGroupedByStatus(null);
		result.setDeviationRetailPriceOfComponentsGroupedByTechnologyAndCurrency(deviationRetailPriceOfComponentsGroupedByTechnologyAndCurrency);
		result.setDeviationRetailPriceOfToolsGroupedByCurrency(null);
		result.setMaximumOfPatronagesGroupedByStatus(null);
		result.setMaximumRetailPriceOfComponentsGroupedByTechnologyAndCurrency(maximumRetailPriceOfComponentsGroupedByTechnologyAndCurrency);
		result.setMaximumRetailPriceOfToolsGroupedByCurrency(null);
		result.setMinimumOfPatronagesGroupedByStatus(null);
		result.setMinimumRetailPriceOfComponentsGroupedByTechnologyAndCurrency(minimumRetailPriceOfComponentsGroupedByTechnologyAndCurrency);
		result.setMinimumRetailPriceOfToolsGroupedByCurrency(null);
		result.setTotalNumberofPatronagesGroupedByStatus(null);
		result.setTotalNumberOfTools(totalNumberOfTools);
		return result;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"averageRetailPriceOfComponentsGroupedByTechnologyAndCurrency", "deviationRetailPriceOfComponentsGroupedByTechnologyAndCurrency", // 
			"minimumRetailPriceOfComponentsGroupedByTechnologyAndCurrency", "maximumRetailPriceOfComponentsGroupedByTechnologyAndCurrency", //
			"totalNumberOfTools");
	}

}
