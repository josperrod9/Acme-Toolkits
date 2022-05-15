package acme.features.any.artefact;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.Artefact;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyArtefactRepository extends AbstractRepository{

	@Query("select a from Artefact a where a.published = true")
	Collection<Artefact> findAllArtefacts();
	@Query("select a from Artefact a where a.id =:id and a.published = true")
	Artefact findArtefactById(int id);
	@Query("select a.artefact from ArtefactToolkit a where a.toolkit.id = :masterId and a.artefact.published = true")
    Collection<Artefact> findManyArtifactsByMasterId(int masterId);
	@Query("select c.defaultCurrency  from Configuration c")
	String getDefaultCurrency();
}
