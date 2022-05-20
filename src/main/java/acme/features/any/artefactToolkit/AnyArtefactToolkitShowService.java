package acme.features.any.artefactToolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.ArtefactToolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyArtefactToolkitShowService implements AbstractShowService<Any, ArtefactToolkit> {
	
	@Autowired
	protected AnyArtefactToolkitRepository repo;
	
	
	@Override
	public boolean authorise(final Request<ArtefactToolkit> request) {
		assert request != null;
		return true;
	}

	@Override
	public ArtefactToolkit findOne(final Request<ArtefactToolkit> request) {
		assert request != null;
		
		int id;
		ArtefactToolkit result;
		
		id = request.getModel().getInteger("id");
		result = this.repo.findArtefactToolkitById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<ArtefactToolkit> request, final ArtefactToolkit entity, final Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "amount","artefact.name","artefact.type","artefact.code");	
	}

}
