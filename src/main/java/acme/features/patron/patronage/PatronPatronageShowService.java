package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	@Override
    public boolean authorise(final Request<Patronage> request) {
        assert request != null;

        boolean result;
        int id;
        id = request.getModel().getInteger("id");

        Patronage patronage;
        patronage = this.repository.findOneById(id);

        result = request.isPrincipal(patronage.getPatron());

        return result;
    }
	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		Patronage result;
		int id;
		
		id =  request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		Inventor inventor;
		inventor = entity.getInventor();
		UserAccount inventorAccount;
		inventorAccount = inventor.getUserAccount();
		request.unbind(entity, model,"status", "code" , "legalStuff", "budget", "creationDate", "startDate", "endDate", "info","notPublished");
		request.unbind(inventor, model, "company", "statement", "inventorInfo");
		request.unbind(inventorAccount, model, "username");
	}
}