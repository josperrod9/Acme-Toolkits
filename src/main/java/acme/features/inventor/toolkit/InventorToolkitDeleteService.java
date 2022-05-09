package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.ArtefactToolkit;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorToolkitDeleteService implements AbstractDeleteService<Inventor, Toolkit> {

	@Autowired
	protected InventorToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result;
		int toolkitId;
		Toolkit toolkit;
		Inventor inventor;

		toolkitId = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(toolkitId);
		inventor = toolkit.getInventor();
		result = toolkit.isDraftMode() && request.isPrincipal(inventor);

		return result;
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
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findToolkitById(id);

		return result;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;		
	}

	@Override
	public void delete(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		Collection<ArtefactToolkit> artefactToolkits;

		artefactToolkits = this.repository.findManyArtefactToolkitByToolkitId(entity.getId());
		for (final ArtefactToolkit artefactToolkit : artefactToolkits) {
			this.repository.delete(artefactToolkit);
		}
		this.repository.delete(entity);
		
	}

}
