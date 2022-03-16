package acme.entities.patronages;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PatronageReport extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@NotBlank
	@Length(max = 256)
	protected String			memorandum;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
    protected Date              creationMoment;
	
	@URL
	protected String			info;

	// Derived attributes -----------------------------------------------------
	
	@NotBlank
	public String automaticSequenceNumber() {
		StringBuilder sequenceNumber;
		sequenceNumber = new StringBuilder();
		sequenceNumber.append(this.patronage.getCode());
	    sequenceNumber.append(String.format("%04d",this.id));
		return sequenceNumber.toString();
	};
	
	public Patron patron() {
		return this.patronage.patron;
	}
	
	public Inventor inventor() {
		return this.patronage.inventor;
	}

	// Relationships ----------------------------------------------------------
	@Valid
	@NotNull
	@OneToOne(optional=false)
	protected Patronage patronage;
	
}
