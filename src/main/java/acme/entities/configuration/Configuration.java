package acme.entities.configuration;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	@NotBlank
	public String defaultCurrency;
	
	@NotBlank
	public String currency;
	
	@NotBlank
	public String weakSpamTerm;
	
	public double weakSpamThreshold;
	
	@NotBlank
	public String strongSpamTerm;
	
	public double strongSpamThreshold;
	
	// Derived attributes -----------------------------------------------------
	// Relationships ----------------------------------------------------------
}