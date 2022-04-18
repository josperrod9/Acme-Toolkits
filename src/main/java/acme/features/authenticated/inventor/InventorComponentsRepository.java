package acme.features.authenticated.inventor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.Artefact;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface InventorComponentsRepository extends AbstractRepository{
	@Query("select a from Artefact a WHERE a.inventor.id= :id AND a.type = 1")
	Collection<Artefact> findAllInventorComponents(int id);

	@Query("select a from Artefact a where a.id =:id")
	Artefact findArtefactById(int id);
	

}
