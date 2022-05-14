package acme.features.inventor.artefactToolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.ArtefactToolkit;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorArtefactToolkitListService implements AbstractListService<Inventor,ArtefactToolkit> {
	
	
	@Autowired
	protected InventorArtefactToolkitRepository repo;
	
	
	@Override
	public boolean authorise( final Request<ArtefactToolkit> request) {
		assert request!=null;
		boolean result;
		int id;
		id = request.getModel().getInteger("masterId");

		Toolkit toolkit;
		toolkit = this.repo.findToolkitById(id);

		result = request.isPrincipal(toolkit.getInventor());

		return result;
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
        model.setAttribute("draftMode", true);
    }
}
