package acme.features.inventor.artefact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkits.Artefact;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorArtefactController extends AbstractController<Inventor, Artefact>{
	
	@Autowired
    protected InventorArtefactListService listService;

    @Autowired
    protected InventorArtefactShowService showService;
    
    @Autowired
    protected InventorArtefactCreateService createService;
    
    @Autowired
    protected InventorArtefactUpdateService updateService;

    @Autowired
    protected InventorArtefactPublishService publishService;
    
    @Autowired
    protected InventorArtefactDeleteService deleteService;
    // Constructors -----------------------------------------------------------

    @PostConstruct
    protected void initialise() {
        super.addCommand("list", this.listService);
        super.addCommand("show", this.showService);
        super.addCommand("create", this.createService);
        super.addCommand("update", this.updateService);
        super.addCommand("publish","update",this.publishService);
        super.addCommand("delete", this.deleteService);
    }

}
