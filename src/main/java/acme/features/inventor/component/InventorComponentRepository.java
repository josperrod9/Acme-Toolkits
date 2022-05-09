package acme.features.inventor.component;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.Artefact;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;


@Repository
public interface InventorComponentRepository extends AbstractRepository{
	@Query("select a from Artefact a WHERE a.inventor.id= :id")
	Collection<Artefact> findAllInventorArtefacts(int id);


	@Query("select a from Artefact a where a.id =:id")
	Artefact findArtefactById(int id);
	
	@Query("SELECT i FROM Inventor i WHERE i.id =:id")
	Inventor findInventorById(int id);
	
	@Query("SELECT t FROM Artefact t WHERE t.code =:code")
	Artefact findArtefactByCode(String code);
}
