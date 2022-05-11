package acme.features.administrator.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorConfigurationUpdateService implements AbstractUpdateService<Administrator, Configuration>{

	@Autowired
	protected AdministratorConfigurationRepository repository;

	@Override
	public boolean authorise(final Request<Configuration> request) {
		assert request != null;
		return true;
	}
	
	@Override
	public void bind(final Request<Configuration> request, final Configuration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "currency", "defaultCurrency", "strongSpamTerm", "strongSpamThreshold", "weakSpamTerm", "weakSpamThreshold");

	}

	@Override
	public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model!=null;
		request.unbind(entity, model, "currency", "defaultCurrency", "strongSpamTerm", "strongSpamThreshold", "weakSpamTerm", "weakSpamThreshold");
	}

	@Override
	public Configuration findOne(final Request<Configuration> request) {
		Configuration result;

		result=this.repository.findSystemConfiguration();
		return result;
	}

	@Override
	public void validate(final Request<Configuration> request, final Configuration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if(!errors.hasErrors("defaultCurrency")) {
        	String acceptedCurrencies;
            String[] currencies;
            acceptedCurrencies = this.repository.findSystemConfiguration().getCurrency();
            currencies = acceptedCurrencies.split(",");
            boolean isCurrency = false;
            String c;
            c = entity.defaultCurrency;
            for (final String currency : currencies) {
            	if (c.equals(currency)) {
            		isCurrency = true;
            	}
			}
        	errors.state(request, isCurrency, "defaultCurrency", "administrator.configuration.form.error.incorrect-currency");
        }
	}

	@Override
	public void update(final Request<Configuration> request, final Configuration entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}