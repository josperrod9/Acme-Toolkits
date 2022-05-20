package acme.features.inventor.patronageReport;

import java.util.Collection;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageReport;
import acme.entities.patronages.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport>{
	
	@Autowired
	protected InventorPatronageReportRepository repo;
	
	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		
		boolean result;
		Patronage patronage;
		int masterId;

		masterId = request.getModel().getInteger("masterId");
		patronage = this.repo.findPatronageById(masterId);
		
		result = request.isPrincipal(patronage.getInventor())&&patronage.getStatus()==Status.ACCEPTED;
		
		return result;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
        assert entity != null;

        request.bind(entity, errors, "memorandum", "automaticSequenceNumber", "creationMoment", "info","id");
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "memorandum", "automaticSequenceNumber", "creationMoment", "info","id");	
		model.setAttribute("masterId", request.getModel().getAttribute("masterId"));
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;
		PatronageReport result;		
		result = new PatronageReport();
		int masterId;
		Patronage patronage;
		String automaticSequenceNumber;
		final Collection<String> automaticSequenceNumbers = this.repo.findAllAutomaticSequenceNumberOfPatronagesReports();
		Random randomNumber;
		randomNumber = new Random();
		
		masterId = request.getModel().getInteger("masterId");
		patronage = this.repo.findPatronageById(masterId);
		result.setPatronage(patronage);
				
        Formatter fmt;
        fmt = new Formatter();
        fmt.format("%04d",randomNumber.nextInt(10000));
        automaticSequenceNumber = patronage.getCode()+":"+fmt;
        
        while(automaticSequenceNumbers.contains(automaticSequenceNumber)) {
        	fmt.format("%04d",randomNumber.nextInt(10000));
            automaticSequenceNumber = patronage.getCode()+":"+fmt;
        }
        fmt.close();
        
        result.setAutomaticSequenceNumber(automaticSequenceNumber);
        result.setCreationMoment(new Date());
		return result;
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;		
		
		boolean confirmation;
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");			
		
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;
		this.repo.save(entity);		
	}

}
