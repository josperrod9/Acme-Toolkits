package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorToolkitListService implements AbstractListService<Inventor,Toolkit> {
	
	
	@Autowired
	protected InventorToolkitRepository repo;
	
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		return request.getPrincipal().isAuthenticated();
	}
	
	
	@Override
    public Collection<Toolkit> findMany(final Request<Toolkit> request) {
        final Principal principal = request.getPrincipal();
        return this.repo.findAllInventorToolkits(principal.getActiveRoleId());
    }

    @Override
    public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "title", "code", "description");
    }
}
