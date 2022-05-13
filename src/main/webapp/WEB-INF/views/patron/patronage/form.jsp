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
	
	<jstl:if test="${status == 'PROPOSED'}">
	<acme:input-textbox code="patron.patronage.show.label.status" path="status" readonly="true"/>
    </jstl:if> 
    <jstl:if test="${status == 'ACCEPTED'}">
	<acme:input-textbox code="patron.patronage.show.label.status" path="status" readonly="true"/>
    </jstl:if>  
    <jstl:if test="${status == 'DENIED'}">
	<acme:input-textbox code="patron.patronage.show.label.status" path="status" readonly="true"/> 
    </jstl:if>

	<jstl:choose>
    	<jstl:when test="${command == 'create'}">	
    		<acme:input-textbox code="patron.patronage.show.label.code" path="code"/>
    	</jstl:when>
    	<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">
    		<acme:input-textbox code="patron.patronage.show.label.code" path="code" readonly="${true}"/>
    	</jstl:when>
    </jstl:choose>
    <acme:input-textarea code="patron.patronage.show.label.legalStuff" path="legalStuff"/>
    <acme:input-money code="patron.patronage.show.label.budget" path="budget"/>
        <acme:input-money code="patron.patronage.show.label.money" path="money"/>
    
    <jstl:choose>
    	<jstl:when test="${command == 'create'}">		
	    	<acme:input-select code="patron.patronage.show.label.inventor.username" path="username">
	   			<jstl:forEach items="${inventors}" var="inventor">
					<acme:input-option code="${inventor}" value="${inventor}"/>
				</jstl:forEach>
			</acme:input-select>
			
	    </jstl:when>
	    <jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">
	    	<acme:input-textbox code="patron.patronage.show.label.inventor.username" path="inventor.userAccount.username" readonly="${true}"/>
	    	<acme:input-textbox code="patron.patronage.show.label.inventor.company" path="inventor.company" readonly="${true}"/>
	    	<acme:input-textbox code="patron.patronage.show.label.inventor.statement" path="inventor.statement" readonly="${true}"/>
	    	<acme:input-textbox code="patron.patronage.show.label.inventor.inventorInfo" path="inventor.inventorInfo" readonly="${true}"/>
		 
	    </jstl:when>
    </jstl:choose>
    <acme:input-moment code="patron.patronage.show.label.startDate" path="startDate"/>
    <acme:input-moment code="patron.patronage.show.label.endDate" path="endDate"/>
    <acme:input-url code="patron.patronage.show.label.info" path="info"/>

    <jstl:choose>
		<jstl:when test="${command == 'show' && notPublished == false}">
			<acme:button code="patron.patronage.form.button.patronage-reports"
				action="/patron/patronage-report/list?masterId=${param.id}" />
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && notPublished == true}">
    		<acme:submit code="patron.patronage.show.button.update" action="/patron/patronage/update?id=${param.id}"/>
    		<acme:submit code="patron.patronage.show.button.delete" action="/patron/patronage/delete?id=${param.id}"/>
    		<acme:submit code="patron.patronage.show.button.publish" action="/patron/patronage/publish?id=${param.id}"/>
    	</jstl:when>
    	<jstl:when test="${command == 'create'}">
    		<acme:submit code="patron.patronage.show.button.create" action="/patron/patronage/create"/>
    	</jstl:when>
    </jstl:choose>
</acme:form>