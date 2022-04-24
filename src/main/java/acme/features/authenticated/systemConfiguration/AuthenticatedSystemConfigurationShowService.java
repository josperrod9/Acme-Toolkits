package acme.features.authenticated.systemConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedSystemConfigurationShowService implements AbstractShowService<Authenticated, Configuration> {
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository repo;
	
	
	@Override
	public boolean authorise(final Request<Configuration> request) {
		assert request != null;
		return request.getPrincipal().isAuthenticated();
	}

	@Override
	public Configuration findOne(final Request<Configuration> request) {
		assert request != null;
		
		
		Collection<Configuration> configuration;
		
		configuration = this.repo.findAuthenticatedSystemConfigurations();
		
		final List<Configuration> conf = new ArrayList<Configuration>(configuration);
		
		return conf.get(0);
	}

	@Override
	public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "defaultCurrency", "currency", "id");
		
	}

}
