package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportListService implements AbstractListService<Inventor,PatronageReport> {
	
	
	@Autowired
	protected InventorPatronageReportRepository repo;
	
	
	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		return request.getPrincipal().isAuthenticated();
	}
	
	
	@Override
    public Collection<PatronageReport> findMany(final Request<PatronageReport> request) {
        final Principal principal = request.getPrincipal();
        return this.repo.findAllInventorPatronageReports(principal.getActiveRoleId());
    }

    @Override
    public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "memorandum", "creationMoment", "info","id");
    }
}
