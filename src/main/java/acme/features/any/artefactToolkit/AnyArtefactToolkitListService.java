package acme.features.any.artefactToolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.ArtefactToolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyArtefactToolkitListService implements AbstractListService<Any,ArtefactToolkit> {
	
	
	@Autowired
	protected AnyArtefactToolkitRepository repo;
	
	
	@Override
	public boolean authorise(final Request<ArtefactToolkit> request) {
		assert request != null;
		return true;
	}
	
	
	@Override
    public Collection<ArtefactToolkit> findMany(final Request<ArtefactToolkit> request) {
		int masterId;
		masterId = request.getModel().getInteger("masterId");
		return this.repo.findManyArtefactToolkitByToolkitId(masterId);
    }

	
    @Override
    public void unbind(final Request<ArtefactToolkit> request, final ArtefactToolkit entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;
        
        request.unbind(entity, model, "amount","artefact.name");
    }
}
