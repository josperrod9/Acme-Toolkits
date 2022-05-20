package acme.features.administrator.announcement;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;

import acme.entities.announcements.*;
import acme.entities.configuration.Configuration;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorAnnouncementRepository extends AbstractRepository{

	@Query("select a from Announcement a where a.id = :id")
	Announcement findOneAnnouncementById(int id);

	@Query("select a from Announcement a")
	Collection<Announcement> findAllAnnouncements();

	@Query("select a from Announcement a where a.creationMoment > :deadline")
	Collection<Announcement> findRecentAnnouncements(Date deadline);
	
	@Query("select c from Configuration c")
	Configuration findConfig();
}