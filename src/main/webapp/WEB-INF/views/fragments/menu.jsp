<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.roles.Patron,acme.roles.Inventor"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
	
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.user-accounts"
				action="/any/user-account/list" />
			<acme:menu-suboption code="master.menu.anonymous.toolkit"
				action="/any/toolkit/list" />
			<acme:menu-suboption code="master.menu.anonymous.chirp"
				action="/any/chirp/list" />
			<acme:menu-suboption code="master.menu.anonymous.artefact"
				action="/any/artefact/list" />
		
			
			<acme:menu-suboption code="master.menu.anonymous.mario" action="http://www.facebook.com/"/>
			
            <acme:menu-suboption code="master.menu.anonymous.andreu" action="http://www.twitter.com/"/>
            <acme:menu-suboption code="master.menu.anonymous.alberto" action="http://www.twitch.com/"/>
            <acme:menu-suboption code="master.menu.anonymous.pepe" action="http://www.instagram.com/"/>
            <acme:menu-suboption code="master.menu.anonymous.pablob" action="http://www.google.com/"/>
            <acme:menu-suboption code="master.menu.anonymous.pablog" action="http://www.yahoo.com/"/>
        </acme:menu-option>

		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.toolkit"
				action="/any/toolkit/list" />
			<acme:menu-suboption code="master.menu.authenticated.user-accounts"
				action="/any/user-account/list" />
			<acme:menu-suboption code="master.menu.authenticated.chirp"
				action="/any/chirp/list" />
			<acme:menu-suboption code="master.menu.authenticated.artefact"
				action="/any/artefact/list" />
			<acme:menu-suboption code="master.menu.authenticated.announcement" action="/authenticated/announcement/list"/>
			<acme:menu-suboption code="master.menu.authenticated.configuration.list" action="/authenticated/configuration/show"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
		
					<acme:menu-suboption code="master.menu.administrator.toolkit"
				action="/any/toolkit/list" />
			<acme:menu-suboption code="master.menu.administrator.chirp"
				action="/any/chirp/list" />
			<acme:menu-suboption code="master.menu.administrator.artefact"
				action="/any/artefact/list" />
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
			<acme:menu-suboption code="master.menu.administrator.configuration" action="/administrator/configuration/show"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/administrator-dashboard/show"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.patron.patronages" action="/patron/patronage/list"/>
			<acme:menu-suboption code="master.menu.patron.dashboard" action="/patron/patron-dashboard/show"/>
			<acme:menu-suboption code="master.menu.patron.user-accounts"
				action="/any/user-account/list" />
			<acme:menu-suboption code="master.menu.patron.toolkit"
				action="/any/toolkit/list" />
			<acme:menu-suboption code="master.menu.patron.chirp"
				action="/any/chirp/list" />
			<acme:menu-suboption code="master.menu.patron.artefact"
				action="/any/artefact/list" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.inventor" access="hasRole('Inventor')">
			<acme:menu-suboption code="master.menu.inventor.user-accounts"
				action="/any/user-account/list" />
					<acme:menu-suboption code="master.menu.inventor.toolkit"
				action="/any/toolkit/list" />
			<acme:menu-suboption code="master.menu.inventor.chirp"
				action="/any/chirp/list" />
			<acme:menu-suboption code="master.menu.inventor.artefact"
				action="/any/artefact/list" />
			<acme:menu-suboption code="master.menu.inventor.artefact.list" action="/inventor/artefact/list"/>
			<acme:menu-suboption code="master.menu.inventor.toolkit.list" action="/inventor/toolkit/list"/>
			<acme:menu-suboption code="master.menu.inventor.patronageReport.list" action="/inventor/patronage-report/list"/>
			<acme:menu-suboption code="master.menu.inventor.patronage.list" action="/inventor/patronage/list"/>
		</acme:menu-option>

	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create" access="!hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.patron" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-inventor" action="/authenticated/inventor/create" access="!hasRole('Inventor')"/>
			<acme:menu-suboption code="master.menu.user-account.inventor" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

