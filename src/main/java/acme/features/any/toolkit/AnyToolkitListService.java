
package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Artefact;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyToolkitListService implements AbstractListService<Any, Toolkit>{
	
	// Internal state -------------------------------------------------------------------
	
	@Autowired
	protected AnyToolkitRepository repository;
	
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
		
		final String payload;
		
		request.unbind(entity, model,"code", "title");
		
		final StringBuilder allArtefactsName = new StringBuilder();
		final StringBuilder allArtefactsCode = new StringBuilder();
		
		final Collection<Artefact> artefacts = this.repository.findArtefactByToolkit(entity.getId());
		
		int size = artefacts.size(); 
		
		for(final Artefact a: artefacts) {
			allArtefactsName.append(a.getName());
			allArtefactsCode.append(a.getCode());
			
			if(size > 1) {
				allArtefactsName.append(",");
				allArtefactsCode.append(",");
				size--;
			}	
		}
		
		payload = String.format(//
			"%s; %s", //
			allArtefactsName.toString(),allArtefactsCode.toString());
		model.setAttribute("payload", payload);
	}
	

}