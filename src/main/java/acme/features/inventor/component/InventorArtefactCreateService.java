package acme.features.inventor.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorArtefactCreateService implements AbstractCreateService<Inventor, Artefact> {

	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorComponentRepository repository;

		// AbstractCreateService<Administrator, Announcement> interface --------------


		@Override
		public boolean authorise(final Request<Artefact> request) {
			boolean result;
			Inventor inventor;
			
			inventor = this.repository.findInventorById(request.getPrincipal().getActiveRoleId());
			
			result = request.isPrincipal(inventor);
			
			return result;
		}

		@Override
		public void bind(final Request<Artefact> request, final Artefact entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "type", "info");
		}

		@Override
		public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "type", "info");
			model.setAttribute("confirmation", false);
			model.setAttribute("readonly", false);
		}

		@Override
		public Artefact instantiate(final Request<Artefact> request) {
			assert request != null;

			Artefact result;
			Inventor inventor;
			
			
			inventor = this.repository.findInventorById(request.getPrincipal().getActiveRoleId());
			result = new Artefact();
			result.setName("");
			result.setCode("");
			result.setTechnology("");
			result.setDescription("");
			result.setRetailPrice(null);
			result.setType(null);
			result.setInfo("");
			result.setInventor(inventor);
			result.setPublished(false);
			return result;
		}

		@Override
		public void validate(final Request<Artefact> request, final Artefact entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			if(!errors.hasErrors("code")) {
				Artefact existing;
				
				existing = this.repository.findArtefactByCode(entity.getCode());
				errors.state(request, existing == null, "code", "inventor.toolkit.form.error.duplicated");
			}
		}

		@Override
		public void create(final Request<Artefact> request, final Artefact entity) {
			assert request != null;
			assert entity != null;


			this.repository.save(entity);
		}

}