<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.patronage.form.label.status" path="status" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.legalStuff" path="legalStuff" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.budget" path="budget" readonly="true"/>
	<acme:input-url code="inventor.patronage.form.label.info" path="info" readonly="true"/>
</acme:form>