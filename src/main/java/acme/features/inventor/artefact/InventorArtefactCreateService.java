package acme.features.inventor.artefact;

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
		protected InventorArtefactRepository repository;

		// AbstractCreateService<Administrator, Announcement> interface --------------


		@Override
		public boolean authorise(final Request<Artefact> request) {
			
			assert request != null;
			return true;
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

			request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "type", "info","published");
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
				errors.state(request, existing == null, "code", "inventor.artefact.form.error.duplicated");
			}
			if(!errors.hasErrors("retailPrice")) {
	        	String acceptedCurrencies;
	            String[] currencies;
	            acceptedCurrencies = this.repository.findConfiguration().getCurrency();
	            currencies = acceptedCurrencies.split(",");
	            boolean isCurrency = false;
	            String c;
	            c = entity.getRetailPrice().getCurrency();
	            for (final String currency : currencies) {
	            	if (c.equals(currency)) {
	            		isCurrency = true;
	            	}
				}
	            Double amount;
	            amount=entity.getRetailPrice().getAmount();
	        	errors.state(request, isCurrency, "retailPrice", "inventor.artefact.form.error.incorrect-currency");
	        	errors.state(request, amount>0, "retailPrice", "inventor.artefact.form.error.negative-budget");
	        }
		}

		@Override
		public void create(final Request<Artefact> request, final Artefact entity) {
			assert request != null;
			assert entity != null;


			this.repository.save(entity);
		}

}