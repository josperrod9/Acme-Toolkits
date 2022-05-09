<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.artefact.form.label.name" path="name" />
	<acme:input-textbox code="inventor.artefact.form.label.code" path="code"/>
		
		<jstl:if test="${type == 'COMPONENT'}">
			<acme:input-select path="type" code="inventor.artefact.form.label.type">
                                <acme:input-option code="inventor.artefact.form.label.type.component" value="COMPONENT" selected="true"/>
                </acme:input-select> 
    </jstl:if> 
    <jstl:if test="${type == 'TOOL'}">
			<acme:input-select path="type" code="inventor.artefact.form.label.type">
                                <acme:input-option code="inventor.artefact.form.label.type.tool" value="TOOL" selected="true"/>
                </acme:input-select> 
    </jstl:if>  
	
	<acme:input-textbox code="inventor.artefact.form.label.technology" path="technology" />
	<acme:input-textbox code="inventor.artefact.form.label.description" path="description" />
	<acme:input-textbox code="inventor.artefact.form.label.retailPrice" path="retailPrice"/>
	<acme:input-url code="inventor.artefact.form.label.info" path="info" />
	
<jstl:choose>	 
	<jstl:when test="${acme:anyOf(command, 'update,show') && published == false}">
			<acme:submit code="inventor.artefact.form.button.update" action="/inventor/artefact/update"/>
			
		</jstl:when>
		<jstl:when test="${command == 'create'}">
				<acme:input-select path="type" code="inventor.artefact.form.label.type">
                                <acme:input-option code="inventor.artefact.form.label.type.component" value="COMPONENT" />
                                <acme:input-option code="inventor.artefact.form.label.type.tool" value="TOOL" />
                </acme:input-select> 
			<acme:submit code="inventor.artefact.form.button.create" action="/inventor/artefact/create"/>
		</jstl:when>		
	</jstl:choose>

	
	

</acme:form>