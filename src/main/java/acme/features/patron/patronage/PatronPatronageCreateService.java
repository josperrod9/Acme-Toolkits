package acme.features.patron.patronage;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repository;
	 
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;	
		return true;
	}

	
	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;
        
        request.bind(entity, errors, "code", "legalStuff", "budget", "startDate", "endDate", "info");
        
        String username;
        username = request.getModel().getString("username");
        
        Inventor inventor;
        inventor = this.repository.findOneInventorByUsername(username);
        entity.setInventor(inventor);
		
	}
	
	
	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;
        
        Date startDate;
        startDate = entity.getStartDate();
        
        if(!errors.hasErrors("code")) {
        	errors.state(request, this.repository.findPatronageByCode(entity.getCode()) == null, "code", "patron.patronage.show.error.not-unique");
        }
        
        if(!errors.hasErrors("budget")) {
        	String acceptedCurrencies;
            String[] currencies;
            acceptedCurrencies = this.repository.findConfiguration().getCurrency();
            currencies = acceptedCurrencies.split(",");
            boolean isCurrency = false;
            String c;
            c = entity.getBudget().getCurrency();
            for (final String currency : currencies) {
            	if (c.equals(currency)) {
            		isCurrency = true;
            	}
			}
        	errors.state(request, isCurrency, "budget", "patron.patronage.show.error.incorrect-currency");
        }
        
        if(!errors.hasErrors("startDate")) {
        	
        	Calendar calendar;
        	calendar = Calendar.getInstance();
        	calendar.setTime(entity.getCreationDate()); 
        	calendar.add(Calendar.MONTH, 1);
        	calendar.add(Calendar.SECOND, -1); 
        	errors.state(request, startDate.after(calendar.getTime()), "startDate", "patron.patronage.show.error.start-date-not-enough");
        }
        
        if(!errors.hasErrors("endDate")) {
        	Date endDate; 
        	endDate = entity.getEndDate();
        	Date moment; 
        	moment = new Date(startDate.getTime() + 604799999); 
        	errors.state(request, endDate.after(moment), "endDate", "patron.patronage.show.error.end-date-one-week-before-start-date");
        }
        
	}

	
	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "legalStuff", "budget", "startDate", "endDate", "info");
		
	}
	
	
	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;
		
		Patronage result;
		result = new Patronage();
		
		Patron patron;
		patron = this.repository.findOnePatronByUsername(request.getPrincipal().getUsername());
		result.setPatron(patron);
		
		result.setNotPublished(true);
		result.setStatus(Status.PROPOSED);
		
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationDate(moment);
		
		return result;
	}
	
	
	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
        assert entity != null;

        this.repository.save(entity);
	}
	
}
