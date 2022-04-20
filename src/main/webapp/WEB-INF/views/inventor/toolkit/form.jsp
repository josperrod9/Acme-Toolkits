<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.toolkit.form.label.title" path="title" readonly="true"/>
	<acme:input-textbox code="inventor.toolkit.form.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="inventor.toolkit.form.label.description" path="description" readonly="true"/>
	<acme:input-textbox code="inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes" readonly="true"/>
	<acme:input-url code="inventor.toolkit.form.label.info" path="info" readonly="true"/>
</acme:form>