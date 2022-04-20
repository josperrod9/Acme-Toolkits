package acme.features.administrator.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorConfigurationShowService implements AbstractShowService<Administrator, Configuration>{

		// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorConfigurationRepository repository;

		// AbstractShowService<Administrator, SystemConfiguration> interface ----------------


		@Override
		public boolean authorise(final Request<Configuration> request) {
			assert request != null;

			return true;
		}

		@Override
		public Configuration findOne(final Request<Configuration> request) {
			assert request != null;

			return this.repository.findSystemConfiguration();
			
		

		}

		@Override
		public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "currency", "defaultCurrency", "strongSpamTerm", "strongSpamThreshold", "weakSpamTerm", "weakSpamThreshold");
		}
}
