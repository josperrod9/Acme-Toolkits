package acme.entities.toolkits;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Artefact extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	@NotBlank
	@Length(max = 101)
	protected String			name;
	
	
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@Column(unique=true)
	@NotBlank
	protected String			code;
	
	@NotNull
	protected ArtefactType			type;
	
	@NotBlank
	@Length(max = 101)
	protected String			technology;

	@NotBlank
	@Length(max = 256)
	protected String			description;
	
	@Valid
	@NotNull
	protected Money 			retailPrice;

	
	@URL
	protected String			info;
	
	
	protected boolean 			published;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	@ManyToOne(optional = false)
	protected Inventor inventor;
}
