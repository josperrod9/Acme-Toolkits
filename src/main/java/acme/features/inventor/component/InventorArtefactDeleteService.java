package acme.features.inventor.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;
@Service
public class InventorArtefactDeleteService implements AbstractDeleteService<Inventor, Artefact>{

	@Autowired
	protected InventorComponentRepository repository;
	
	@Override
	public boolean authorise(final Request<Artefact> request) {
		assert request != null;

        boolean result;
        int id;
        id = request.getModel().getInteger("id");

        Artefact artefact;
        artefact = this.repository.findArtefactById(id);

        result = !artefact.isPublished() && request.isPrincipal(artefact.getInventor());


        return result;

	}

	@Override
	public void bind(final Request<Artefact> request, final Artefact entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name", "code", "type", "technology", "description","retailPrice","info");		
	}

	@Override
	public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "type", "technology", "description","retailPrice","info","published");		
	}

	@Override
	public Artefact findOne(final Request<Artefact> request) {
		assert request != null;

		Artefact result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findArtefactById(id);

		return result;
	}

	@Override
	public void validate(final Request<Artefact> request, final Artefact entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;		
	}

	@Override
	public void delete(final Request<Artefact> request, final Artefact entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
		
	}
}
