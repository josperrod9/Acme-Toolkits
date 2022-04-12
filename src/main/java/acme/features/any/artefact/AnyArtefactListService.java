package acme.features.any.artefact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyArtefactListService implements AbstractListService<Any, Artefact>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AnyArtefactRepository repository;
	
	// AbstractListService<Any, Artefact> interface --------------
	
	@Override
	public boolean authorise(final Request<Artefact> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Artefact> findMany(final Request<Artefact> request) {
		return this.repository.findAllArtefacts();
	}

	@Override
	public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code","retailPrice", "type");
	}

}
