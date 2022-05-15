<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form> 
   	<acme:input-textbox code="inventor.patronage.form.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.legalStuff" path="legalStuff" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.budget" path="budget" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.creationDate" path="creationDate" readonly="true"/>
	<acme:input-url code="inventor.patronage.form.label.info" path="info" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.money" path="money" readonly="true"/>
	
	<jstl:if test="${status != 'PROPOSED'}">
		<acme:input-textbox code="inventor.patronage.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${status == 'PROPOSED'}">
		<acme:input-select path="status" code="inventor.patronage.form.label.new-status">
			<acme:input-option code="PROPOSED" value="PROPOSED" selected="true"/>
			<acme:input-option code="ACCEPTED" value="ACCEPTED"/>
			<acme:input-option code="DENIED" value="DENIED"/>
		</acme:input-select>		
	</jstl:if>
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">		
	    	<acme:input-textbox code="patron.patronage.show.label.patron.username" path="username"/>
	    </jstl:when>
	    
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">
	    	<acme:input-textbox code="patron.patronage.show.label.patron.username" path="patron.userAccount.username" readonly="${true}"/>
	    	<acme:input-textbox code="patron.patronage.show.label.patron.company" path="patron.company" readonly="${true}"/>
	    	<acme:input-textbox code="patron.patronage.show.label.patron.statement" path="patron.statement" readonly="${true}"/>
		</jstl:when>	
	
	</jstl:choose>
	<acme:button code="inventor.patronage.form.button.createPatronageReport" action="/inventor/patronage-report/create?masterId=${id}"/>
	<acme:submit test="${acme:anyOf(command, 'show, update') && status == 'PROPOSED'}" code="inventor.patronage.form.button.update" action="/inventor/patronage/update"/>	
</acme:form>