<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.artefactToolkit.form.label.amount" path="amount" readonly="${draftMode == false}"/>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:input-textbox code="inventor.artefactToolkit.form.label.artefact.name" path="artefact.name" readonly="${true}"/>
			<acme:input-textbox code="inventor.artefactToolkit.form.label.artefact.type" path="artefact.type" readonly="${true}"/>
			<acme:input-textbox code="inventor.artefactToolkit.form.label.artefact.code" path="artefact.code" readonly="${true}"/>
			<jstl:if test="${draftMode == true}">
				<acme:submit code="inventor.artefactToolkit.form.button.update" action="/inventor/artefact-toolkit/update"/>
				<acme:submit code="inventor.artefactToolkit.form.button.delete" action="/inventor/artefact-toolkit/delete"/>			
			</jstl:if>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
				<acme:input-select path="artefacts" code="inventor.artefactToolkit.form.label.artefacts">
					<jstl:forEach items="${artefacts}" var="artefact">
                    	<acme:input-option code="${artefact.name} - ${artefact.type} - ${artefact.retailPrice} " value="${artefact.id}"/>
               		</jstl:forEach>
                </acme:input-select> 
			<acme:submit code="inventor.artefactToolkit.form.button.create" action="/inventor/artefact-toolkit/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>