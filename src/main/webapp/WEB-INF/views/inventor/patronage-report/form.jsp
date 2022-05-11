<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.patronageReport.form.label.memorandum" path="memorandum"/>
	<acme:input-url code="inventor.patronageReport.form.label.info" path="info"/>

	<jstl:if test="${command == 'show'}">
		<acme:input-textbox code="inventor.patronageReport.form.label.automaticSequenceNumber" path="automaticSequenceNumber"/>
		<acme:input-textbox code="inventor.patronageReport.form.label.creationMoment" path="creationMoment"/>
	</jstl:if>

	<jstl:if test="${command == 'create'}">
		<acme:hidden-data path="automaticSequenceNumber"/>
		<acme:hidden-data path="creationMoment"/>
		<acme:input-checkbox code="inventor.patronageReport.form.label.confirmation" path="confirmation"/>
		<acme:submit code="inventor.patronageReport.form.button.create" action="/inventor/patronage-report/create?masterId=${masterId}"/>
	</jstl:if>
	
</acme:form>
