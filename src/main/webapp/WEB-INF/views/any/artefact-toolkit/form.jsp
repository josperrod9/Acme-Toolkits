<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.artefactToolkit.form.label.amount" path="amount" readonly="${true}"/>
	<acme:input-textbox code="any.artefactToolkit.form.label.artefact.name" path="artefact.name" readonly="${true}"/>
	<acme:input-textbox code="any.artefactToolkit.form.label.artefact.type" path="artefact.type" readonly="${true}"/>
	<acme:input-textbox code="any.artefactToolkit.form.label.artefact.code" path="artefact.code" readonly="${true}"/>
</acme:form>