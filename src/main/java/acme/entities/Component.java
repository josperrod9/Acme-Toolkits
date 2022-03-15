package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Component extends AbstractEntity {
	// Serialisation identifier -----------------------------------------------

				protected static final long	serialVersionUID	= 1L;

				// Attributes -------------------------------------------------------------
			
				@NotBlank
				@Length(max=101)
				protected String			name;
				
				@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
				protected String			code;
				
				@NotBlank
				@Length(max=101)
				protected String			technology;
				
				@NotBlank
				@Length(max=256)
				protected String			description;
				
				@Min(0)
				protected Double price;
				protected String link;

				// Derived attributes -----------------------------------------------------

				// Relationships ----------------------------------------------------------

}
