package acme.features.any.artefact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyArtefactShowService implements AbstractShowService<Any, Artefact>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AnyArtefactRepository repository;
	@Autowired
	protected AnyArtefactMoneyExchange anyArtefactMoneyExchange;
	
	
	// AbstractShowService<Any, Artefact> interface --------------
	
	
	@Override
	public boolean authorise(final Request<Artefact> request) {
		assert request != null;
		return true;
	}

	@Override
	public Artefact findOne(final Request<Artefact> request) {
		assert request != null;
		Artefact result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findArtefactById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final String systemCurrency= this.repository.getDefaultCurrency();
		final Money priceExchanged=this.anyArtefactMoneyExchange.computeMoneyExchange(entity.getRetailPrice(), systemCurrency).getTarget();
		model.setAttribute("money", priceExchanged);
		request.unbind(entity, model, "name","code","type","technology","description","retailPrice","info");
		
	}

}
