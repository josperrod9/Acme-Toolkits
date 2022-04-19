package acme.features.authenticated.systemConfiguration;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.Configuration;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AuthenticatedSystemConfigurationRepository extends AbstractRepository{
	
	@Query("SELECT c FROM Configuration c")
	Collection<Configuration> findAuthenticatedSystemConfigurations();

}
