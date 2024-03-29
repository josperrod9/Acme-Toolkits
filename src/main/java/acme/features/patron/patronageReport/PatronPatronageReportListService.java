package acme.features.patron.patronageReport;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportListService implements AbstractListService<Patron, PatronageReport> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageReportRepository repository;
	
	// AbstractListService<Patron, PatronageReport> interface --------------
	
	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public Collection<PatronageReport> findMany(final Request<PatronageReport> request) {
		assert request != null;

		Collection<PatronageReport> result;

		final int masterId = request.getModel().getInteger("masterId");
		result = this.repository.findManyPatronagesReportByPatronageId(masterId);

		return result;
	}
	
	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("masterId", entity.getPatronage().getCode());

		request.unbind(entity, model, "creationMoment");
	}
}