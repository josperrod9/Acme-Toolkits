package acme.features.authenticated.announcements;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.announcements.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAnnouncementRepository extends AbstractRepository{
	
	@Query("select a from Announcement a where a.creationMoment > :deadline")
	Collection<Announcement> findAnnouncementByDate(Date deadline);
	
	@Query("select a from Announcement a where a.id = :id and a.creationMoment > :deadline")
	Announcement findAnnouncementById(int id, Date deadline);
}
