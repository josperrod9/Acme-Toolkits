package acme.features.patron.dashboard;


import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Status;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronDashboardRepository repository;

	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;
		
		Principal principal;
		principal = request.getPrincipal();
		
		final PatronDashboard result;
		Integer numPatronageProposed;
		Integer numPatronageAccepted;
		Integer numPatronageDenied;

		numPatronageProposed = this.repository.numPatronageProposedByPatronId(principal.getActiveRoleId());
		numPatronageAccepted = this.repository.numPatronageAcceptedByPatronId(principal.getActiveRoleId());
		numPatronageDenied = this.repository.numPatronageDeniedByPatronId(principal.getActiveRoleId());
	
		final Map<Status,Integer> map= new EnumMap<Status, Integer>(Status.class);
		map.put(Status.ACCEPTED, numPatronageAccepted);
		map.put(Status.DENIED, numPatronageDenied);
		map.put(Status.PROPOSED, numPatronageProposed);
		
		final Map<Pair<Status,String>, Double> averageBudgetOfPatronagesStatusByCurrency = new HashMap<Pair<Status,String>, Double>();
		final List<String> averageBudgetByCurrency = this.repository.averageBudgetOfPatronagesGroupedByCurrencyAndStatusByPatronId(principal.getActiveRoleId());
		
		for(final String item: averageBudgetByCurrency) {
			final String[] split = item.split(",");
			final Double amount = Double.parseDouble(split[1]);
			final String currency = split[0];
			final Status status = Status.valueOf(split[2]);
			final Pair<Status,String> key = Pair.of(status, currency);
			averageBudgetOfPatronagesStatusByCurrency.put(key, amount);
		}
				
		final Map<Pair<Status,String>, Double> deviationBudgetOfPatronagesStatusByCurrency = new HashMap<Pair<Status,String>, Double>();
		final List<String> deviationBudgetByCurrency = this.repository.deviationBudgetOfPatronagesGroupedByCurrencyAndStatusByPatronId(principal.getActiveRoleId());
		
		for(final String item: deviationBudgetByCurrency) {
			final String[] split = item.split(",");
			final Double amount = Double.parseDouble(split[1]);
			final String currency = split[0];
			final Status status = Status.valueOf(split[2]);
			final Pair<Status,String> key = Pair.of(status, currency);
			deviationBudgetOfPatronagesStatusByCurrency.put(key, amount);
		}
		
		final Map<Pair<Status,String>, Double> minimumBudgetOfPatronagesStatusByCurrency = new HashMap<Pair<Status,String>, Double>();
		final List<String> minimumBudgetByCurrency = this.repository.minimumBudgetOfPatronagesGroupedByCurrencyAndStatusByPatronId(principal.getActiveRoleId());
		
		for(final String item: minimumBudgetByCurrency) {
			final String[] split = item.split(",");
			final Double amount = Double.parseDouble(split[1]);
			final String currency = split[0];
			final Status status = Status.valueOf(split[2]);
			final Pair<Status,String> key = Pair.of(status, currency);
			minimumBudgetOfPatronagesStatusByCurrency.put(key, amount);
		}
		
		final Map<Pair<Status,String>, Double> maximumBudgetOfPatronagesStatusByCurrency = new HashMap<Pair<Status,String>, Double>();
		final List<String> maximumBudgetByCurrency = this.repository.maximumBudgetOfPatronagesGroupedByCurrencyAndStatusByPatronId(principal.getActiveRoleId());
		
		for(final String item: maximumBudgetByCurrency) {
			final String[] split = item.split(",");
			final Double amount = Double.parseDouble(split[1]);
			final String currency = split[0];
			final Status status = Status.valueOf(split[2]);
			final Pair<Status, String> key = Pair.of(status, currency);
			maximumBudgetOfPatronagesStatusByCurrency.put(key, amount);
		}

		result = new PatronDashboard();
		result.setTotalNumberOfPatronagesGroupedByStatus(map);
		result.setAverageBudgetOfPatronagesGroupedByCurrencyAndStatus(averageBudgetOfPatronagesStatusByCurrency);
		result.setDeviationBudgetOfPatronagesGroupedByCurrencyAndStatus(deviationBudgetOfPatronagesStatusByCurrency);
		result.setMinimumBudgetOfPatronagesGroupedByCurrencyAndStatus(minimumBudgetOfPatronagesStatusByCurrency);
		result.setMaximumBudgetOfPatronagesGroupedByCurrencyAndStatus(maximumBudgetOfPatronagesStatusByCurrency);

		return result;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, 
			"totalNumberOfPatronagesGroupedByStatus", 
			"averageBudgetOfPatronagesGroupedByCurrencyAndStatus",
			"deviationBudgetOfPatronagesGroupedByCurrencyAndStatus", 
			"minimumBudgetOfPatronagesGroupedByCurrencyAndStatus",
			"maximumBudgetOfPatronagesGroupedByCurrencyAndStatus");
	}
	

}
