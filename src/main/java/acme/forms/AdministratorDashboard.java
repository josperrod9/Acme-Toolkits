package acme.forms;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Map<String,Double> averageRetailPriceOfComponentsGroupedByTechnology;
	Map<String,Double> deviationRetailPriceOfComponentsGroupedByTechnology;
	Map<String,Double> minimumRetailPriceOfComponentsGroupedByTechnology;
	Map<String,Double> maximumRetailPriceOfComponentsGroupedByTechnology;
	
	Map<String,Double> averageRetailPriceOfComponentsGroupedByCurrency;
	Map<String,Double> deviationRetailPriceOfComponentsGroupedByCurrency;
	Map<String,Double> minimumRetailPriceOfComponentsGroupedByCurrency;
	Map<String,Double> maximumRetailPriceOfComponentsGroupedByCurrency;
	
	Integer totalNumberOfTools;
	
	Map<String,Double> averageRetailPriceOfToolsGroupedByCurrency;
	Map<String,Double> deviationRetailPriceOfToolsGroupedByCurrency;
	Map<String,Double> minimumRetailPriceOfToolsGroupedByCurrency;
	Map<String,Double> maximumRetailPriceOfToolsGroupedByCurrency;
	
	Integer totalNumberOfProposedPatronages;
	Integer totalNumberOfAcceptedPatronages;
	Integer totalNumberOfDeniedPatronages;
	
	Double averageOfProposedPatronages;
	Double deviationOfProposedPatronages;
	Double minimumOfProposedPatronages;
	Double maximumOfProposedPatronages;
	
	Double averageOfAceptedPatronages;
	Double deviationOfAceptedPatronages;
	Double minimumOfAceptedPatronages;
	Double maximumOfAceptedPatronages;
	
	Double averageOfDeniedPatronages;
	Double deviationOfDeniedPatronages;
	Double minimumOfDeniedPatronages;
	Double maximumOfDeniedPatronages;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
