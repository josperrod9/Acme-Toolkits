<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>	
	<acme:list-column code="inventor.artefact.list.label.name" path="name" width="20%"/>
	<acme:list-column code="inventor.artefact.list.label.code" path="code" width="30%"/>
	<acme:list-column code="inventor.artefact.list.label.retailPrice" path="retailPrice" width="50%"/>	
	<acme:list-column code="inventor.artefact.list.label.type" path="type" width="50%"/>	
</acme:list>
