<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>	
	<acme:list-column code="inventor.toolkit.list.label.title" path="title" width="20%"/>
	<acme:list-column code="inventor.toolkit.list.label.code" path="code" width="30%"/>
	<acme:list-column code="inventor.toolkit.list.label.description" path="description" width="50%"/>	
</acme:list>

<acme:button code="inventor.toolkit.list.button.create" action="/inventor/toolkit/create"/>
