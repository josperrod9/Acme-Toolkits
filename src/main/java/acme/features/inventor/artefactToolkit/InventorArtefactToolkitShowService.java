package acme.features.inventor.artefactToolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.ArtefactToolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorArtefactToolkitShowService implements AbstractShowService<Inventor, ArtefactToolkit> {
	
	@Autowired
	protected InventorArtefactToolkitRepository repo;
	
	
	@Override
	public boolean authorise(final Request<ArtefactToolkit> request) {
		assert request != null;

		boolean result;
		int id;
		id = request.getModel().getInteger("id");

		final ArtefactToolkit artefactToolkit;
		artefactToolkit = this.repo.findArtefactToolkitById(id);

		result = request.isPrincipal(artefactToolkit.getToolkit().getInventor());

		return result;
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
        model.setAttribute("draftMode", entity.getToolkit().isDraftMode());
		
	}

}
