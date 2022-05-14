package acme.features.inventor.artefactToolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.Artefact;
import acme.entities.toolkits.ArtefactToolkit;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface InventorArtefactToolkitRepository extends AbstractRepository{
	@Query("select a from Artefact a")
	Collection<Artefact> findAllArtefacts();

	@Query("select a from Artefact a where a.id =:id")
	Artefact findArtefactById(int id);

	@Query("select a.artefact from ArtefactToolkit a where a.toolkit.id = :id")
	Collection<Artefact> findManyArtefactsByMasterId(int id);

	@Query("select t from Toolkit t where t.id=:id")
	Toolkit findToolkitById(int id);

	@Query("select at from ArtefactToolkit at where at.id=:id")
	ArtefactToolkit findArtefactToolkitById(int id);

	@Query("select at from ArtefactToolkit at where at.toolkit.id=:id")
	Collection<ArtefactToolkit> findManyArtefactToolkitByToolkitId(int id);

	@Query("select a from Artefact a where a.code =:code")
	Artefact findArtefactByCode(String code);
	
	
}
