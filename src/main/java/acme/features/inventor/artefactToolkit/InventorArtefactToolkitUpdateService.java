package acme.features.inventor.artefactToolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Artefact;
import acme.entities.toolkits.ArtefactToolkit;
import acme.entities.toolkits.ArtefactType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorArtefactToolkitUpdateService implements AbstractUpdateService<Inventor,ArtefactToolkit>{

    @Autowired
    protected InventorArtefactToolkitRepository repository;

    @Override
    public boolean authorise(final Request<ArtefactToolkit> request) {
    	assert request != null;

		boolean result;
		int id;
		id = request.getModel().getInteger("id");

		final ArtefactToolkit artefactToolkit;
		artefactToolkit = this.repository.findArtefactToolkitById(id);

		result = request.isPrincipal(artefactToolkit.getToolkit().getInventor());

		return result;
    }

    @Override
    public void bind(final Request<ArtefactToolkit> request, final ArtefactToolkit entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;

        request.bind(entity, errors,"amount");

    }

    @Override
    public void unbind(final Request<ArtefactToolkit> request, final ArtefactToolkit entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model!=null;
        request.unbind(entity, model,"amount","artefact.name","artefact.type","artefact.code");
        model.setAttribute("draftMode", entity.getToolkit().isDraftMode());
    }

    @Override
    public ArtefactToolkit findOne(final Request<ArtefactToolkit> request) {
    	ArtefactToolkit result;
        final int id =request.getModel().getInteger("id");

        result=this.repository.findArtefactToolkitById(id);
        return result;
    }

    @Override
    public void validate(final Request<ArtefactToolkit> request, final ArtefactToolkit entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;
        if(!errors.hasErrors("amount")) {
			Artefact artefact;
			artefact = this.repository.findArtefactByCode(request.getModel().getString("artefact.code"));
			errors.state(request, !(entity.getAmount()!=1&&artefact.getType().equals(ArtefactType.TOOL)), "amount", "inventor.artefactToolkit.form.error.invalidAmount");
		}
    }

    @Override
    public void update(final Request<ArtefactToolkit> request, final ArtefactToolkit entity) {
        assert request != null;
        assert entity != null;
        
        this.repository.save(entity);

    }

}