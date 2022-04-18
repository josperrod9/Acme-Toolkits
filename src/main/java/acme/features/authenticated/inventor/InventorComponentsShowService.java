package acme.features.authenticated.inventor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorComponentsShowService implements AbstractShowService<Inventor, Artefact> {
	
	@Autowired
	protected InventorComponentsRepository repo;
	
	
	@Override
	public boolean authorise(final Request<Artefact> request) {
		assert request != null;
		return true;
	}

	@Override
	public Artefact findOne(final Request<Artefact> request) {
		assert request != null;
		
		Integer id;
		final Artefact artefact;
		id = request.getModel().getInteger("id");
		artefact = this.repo.findArtefactById(id);
		
		return artefact;
	}

	@Override
	public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "type", "info");
		
	}

}
