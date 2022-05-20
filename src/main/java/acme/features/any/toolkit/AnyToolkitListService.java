
package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.ArtefactToolkit;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyToolkitListService implements AbstractListService<Any, Toolkit>{
	
	// Internal state -------------------------------------------------------------------
	
	@Autowired
	protected AnyToolkitRepository repository;
	
	@Autowired
	protected AnyToolkitMoneyExchange anyToolkitMoneyExchange;
	
	// AbstractListService<Authenticated, Toolkit> interface ----------------------------
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		return true;
	}
	

	

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;
		
		Collection<Toolkit> result;
		
		result = this.repository.findAllToolkits();
		
		return result;
	}

	@Override
	public void unbind( final Request<Toolkit> request,  final Toolkit entity,  final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final String systemCurrency= this.repository.getDefaultCurrency();
		double price=0.;
		Money moneyInternational;
		final Collection<ArtefactToolkit> artefactToolkits = this.repository.findArtefactToolkitByToolKit(entity.getId());
		
		for(final ArtefactToolkit a :artefactToolkits) {
		final Money artefactPrice=a.getArtefact().getRetailPrice();
		
			final Money priceExchanged=this.anyToolkitMoneyExchange.computeMoneyExchange(artefactPrice, systemCurrency).getTarget();
			price += a.getAmount()*priceExchanged.getAmount();
		}
		
		moneyInternational=new Money();
		moneyInternational.setAmount(price);
		moneyInternational.setCurrency(systemCurrency);
		
		model.setAttribute("money", moneyInternational);
		
		request.unbind(entity, model,"code", "title");
	}
	

}