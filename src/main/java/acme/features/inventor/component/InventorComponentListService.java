package acme.features.inventor.component;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorComponentListService implements AbstractListService<Inventor,Artefact> {
	
	
	@Autowired
	protected InventorComponentRepository repo;
	
	
	@Override
	public boolean authorise( final Request<Artefact> request) {
		assert request != null;
		return true;
	}
	
	
	@Override
    public Collection<Artefact> findMany(final Request<Artefact> request) {
        final Principal principal = request.getPrincipal();
		
			return this.repo.findAllInventorArtefacts(principal.getActiveRoleId());
    }

	
    @Override
    public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "name","code","retailPrice","type");
    }
}
