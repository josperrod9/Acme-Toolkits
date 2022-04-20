package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface InventorToolkitRepository extends AbstractRepository{
	
	@Query("SELECT t FROM Toolkit t WHERE t.inventor.id = :id")
	Collection<Toolkit> findAllInventorToolkits(int id);

	@Query("SELECT t FROM Toolkit t WHERE t.id =:id")
	Toolkit findToolkitById(int id);
	

}
