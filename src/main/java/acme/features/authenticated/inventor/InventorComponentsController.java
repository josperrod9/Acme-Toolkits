package acme.features.authenticated.inventor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkits.Artefact;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorComponentsController extends AbstractController<Inventor, Artefact>{
	
	@Autowired
    protected InventorComponentsListService listService;

    @Autowired
    protected InventorComponentsShowService showService;

    // Constructors -----------------------------------------------------------

    @PostConstruct
    protected void initialise() {
        super.addCommand("list", this.listService);
        super.addCommand("show", this.showService);
    }

}
