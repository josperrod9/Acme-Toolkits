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

import acme.entities.patronages.Status;
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
		final Map<String,Double> averageRetailPriceOfToolsGroupedByCurrency=new HashMap<String, Double>();
		final List<String> averageRetailPriceOfToolsByCurrencyList = this.repository.averageRetailPriceOfToolsGroupedByCurrency();
		for(final String str:averageRetailPriceOfToolsByCurrencyList) {
			final String[] split = str.split(",");
			final String key = split[0];
			final Double value = Double.parseDouble(split[1]);
			averageRetailPriceOfToolsGroupedByCurrency.put(key,value);
		}
		
		final Map<String,Double> deviationRetailPriceOfToolsGroupedByCurrency=new HashMap<String, Double>();
		final List<String> deviationRetailPriceOfToolsByCurrencyList=this.repository.deviationRetailPriceOfToolsGroupedByCurrency();
		for(final String str:deviationRetailPriceOfToolsByCurrencyList) {
			final String[] split = str.split(",");
			final String key = split[0];
			final Double value = Double.parseDouble(split[1]);
			deviationRetailPriceOfToolsGroupedByCurrency.put(key,value);
		}
		
		final Map<String,Double> minimumRetailPriceOfToolsGroupedByCurrency=new HashMap<String, Double>();
		final List<String> minimumRetailPriceOfToolsByCurrencyList= this.repository.minimumRetailPriceOfToolsGroupedByCurrency();
		for(final String str:minimumRetailPriceOfToolsByCurrencyList) {
			final String[] split = str.split(",");
			final String key = split[0];
			final Double value = Double.parseDouble(split[1]);
			minimumRetailPriceOfToolsGroupedByCurrency.put(key,value);
		}
		
		final Map<String,Double> maximumRetailPriceOfToolsGroupedByCurrency=new HashMap<String, Double>();
		final List<String> maximumRetailPriceOfToolsByCurrencyList= this.repository.maximumRetailPriceOfToolsGroupedByCurrency();
		for(final String str:maximumRetailPriceOfToolsByCurrencyList) {
			final String[] split = str.split(",");
			final String key = split[0];
			final Double value = Double.parseDouble(split[1]);
			maximumRetailPriceOfToolsGroupedByCurrency.put(key,value);
		}
		
		final Map<Status,Integer> totalNumberofPatronagesGroupedByStatus=new HashMap<Status, Integer>();
		final List<String> totalNumberofPatronagesByStatusList=this.repository.totalNumberofPatronagesGroupedByStatus();
		for(final String str:totalNumberofPatronagesByStatusList) {
			final String[] split = str.split(",");
			final Status key = Status.valueOf(split[0]);
			final Integer value = Integer.parseInt(split[1]);
			totalNumberofPatronagesGroupedByStatus.put(key,value);
		}
		
		final Map<Status,Double> averageOfPatronagesGroupedByStatus=new HashMap<Status, Double>();
		final List<String> averageOfPatronagesByStatusList = this.repository.averageOfPatronagesGroupedByStatus();
		for(final String str:averageOfPatronagesByStatusList) {
			final String[] split = str.split(",");
			final Status key = Status.valueOf(split[0]);
			final Double value = Double.parseDouble(split[1]);
			averageOfPatronagesGroupedByStatus.put(key,value);
		}
		
		final Map<Status,Double> deviationOfPatronagesGroupedByStatus=new HashMap<Status, Double>();
		final List<String> deviationOfPatronagesByStatusList = this.repository.deviationOfPatronagesGroupedByStatus();
		for(final String str:deviationOfPatronagesByStatusList) {
			final String[] split = str.split(",");
			final Status key = Status.valueOf(split[0]);
			final Double value = Double.parseDouble(split[1]);
			deviationOfPatronagesGroupedByStatus.put(key,value);
		}
		
		final Map<Status,Double> minimumOfPatronagesGroupedByStatus=new HashMap<Status, Double>();
		final List<String> minimumOfPatronagesByStatusList = this.repository.minimumOfPatronagesGroupedByStatus();
		for(final String str:minimumOfPatronagesByStatusList) {
			final String[] split = str.split(",");
			final Status key = Status.valueOf(split[0]);
			final Double value = Double.parseDouble(split[1]);
			minimumOfPatronagesGroupedByStatus.put(key,value);
		}
		
		final Map<Status,Double> maximumOfPatronagesGroupedByStatus=new HashMap<Status, Double>();
		final List<String> maximumOfPatronagesByStatusList = this.repository.maximumOfPatronagesGroupedByStatus();
		for(final String str:maximumOfPatronagesByStatusList) {
			final String[] split = str.split(",");
			final Status key = Status.valueOf(split[0]);
			final Double value = Double.parseDouble(split[1]);
			maximumOfPatronagesGroupedByStatus.put(key,value);
		}
		
		result= new AdministratorDashboard();
		result.setAverageOfPatronagesGroupedByStatus(averageOfPatronagesGroupedByStatus);
		result.setAverageRetailPriceOfComponentsGroupedByTechnologyAndCurrency(averageRetailPriceOfComponentsGroupedByTechnologyAndCurrency);
		result.setAverageRetailPriceOfToolsGroupedByCurrency(averageRetailPriceOfToolsGroupedByCurrency);
		result.setDeviationOfPatronagesGroupedByStatus(deviationOfPatronagesGroupedByStatus);
		result.setDeviationRetailPriceOfComponentsGroupedByTechnologyAndCurrency(deviationRetailPriceOfComponentsGroupedByTechnologyAndCurrency);
		result.setDeviationRetailPriceOfToolsGroupedByCurrency(deviationRetailPriceOfToolsGroupedByCurrency);
		result.setMaximumOfPatronagesGroupedByStatus(maximumOfPatronagesGroupedByStatus);
		result.setMaximumRetailPriceOfComponentsGroupedByTechnologyAndCurrency(maximumRetailPriceOfComponentsGroupedByTechnologyAndCurrency);
		result.setMaximumRetailPriceOfToolsGroupedByCurrency(maximumRetailPriceOfToolsGroupedByCurrency);
		result.setMinimumOfPatronagesGroupedByStatus(minimumOfPatronagesGroupedByStatus);
		result.setMinimumRetailPriceOfComponentsGroupedByTechnologyAndCurrency(minimumRetailPriceOfComponentsGroupedByTechnologyAndCurrency);
		result.setMinimumRetailPriceOfToolsGroupedByCurrency(minimumRetailPriceOfToolsGroupedByCurrency);
		result.setTotalNumberofPatronagesGroupedByStatus(totalNumberofPatronagesGroupedByStatus);
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
			"totalNumberOfTools","averageRetailPriceOfToolsGroupedByCurrency","deviationRetailPriceOfToolsGroupedByCurrency", //
			"minimumRetailPriceOfToolsGroupedByCurrency","maximumRetailPriceOfToolsGroupedByCurrency","totalNumberofPatronagesGroupedByStatus", //
			"averageOfPatronagesGroupedByStatus","deviationOfPatronagesGroupedByStatus","minimumOfPatronagesGroupedByStatus","maximumOfPatronagesGroupedByStatus");
	}
	

}