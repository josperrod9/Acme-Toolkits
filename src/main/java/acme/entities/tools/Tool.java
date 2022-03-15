package acme.entities.tools;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tool extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	@NotBlank
	@Length(max = 101)
	protected String			name;
	
	
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$", message = "default.error.conversion")
	@Column(unique=true)
	protected String			code;

	@NotBlank
	@Length(max = 101)
	protected String			technology;

	@NotBlank
	@Length(max = 256)
	protected String			description;
	
	@Min(0)
	protected Double 			retailPrice;

	@URL
	protected String			info;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
