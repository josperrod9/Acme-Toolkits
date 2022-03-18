package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.patronages.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Map<Pair<String,String>,Double> averageRetailPriceOfComponentsGroupedByTechnologyAndCurrency;
	Map<Pair<String,String>,Double> deviationRetailPriceOfComponentsGroupedByTechnologyAndCurrency;
	Map<Pair<String,String>,Double> minimumRetailPriceOfComponentsGroupedByTechnologyAndCurrency;
	Map<Pair<String,String>,Double> maximumRetailPriceOfComponentsGroupedByTechnologyAndCurrency;
	
	Integer totalNumberOfTools;
	
	Map<String,Double> averageRetailPriceOfToolsGroupedByCurrency;
	Map<String,Double> deviationRetailPriceOfToolsGroupedByCurrency;
	Map<String,Double> minimumRetailPriceOfToolsGroupedByCurrency;
	Map<String,Double> maximumRetailPriceOfToolsGroupedByCurrency;
	
	Integer totalNumberOfProposedPatronages;
	Integer totalNumberOfAcceptedPatronages;
	Integer totalNumberOfDeniedPatronages;
	
	Map<Status,Double> averageOfPatronagesGroupedByStatus;
	Map<Status,Double> deviationOfPatronagesGroupedByStatus;
	Map<Status,Double> minimumOfPatronagesGroupedByStatus;
	Map<Status,Double> maimumOfPatronagesGroupedByStatus;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
