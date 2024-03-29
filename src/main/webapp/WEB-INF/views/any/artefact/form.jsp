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
	<acme:input-textbox code="any.artefact.form.label.name" path="name" readonly="true"/>
	<acme:input-textbox code="any.artefact.form.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="any.artefact.form.label.technology" path="technology" readonly="true"/>
	<acme:input-textbox code="any.artefact.form.label.description" path="description" readonly="true"/>
	<acme:input-money code="any.artefact.form.label.retailPrice" path="retailPrice" readonly="true"/>
	<acme:input-url code="any.artefact.form.label.info" path="info" readonly="true"/>
	<acme:input-money code="any.artefact.form.label.money" path="money" readonly="true"/>
	
		<jstl:if test="${type == 'COMPONENT'}">
			<acme:input-select path="type" code="any.artefact.form.label.type">
                                <acme:input-option code="any.artefact.form.label.type.component" value="COMPONENT" selected="true"/>
                                <acme:input-option code="any.artefact.form.label.type.tool" value="TOOL"/>
                </acme:input-select> 
    </jstl:if> 
    <jstl:if test="${type == 'TOOL'}">
			<acme:input-select path="type" code="any.artefact.form.label.type">
                                <acme:input-option code="any.artefact.form.label.type.component" value="COMPONENT" />
                                <acme:input-option code="any.artefact.form.label.type.tool" value="TOOL" selected="true"/>
                </acme:input-select> 
    </jstl:if>  
	
	
</acme:form>
