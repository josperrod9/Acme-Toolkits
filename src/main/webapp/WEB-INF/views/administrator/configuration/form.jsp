<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="administrator.configuration.form.label.default-currency" path="defaultCurrency"/>
	<acme:input-textarea code="administrator.configuration.form.label.currency" path="currency"/>
	<acme:input-textarea code="administrator.configuration.form.label.strong-spam-terms" path="strongSpamTerm"/>
	<acme:input-double code="administrator.configuration.form.label.strong-spam-threshold" path="strongSpamThreshold"/>
	<acme:input-textarea code="administrator.configuration.form.label.weak-spam-terms" path="weakSpamTerm"/>
	<acme:input-double code="administrator.configuration.form.label.weak-spam-threshold" path="weakSpamThreshold"/>
	
	<jstl:choose>	 
		<jstl:when test="${command == 'update'}">
			<acme:submit code="administrator.configuration.form.button.update" action="/administrator/configuration/update"/>
		</jstl:when>	
		<jstl:when test="${command == 'show'}">
			<acme:button code="administrator.configuration.form.button.update1" action="/administrator/configuration/update"/>
		</jstl:when>
	</jstl:choose>
</acme:form>