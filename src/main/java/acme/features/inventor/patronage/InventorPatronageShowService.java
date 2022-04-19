package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageShowService implements AbstractShowService<Inventor, Patronage> {
	
	@Autowired
	protected InventorPatronageRepository repo;
	
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		return true;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		int id;
		Patronage patronage;
		
		id = request.getModel().getInteger("id");
		patronage = this.repo.findPatronageById(id);
		
		return patronage;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "status", "code", "legalStuff","creationDate","budge","info");
		
	}

}
