<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.artefact.form.label.name" path="name" readonly="true"/>
	<acme:input-textbox code="inventor.artefact.form.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="inventor.artefact.form.label.type" path="type" readonly="true"/>
	<acme:input-textbox code="inventor.artefact.form.label.technology" path="technology" readonly="true"/>
	<acme:input-textbox code="inventor.artefact.form.label.description" path="description" readonly="true"/>
	<acme:input-textbox code="inventor.artefact.form.label.retailPrice" path="retailPrice" readonly="true"/>
	<acme:input-url code="inventor.artefact.form.label.info" path="info" readonly="true"/>
</acme:form>