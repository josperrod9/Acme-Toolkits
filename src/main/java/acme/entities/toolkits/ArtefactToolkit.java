package acme.entities.toolkits;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ArtefactToolkit extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
		@NotNull
		protected ArtefactType			type;
		
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
		@Valid
		@NotNull
		@ManyToOne(optional=false)
		protected Artefact artefact;
		
		@Valid
		@NotNull
		@ManyToOne(optional=false)
		protected Toolkit toolkit;
		

}
