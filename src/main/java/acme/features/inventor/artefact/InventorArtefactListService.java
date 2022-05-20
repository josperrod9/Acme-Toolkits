package acme.features.inventor.artefact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorArtefactListService implements AbstractListService<Inventor,Artefact> {
	
	
	@Autowired
	protected InventorArtefactRepository repo;
	@Autowired
	protected InventorArtefactMoneyExchange InventorArtefactMoneyExchange;
	
	@Override
	public boolean authorise( final Request<Artefact> request) {
		assert request != null;
		return true;
	}
	
	
	@Override
    public Collection<Artefact> findMany(final Request<Artefact> request) {
        final Principal principal = request.getPrincipal();
		int masterId;
			if(request.getModel().hasAttribute("masterId")) {
				  masterId = request.getModel().getInteger("masterId");
				 
				return this.repo.findManyArtefactsByMasterId(masterId);
			}
			else {
				return this.repo.findAllInventorArtefacts(principal.getActiveRoleId());
			}
					
				

    }

	
    @Override
    public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;
        
        final String systemCurrency= this.repo.getDefaultCurrency();
		final Money priceExchanged=this.InventorArtefactMoneyExchange.computeMoneyExchange(entity.getRetailPrice(), systemCurrency).getTarget();
		model.setAttribute("money", priceExchanged);
        request.unbind(entity, model, "name","code","retailPrice","type","id");
    }
}
