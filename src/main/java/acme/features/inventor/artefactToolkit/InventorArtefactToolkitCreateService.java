package acme.features.inventor.artefactToolkit;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Artefact;
import acme.entities.toolkits.ArtefactToolkit;
import acme.entities.toolkits.ArtefactType;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorArtefactToolkitCreateService implements AbstractCreateService<Inventor, ArtefactToolkit> {

	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorArtefactToolkitRepository repository;

		// AbstractCreateService<Administrator, Announcement> interface --------------


		@Override
		public boolean authorise(final Request<ArtefactToolkit> request) {
			
			boolean result;
			int id;
			id = request.getModel().getInteger("masterId");

			Toolkit toolkit;
			toolkit = this.repository.findToolkitById(id);

			result = request.isPrincipal(toolkit.getInventor());

			return result;
		}

		@Override
		public void bind(final Request<ArtefactToolkit> request, final ArtefactToolkit entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors, "amount");
			int artefactId;
			artefactId = request.getModel().getInteger("artefacts");
	        
	        Artefact artefact;
	        artefact = this.repository.findArtefactById(artefactId);
	        entity.setArtefact(artefact);
		}

		@Override
		public void unbind(final Request<ArtefactToolkit> request, final ArtefactToolkit entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			int masterId;
			masterId =  request.getModel().getInteger("masterId");
			
			Collection<Artefact> artefactsInToolKit;
			artefactsInToolKit = this.repository.findManyArtefactsByMasterId(masterId);
			
			request.unbind(entity, model,"amount","toolkit");
			model.setAttribute("artefacts", this.repository.findAllArtefacts().stream().filter(x->!artefactsInToolKit.contains(x)).collect(Collectors.toList()));
			model.setAttribute("masterId",masterId);
		}

		@Override
		public ArtefactToolkit instantiate(final Request<ArtefactToolkit> request) {
			assert request != null;

			ArtefactToolkit result;
			Toolkit toolkit;
			
			
			toolkit = this.repository.findToolkitById(request.getModel().getInteger("masterId"));
			result = new ArtefactToolkit();
			result.setToolkit(toolkit);
			return result;
		}

		@Override
		public void validate(final Request<ArtefactToolkit> request, final ArtefactToolkit entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			if(!errors.hasErrors("amount")) {
				Artefact artefact;
				artefact = this.repository.findArtefactById(request.getModel().getInteger("artefacts"));
				errors.state(request, !(entity.getAmount()!=1&&artefact.getType().equals(ArtefactType.TOOL)), "amount", "inventor.artefactToolkit.form.error.invalidAmount");
			}
		}

		@Override
		public void create(final Request<ArtefactToolkit> request, final ArtefactToolkit entity) {
			assert request != null;
			assert entity != null;
			this.repository.save(entity);
		}

}