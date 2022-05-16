package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.Configuration;
import acme.entities.toolkits.ArtefactToolkit;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;


@Repository
public interface InventorToolkitRepository extends AbstractRepository{
	
	@Query("SELECT t FROM Toolkit t WHERE t.inventor.id = :id")
	Collection<Toolkit> findAllInventorToolkits(int id);

	@Query("SELECT t FROM Toolkit t WHERE t.id =:id")
	Toolkit findToolkitById(int id);

	@Query("SELECT t FROM Toolkit t WHERE t.code =:code")
	Toolkit findToolkitByCode(String code);
	
	@Query("SELECT i FROM Inventor i WHERE i.id =:id")
	Inventor findInventorById(int id);

	@Query("SELECT at FROM ArtefactToolkit at WHERE at.toolkit.id=:id")
	Collection<ArtefactToolkit> findManyArtefactToolkitByToolkitId(int id);

	@Query("select c from Configuration c")
	Configuration findConfig();
}
