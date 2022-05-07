package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.ArtefactToolkit;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository {
	
	@Query("select t from Toolkit t where t.published = true")
	Collection<Toolkit> findAllToolkits();
	
	@Query("select t from Toolkit t where t.id = :id and t.published = true")
	Toolkit findOneToolkitById(int id);
	
	@Query("select a from ArtefactToolkit a where a.toolkit.id=:id")
	Collection<ArtefactToolkit> findArtefactToolkitByToolKit(int id);
	
	@Query("select c.defaultCurrency  from Configuration c")
	String getDefaultCurrency();
	
	

}