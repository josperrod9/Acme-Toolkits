package acme.features.inventor.artefact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorArtefactPublishService implements AbstractUpdateService<Inventor, Artefact>{
	 @Autowired
	    protected InventorArtefactRepository repository;

	    @Override
	    public boolean authorise(final Request<Artefact> request) {
	         assert request != null;

	            boolean result;
	            int id;
	            id = request.getModel().getInteger("id");

	            Artefact artefact;
	            artefact = this.repository.findArtefactById(id);

	            result = !artefact.isPublished() && request.isPrincipal(artefact.getInventor());

	            return result;
	    }

	    @Override
	    public void bind(final Request<Artefact> request, final Artefact entity, final Errors errors) {
	        assert request != null;
	        assert entity != null;
	        assert errors != null;

	        request.bind(entity, errors, "name", "code", "type", "technology", "description","retailPrice","info");

	    }

	    @Override
	    public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
	        assert request != null;
	        assert entity != null;
	        assert model!=null;
	        request.unbind(entity, model, "name", "code", "type", "technology", "description","retailPrice","info","published");
	    }

	    @Override
	    public Artefact findOne(final Request<Artefact> request) {
	        Artefact result;
	        final int id =request.getModel().getInteger("id");

	        result=this.repository.findArtefactById(id);
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
				errors.state(request, existing == null|| existing.getId() == entity.getId(), "code", "inventor.artefact.form.error.duplicated");
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
	    public void update(final Request<Artefact> request, final Artefact entity) {
	        assert request != null;
	        assert entity != null;
	        
	        entity.setPublished(true);
	        this.repository.save(entity);

	    }

	}