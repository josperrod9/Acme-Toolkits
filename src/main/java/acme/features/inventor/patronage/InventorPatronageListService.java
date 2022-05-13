package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorPatronageListService implements AbstractListService<Inventor,Patronage> {
	
	
	@Autowired
	protected InventorPatronageRepository repo;
	@Autowired
	protected InventorPatronageMoneyExchange inventorPatronageMoneyExchange;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		return true;
	}
	
	
	@Override
    public Collection<Patronage> findMany(final Request<Patronage> request) {
        final Principal principal = request.getPrincipal();
        return this.repo.findAllPatronages(principal.getActiveRoleId());
    }

    @Override
    public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;
        
        final String systemCurrency= this.repo.getDefaultCurrency();
      		final Money priceExchanged=this.inventorPatronageMoneyExchange.computeMoneyExchange(entity.getBudget(), systemCurrency).getTarget();
      		model.setAttribute("money", priceExchanged);

        request.unbind(entity, model,"code","legalStuff","budget","id");
    }
}
