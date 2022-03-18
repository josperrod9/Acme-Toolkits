package acme.entities.configuration;

import javax.persistence.Entity;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InitialConfiguration extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	public String defaultCurrency;
	
	public String currency;
	
	public String weakSpamTerms;
	
	public Double weakSpamThreshold;
	
	public String strongSpamTerms;
	
	public Double strongSpamThreshold;
	
	// Derived attributes -----------------------------------------------------
	// Relationships ----------------------------------------------------------
}