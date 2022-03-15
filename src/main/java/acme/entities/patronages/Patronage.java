package acme.entities.patronages;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	protected Status			status;
	
	
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$", message = "default.error.conversion")
	@Column(unique=true)
	protected String			code;

	@NotBlank
	@Length(max = 256)
	protected String			legalStuff;
	
	@Positive
	protected Double 			budget;
	
	@URL
	protected String			info;

	// Derived attributes -----------------------------------------------------
	@Temporal(TemporalType.TIMESTAMP)
    protected Date                    periodOfTime;

	// Relationships ----------------------------------------------------------

}

