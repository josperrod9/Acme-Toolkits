/*
 * AdministratorUserAccountListService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.any.userAccount;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Administrator;
import acme.framework.roles.Anonymous;
import acme.framework.roles.Any;
import acme.framework.roles.UserRole;
import acme.framework.services.AbstractListService;

@Service
public class AnyUserAccountListService implements AbstractListService<Any, UserAccount> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyUserAccountRepository repository;

	// AbstractListService<Any, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public Collection<UserAccount> findMany(final Request<UserAccount> request) {
		assert request != null;
		final Collection<UserAccount> result = new ArrayList<UserAccount>();

		final Collection<UserAccount> allUserAccount = this.repository.findAllUserAccounts();

		for(final UserAccount userAccount : allUserAccount ) {


			if(!userAccount.hasRole(Anonymous.class) && !userAccount.hasRole(Administrator.class) && userAccount.isEnabled()){

				result.add(userAccount);

			}

		}
		return result;
	}
	
	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		StringBuilder buffer;
		Collection<UserRole> roles;

		request.unbind(entity, model, "username", "identity.name", "identity.surname", "identity.email");

		roles = entity.getRoles();
		buffer = new StringBuilder();
		for (final UserRole role : roles) {
			buffer.append(role.getAuthorityName());
			buffer.append(" ");
		}

		model.setAttribute("roleList", buffer.toString());
	}

}
