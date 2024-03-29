<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>
	<jstl:choose>
    	<jstl:when test="${command == 'create'}">	
    		<acme:input-textbox code="inventor.toolkit.form.label.code" path="code"/>
    	</jstl:when>
    	<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">
    		<acme:input-textbox code="inventor.toolkit.form.label.code" path="code" readonly="${true}"/>
    			<acme:input-textbox code="inventor.toolkit.form.label.price" path="money" readonly="true"/>
    	</jstl:when>
    </jstl:choose>
	<acme:input-textbox code="inventor.toolkit.form.label.description" path="description"/>
	<acme:input-textbox code="inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
	<acme:input-url code="inventor.toolkit.form.label.info" path="info"/>
	<jstl:choose>	 
		<jstl:when test="${command == 'show' && draftMode == false}">
			<acme:button code="inventor.toolkit.form.button.artefacts" action="/inventor/artefact-toolkit/list?masterId=${id}"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && draftMode == true}">
			<acme:button code="inventor.toolkit.form.button.artefacts" action="/inventor/artefact-toolkit/list?masterId=${id}&?draftMode=${draftMode}"/>
			<acme:submit code="inventor.toolkit.form.button.update" action="/inventor/toolkit/update"/>
			<acme:submit code="inventor.toolkit.form.button.delete" action="/inventor/toolkit/delete"/>
			<acme:submit code="inventor.toolkit.form.button.publish" action="/inventor/toolkit/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.toolkit.form.button.create" action="/inventor/toolkit/create"/>
		</jstl:when>		
	</jstl:choose>


</acme:form>