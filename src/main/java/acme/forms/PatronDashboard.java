package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.patronages.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Map<Status,Integer>	totalNumberOfPatronagesGroupedByStatus;
	
	Map<Pair<Status, String>,Double> averageBudgetOfPatronagesGroupedByCurrencyAndStatus;
	Map<Pair<Status, String>,Double> deviationBudgetOfPatronagesGroupedByCurrencyAndStatus;
	Map<Pair<Status, String>,Double> minimumBudgetOfPatronagesGroupedByCurrencyAndStatus;
	Map<Pair<Status, String>,Double> maximumBudgetOfPatronagesGroupedByCurrencyAndStatus;
	


	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
