package acme.features.any.chirp;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chirps.Chirp;
import acme.entities.configuration.Configuration;
import acme.features.SpamDetector;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp> {

	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnyChirpRepository repository;

		// AbstractCreateService<Administrator, Announcement> interface --------------


		@Override
		public boolean authorise(final Request<Chirp> request) {
			assert request != null;

			return true;
		}

		@Override
		public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors, "title", "author", "body", "email");
		}

		@Override
		public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "creationMoment", "title", "author", "body", "email");
			model.setAttribute("confirmation", false);
			model.setAttribute("readonly", false);
		}

		@Override
		public Chirp instantiate(final Request<Chirp> request) {
			assert request != null;

			Chirp result;
			Date moment;

			moment = new Date(System.currentTimeMillis() - 1);

			result = new Chirp();
			result.setTitle("");
			result.setCreationMoment(moment);
			result.setBody("");
			result.setAuthor("");
			result.setEmail("");

			return result;
		}

		@Override
		public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			boolean confirmation;
			SpamDetector spamDetector;
			String strongSpamTerms;
			String weakSpamTerms;
			double strongSpamThreshold;
			double weakSpamThreshold;

			spamDetector = new SpamDetector();
			Configuration configuration = this.repository.findConfig();
			strongSpamTerms = configuration.getStrongSpamTerm();
			weakSpamTerms = configuration.getWeakSpamTerm();
			strongSpamThreshold = configuration.getStrongSpamThreshold();
			weakSpamThreshold = configuration.getWeakSpamThreshold();

			if(!errors.hasErrors("title")) {
				errors.state(request, !spamDetector.containsSpam(weakSpamTerms.split(","), weakSpamThreshold, entity.getTitle())
					&& !spamDetector.containsSpam(strongSpamTerms.split(","), strongSpamThreshold, entity.getTitle()),
					"title", "any.chirp.form.error.spam");
			}

			if(!errors.hasErrors("author")) {
				errors.state(request, !spamDetector.containsSpam(weakSpamTerms.split(","), weakSpamThreshold, entity.getAuthor())
					&& !spamDetector.containsSpam(strongSpamTerms.split(","), strongSpamThreshold, entity.getAuthor()),
					"author", "any.chirp.form.error.spam");
			}

			if(!errors.hasErrors("body")) {
				errors.state(request, !spamDetector.containsSpam(weakSpamTerms.split(","), weakSpamThreshold, entity.getBody())
					&& !spamDetector.containsSpam(strongSpamTerms.split(","), strongSpamThreshold, entity.getBody()),
					"body", "any.chirp.form.error.spam");
			}


			confirmation = request.getModel().getBoolean("confirmation");
			errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		}

		@Override
		public void create(final Request<Chirp> request, final Chirp entity) {
			assert request != null;
			assert entity != null;

			Date moment;

			moment = new Date(System.currentTimeMillis() - 1);
			entity.setCreationMoment(moment);

			this.repository.save(entity);
		}

}
