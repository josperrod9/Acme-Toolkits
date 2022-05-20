package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface InventorPatronageRepository extends AbstractRepository{
	
	@Query("SELECT p FROM Patronage p WHERE p.inventor.id = :id and p.notPublished=false")
	Collection<Patronage> findAllPatronages(int id);

	@Query("SELECT p FROM Patronage p WHERE p.id =:id")
	Patronage findPatronageById(int id);

	@Query("SELECT p FROM Patronage p WHERE p.code =:code")
	Patronage findPatronageByCode(String code);
	@Query("select c.defaultCurrency  from Configuration c")
	String getDefaultCurrency();

}
