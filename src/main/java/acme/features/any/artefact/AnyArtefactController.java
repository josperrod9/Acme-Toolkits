package acme.features.any.artefact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkits.Artefact;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyArtefactController extends AbstractController<Any, Artefact>{
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	public AnyArtefactListService listService;
	
	@Autowired
	public AnyArtefactShowService showService;
	
	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}

}
