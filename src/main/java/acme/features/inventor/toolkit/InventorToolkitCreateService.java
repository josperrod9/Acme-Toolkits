package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.toolkits.Toolkit;
import acme.features.SpamDetector;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitCreateService implements AbstractCreateService<Inventor, Toolkit>{

	@Autowired
	protected InventorToolkitRepository repo;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		return true;

	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "info");
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "info", "draftMode");
		
		
	}

	@Override
	public Toolkit instantiate(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		Inventor inventor;
		
		inventor = this.repo.findInventorById(request.getPrincipal().getActiveRoleId());
		result = new Toolkit();
		result.setDraftMode(true);
		result.setInventor(inventor);

		return result;

	
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		SpamDetector spamDetector;
		String strongSpamTerms;
		String weakSpamTerms;
		double strongSpamThreshold;
		double weakSpamThreshold;

		spamDetector = new SpamDetector();
		Configuration configuration = this.repo.findConfig();
		strongSpamTerms = configuration.getStrongSpamTerm();
		weakSpamTerms = configuration.getWeakSpamTerm();
		strongSpamThreshold = configuration.getStrongSpamThreshold();
		weakSpamThreshold = configuration.getWeakSpamThreshold();

		if(!errors.hasErrors("title")) {
			errors.state(request, !spamDetector.containsSpam(weakSpamTerms.split(","), weakSpamThreshold, entity.getTitle())
				&& !spamDetector.containsSpam(strongSpamTerms.split(","), strongSpamThreshold, entity.getTitle()),
				"title", "inventor.toolkit.form.error.spam");
		}

		if(!errors.hasErrors("description")) {
			errors.state(request, !spamDetector.containsSpam(weakSpamTerms.split(","), weakSpamThreshold, entity.getDescription())
				&& !spamDetector.containsSpam(strongSpamTerms.split(","), strongSpamThreshold, entity.getDescription()),
				"description", "inventor.toolkit.form.error.spam");
		}

		if(!errors.hasErrors("assemblyNotes")) {
			errors.state(request, !spamDetector.containsSpam(weakSpamTerms.split(","), weakSpamThreshold, entity.getAssemblyNotes())
				&& !spamDetector.containsSpam(strongSpamTerms.split(","), strongSpamThreshold, entity.getAssemblyNotes()),
				"assemblyNotes", "inventor.toolkit.form.error.spam");
		}
		
		if(!errors.hasErrors("code")) {
			Toolkit existing;
			
			existing = this.repo.findToolkitByCode(entity.getCode());
			errors.state(request, existing == null, "code", "inventor.toolkit.form.error.duplicated");
		}
	}

	@Override
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		this.repo.save(entity);
	}

}
