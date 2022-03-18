package acme.forms;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalNumberofProposedPatronages;
	Integer						totalNumberofDeniedPatronages;
	Integer						totalNumberofAceptedPatronages;
	
	
	Map<String,Double>			averageBudgetOfProposedPatronagesGroupedByCurrency;
	Map<String,Double>			deviationBudgetOfProposedPatronagesGroupedByCurrency;
	Map<String,Double>			minimunBudgetOfProposedPatronagesGroupedByCurrency;
	Map<String,Double>			maximunBudgetOfProposedPatronagesGroupedByCurrency;
	

	Map<String,Double>			averageBudgetOfDeniedPatronagesGroupedByCurrency;
	Map<String,Double>			deviationBudgetOfDeniedPatronagesGroupedByCurrency;
	Map<String,Double>			minimunBudgetOfDeniedPatronagesGroupedByCurrency;
	Map<String,Double>			maximunBudgetOfDeniedPatronagesGroupedByCurrency;

	
	Map<String,Double>			averageBudgetOfAceptedPatronagesGroupedByCurrency;
	Map<String,Double>			deviationBudgetOfAceptedPatronagesGroupedByCurrency;
	Map<String,Double>			minimunBudgetOfAceptedPatronagesGroupedByCurrency;
	Map<String,Double>			maximunBudgetOfAceptedPatronagesGroupedByCurrency;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
