package acme.features.inventor.artefactToolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkits.ArtefactToolkit;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorArtefactToolkitController extends AbstractController<Inventor, ArtefactToolkit>{
	
	@Autowired
    protected InventorArtefactToolkitListService listService;

    @Autowired
    protected InventorArtefactToolkitShowService showService;
    
    @Autowired
    protected InventorArtefactToolkitCreateService createService;
    
    @Autowired
    protected InventorArtefactToolkitUpdateService updateService;
    
    @Autowired
    protected InventorArtefactToolkitDeleteService deleteService;
    // Constructors -----------------------------------------------------------

    @PostConstruct
    protected void initialise() {
        super.addCommand("list", this.listService);
        super.addCommand("show", this.showService);
        super.addCommand("create", this.createService);
        super.addCommand("update", this.updateService);
        super.addCommand("delete", this.deleteService);
    }

}
