<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.patronageReport.form.label.memorandum" path="memorandum" readonly="true"/>
	<acme:input-textbox code="inventor.patronageReport.form.label.automaticSequenceNumber" path="automaticSequenceNumber" readonly="true"/>
	<acme:input-textbox code="inventor.patronageReport.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:input-url code="inventor.patronageReport.form.label.info" path="info" readonly="true"/>
</acme:form>