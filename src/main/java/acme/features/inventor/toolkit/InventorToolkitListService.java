package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.ArtefactToolkit;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorToolkitListService implements AbstractListService<Inventor,Toolkit> {
	
	
	@Autowired
	protected InventorToolkitRepository repo;
	
	@Autowired
	protected InventorToolkitMoneyExchange inventorToolkitMoneyExchange;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		return true;
	}
	
	
	@Override
    public Collection<Toolkit> findMany(final Request<Toolkit> request) {
        final Principal principal = request.getPrincipal();
        return this.repo.findAllInventorToolkits(principal.getActiveRoleId());
    }

    @Override
    public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;
        
        final String systemCurrency= this.repo.getDefaultCurrency();
		double price=0.;
		Money moneyInternational;
		final Collection<ArtefactToolkit> artefactToolkits = this.repo.findManyArtefactToolkitByToolkitId(entity.getId());
		
		for(final ArtefactToolkit a :artefactToolkits) {
		final Money artefactPrice=a.getArtefact().getRetailPrice();
		
			final Money priceExchanged=this.inventorToolkitMoneyExchange.computeMoneyExchange(artefactPrice, systemCurrency).getTarget();
			price += a.getAmount()*priceExchanged.getAmount();
		}
		
		moneyInternational=new Money();
		moneyInternational.setAmount(price);
		moneyInternational.setCurrency(systemCurrency);
		
		model.setAttribute("money", moneyInternational);
        request.unbind(entity, model, "title", "code", "description");
    }
}
