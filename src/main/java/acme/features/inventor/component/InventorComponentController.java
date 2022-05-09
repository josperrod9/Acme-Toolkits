package acme.features.inventor.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkits.Artefact;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorComponentController extends AbstractController<Inventor, Artefact>{
	
	@Autowired
    protected InventorComponentListService listService;

    @Autowired
    protected InventorComponentShowService showService;
    
    @Autowired
    protected InventorArtefactCreateService createService;
    
    @Autowired
    protected InventorArtefactUpdateService updateService;

    // Constructors -----------------------------------------------------------

    @PostConstruct
    protected void initialise() {
        super.addCommand("list", this.listService);
        super.addCommand("show", this.showService);
        super.addCommand("create", this.createService);
        super.addCommand("update", this.updateService);
    }

}
