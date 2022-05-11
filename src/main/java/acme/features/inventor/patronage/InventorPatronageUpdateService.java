package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageUpdateService implements AbstractUpdateService<Inventor, Patronage>{

	@Autowired
	protected InventorPatronageRepository repo;

	@Override
	public boolean authorise(final Request<Patronage> request) {
        assert request != null;

        boolean result;
        int id;
        id = request.getModel().getInteger("id");

        Patronage patronage;
        patronage = this.repo.findPatronageById(id);

        result = request.isPrincipal(patronage.getInventor());
        return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
        assert entity != null;

        request.bind(entity, errors, "status", "code", "legalStuff","creationDate","budget","info","id");
				
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "status", "code", "legalStuff","creationDate","budget","info","id");		
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
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			Patronage existing;
			
			existing = this.repo.findPatronageByCode(entity.getCode());
			errors.state(request, existing == null|| existing.getId() == entity.getId(), "code", "inventor.patronage.form.error.duplicated");
		}
	
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		this.repo.save(entity);	}

}
