<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.patron.form.label.company" path="company"/>
	<acme:input-textbox code="authenticated.patron.form.label.statement" path="statement"/>
	<acme:input-textbox code="authenticated.patron.form.label.info" path="info"/>
	
	<acme:submit test="${command == 'create'}" code="authenticated.patron.form.button.create" action="/authenticated/patron/create"/>
</acme:form>
