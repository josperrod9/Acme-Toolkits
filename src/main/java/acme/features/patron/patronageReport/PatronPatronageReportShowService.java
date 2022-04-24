package acme.features.patron.patronageReport;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportShowService implements AbstractShowService<Patron, PatronageReport> {
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected PatronPatronageReportRepository repository;
	
	// AbstractShowService<Inventor, PatronageReport> interface --------------
	
	
	@Override
    public boolean authorise(final Request<PatronageReport> request) {
        assert request != null;

        boolean result;
        int id;
        PatronageReport report;

        id = request.getModel().getInteger("id");
        report = this.repository.findOneById(id);
        result = (report != null && (request.isPrincipal(report.getPatronage().getPatron())));

        return result;
    }
	
	@Override
	public PatronageReport findOne(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
	
	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "memorandum", "automaticSequenceNumber", "creationMoment", "info");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
	}
}