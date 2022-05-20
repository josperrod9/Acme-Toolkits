package acme.features.inventor.artefactToolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.ArtefactToolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;
@Service
public class InventorArtefactToolkitDeleteService implements AbstractDeleteService<Inventor, ArtefactToolkit>{

	@Autowired
	protected InventorArtefactToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<ArtefactToolkit> request) {
		assert request != null;

        boolean result;
        int id;
        id = request.getModel().getInteger("id");

        ArtefactToolkit artefactToolkit;
        artefactToolkit = this.repository.findArtefactToolkitById(id);

        result = request.isPrincipal(artefactToolkit.getToolkit().getInventor());


        return result;

	}

	@Override
	public void bind(final Request<ArtefactToolkit> request, final ArtefactToolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors,"amount","artefact","toolkit");		
	}

	@Override
	public void unbind(final Request<ArtefactToolkit> request, final ArtefactToolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "amount","artefact","toolkit");		
	}

	@Override
	public ArtefactToolkit findOne(final Request<ArtefactToolkit> request) {
		assert request != null;

		ArtefactToolkit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findArtefactToolkitById(id);

		return result;
	}

	@Override
	public void validate(final Request<ArtefactToolkit> request, final ArtefactToolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;		
	}

	@Override
	public void delete(final Request<ArtefactToolkit> request, final ArtefactToolkit entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
		
	}
}
