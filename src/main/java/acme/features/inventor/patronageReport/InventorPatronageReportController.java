package acme.features.inventor.patronageReport;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronages.PatronageReport;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorPatronageReportController extends AbstractController<Inventor, PatronageReport>{
	
	@Autowired
    protected InventorPatronageReportListService listService;

    @Autowired
    protected InventorPatronageReportShowService showService;

    @Autowired
    protected InventorPatronageReportCreateService createService;

    // Constructors -----------------------------------------------------------

    @PostConstruct
    protected void initialise() {
        super.addCommand("list", this.listService);
        super.addCommand("create", this.createService);
        super.addCommand("show", this.showService);
    }

}
