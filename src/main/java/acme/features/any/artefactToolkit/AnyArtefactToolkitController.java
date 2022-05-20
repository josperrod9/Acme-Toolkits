package acme.features.any.artefactToolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkits.ArtefactToolkit;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyArtefactToolkitController extends AbstractController<Any, ArtefactToolkit>{
	
	@Autowired
    protected AnyArtefactToolkitListService listService;

    @Autowired
    protected AnyArtefactToolkitShowService showService;
    // Constructors -----------------------------------------------------------

    @PostConstruct
    protected void initialise() {
        super.addCommand("list", this.listService);
        super.addCommand("show", this.showService);
    }

}
