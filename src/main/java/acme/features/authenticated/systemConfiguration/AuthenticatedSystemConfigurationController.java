package acme.features.authenticated.systemConfiguration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.configuration.Configuration;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class AuthenticatedSystemConfigurationController extends AbstractController<Authenticated, Configuration>{
	
    @Autowired
    protected AuthenticatedSystemConfigurationShowService showService;

    // Constructors -----------------------------------------------------------

    @PostConstruct
    protected void initialise() {
    	super.addCommand("show", this.showService);
    }

}
