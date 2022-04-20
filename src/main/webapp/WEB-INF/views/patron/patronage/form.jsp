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
	<acme:input-select path="status" code="patron.patronage.list.label.status">
                                <acme:input-option code="patron.patronage.show.label.status.proposed" value="PROPOSED" selected="true"/>
                                <acme:input-option code="patron.patronage.show.label.status.accepted" value="ACCEPTED"/>
                                <acme:input-option code="patron.patronage.show.label.status.denied" value="DENIED"/>
                </acme:input-select> 
    </jstl:if> 
    <jstl:if test="${status == 'ACCEPTED'}">
	<acme:input-select path="status" code="patron.patronage.list.label.status">
                                <acme:input-option code="patron.patronage.show.label.status.proposed" value="PROPOSED" />
                                <acme:input-option code="patron.patronage.show.label.status.accepted" value="ACCEPTED" selected="true"/>
                                <acme:input-option code="patron.patronage.show.label.status.denied" value="DENIED"/>
                </acme:input-select> 
    </jstl:if>  
    <jstl:if test="${status == 'DENIED'}">
	<acme:input-select path="status" code="patron.patronage.list.label.status">
                                <acme:input-option code="patron.patronage.show.label.status.proposed" value="PROPOSED"/>
                                <acme:input-option code="patron.patronage.show.label.status.accepted" value="ACCEPTED" />
                                <acme:input-option code="patron.patronage.show.label.status.denied" value="DENIED" selected="true"/>
                </acme:input-select> 
    </jstl:if>   
	<acme:input-textbox code="patron.patronage.show.label.code" path="code" />
	<acme:input-textbox code="patron.patronage.show.label.legalStuff" path="legalStuff" />
	<acme:input-money code="patron.patronage.show.label.budget" path="budget" />
	<acme:input-textbox code="patron.patronage.show.label.creationDate" path="creationDate" />
	<acme:input-textbox code="patron.patronage.show.label.startDate" path="startDate" />
	<acme:input-textbox code="patron.patronage.show.label.endDate" path="endDate" />
	<acme:input-url code="patron.patronage.show.label.info" path="info" />
	<acme:input-textbox code="patron.patronage.show.label.inventor.username" path="username" />
	<acme:input-textbox code="patron.patronage.show.label.inventor.company" path="company" />
	<acme:input-textbox code="patron.patronage.show.label.inventor.statement" path="statement" />
	<acme:input-textbox code="patron.patronage.show.label.inventor.inventorInfo" path="inventorInfo" />
	
	<acme:button code="patron.patronage.form.button.patronage-reports" action="/patron/patronage-report/list?masterId=${id}"/>
</acme:form>