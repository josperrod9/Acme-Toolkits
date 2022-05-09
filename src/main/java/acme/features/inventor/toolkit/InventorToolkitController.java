package acme.features.inventor.toolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkits.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorToolkitController extends AbstractController<Inventor, Toolkit>{
	
	@Autowired
    protected InventorToolkitListService listService;

	@Autowired
    protected InventorToolkitCreateService createService;

	@Autowired
    protected InventorToolkitUpdateService updateService;

	@Autowired
    protected InventorToolkitDeleteService deleteService;

	@Autowired
    protected InventorToolkitPublishService publishService;

    @Autowired
    protected InventorToolkitShowService showService;

    // Constructors -----------------------------------------------------------

    @PostConstruct
    protected void initialise() {
        super.addCommand("list", this.listService);
        super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
        super.addCommand("update", this.updateService);
        super.addCommand("delete", this.deleteService);
        
        
        super.addCommand("publish", "update", this.publishService);
    }

}
