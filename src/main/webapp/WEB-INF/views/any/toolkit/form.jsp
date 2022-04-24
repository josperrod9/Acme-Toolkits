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
	<acme:input-textbox code="any.toolkit.form.label.title" path="title" readonly="true"/>
	<acme:input-textbox code="any.toolkit.form.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="any.toolkit.form.label.description" path="description" readonly="true"/>
	<acme:input-textbox code="any.toolkit.form.label.assemblyNotes" path="assemblyNotes" readonly="true"/>
	<acme:input-textbox code="any.toolkit.form.label.info" path="info" readonly="true"/>
	<acme:input-textbox code="any.toolkit.form.label.price" path="price" readonly="true"/>
	<acme:button code="any.toolkit.form.button.artefacts" action="/any/artefact/list?masterId=${id}"/>
</acme:form>
