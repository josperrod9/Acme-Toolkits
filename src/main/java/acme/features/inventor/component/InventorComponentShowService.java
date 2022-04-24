package acme.features.inventor.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorComponentShowService implements AbstractShowService<Inventor, Artefact> {
	
	@Autowired
	protected InventorComponentRepository repo;
	
	
	@Override
	public boolean authorise(final Request<Artefact> request) {
		 assert request != null;

	        boolean result;
	        int id;
	        id = request.getModel().getInteger("id");

	        Artefact artefact;
	        artefact = this.repo.findArtefactById(id);

	        result = request.isPrincipal(artefact.getInventor());

	        return result;
	}

	@Override
	public Artefact findOne(final Request<Artefact> request) {
		assert request != null;
		
		int id;
		Artefact artefact;
		
		id = request.getModel().getInteger("id");
		artefact = this.repo.findArtefactById(id);
		
		return artefact;
	}

	@Override
	public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "type", "info", "id");
		
	}

}
