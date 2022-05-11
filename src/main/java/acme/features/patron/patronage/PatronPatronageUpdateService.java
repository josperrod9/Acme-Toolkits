package acme.features.patron.patronage;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronPatronageUpdateService implements AbstractUpdateService<Patron, Patronage>{

	@Autowired
	protected PatronPatronageRepository repository;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean result;
		int id;
		Patronage patronage;
		Patron patron;
		
		id= request.getModel().getInteger("id");
		patronage = this.repository.findOneById(id);
		patron = patronage.getPatron();
		result = request.isPrincipal(patron)&&patronage.isNotPublished();

		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;

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
        	Date startDate; 
        	startDate = entity.getStartDate();
        	Calendar calendar;
        	calendar = Calendar.getInstance();
        	calendar.setTime(entity.getCreationDate()); 
        	calendar.add(Calendar.MONTH, 1);
        	calendar.add(Calendar.SECOND, -1); 
        	errors.state(request, startDate.after(calendar.getTime()), "startDate", "patron.patronage.show.error.start-date-not-enough");
        }

        if(!errors.hasErrors("endDate")) {
        	Date startDate;
        	startDate = entity.getStartDate();
        	Date endDate; 
        	endDate = entity.getEndDate();
        	Date moment; 
        	moment = new Date(startDate.getTime() + 604799999); 
        	errors.state(request, endDate.after(moment), "endDate", "patron.patronage.show.error.end-date-one-week-before-start-date");
        }

	}
	
	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;
        
		request.bind(entity, errors,  "status", "legalStuff", "budget", "startDate", "endDate", "info","inventor.userAccount.username");
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "status", "legalStuff", "budget","creationDate", "startDate", "endDate", "info",
            "inventor.userAccount.username","inventor.company", "inventor.statement", "inventor.inventorInfo","notPublished");

	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
        assert entity != null;

        this.repository.save(entity);
	}

}
