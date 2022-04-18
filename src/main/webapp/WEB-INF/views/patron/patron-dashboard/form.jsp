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
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h2>
	<acme:message code="patron.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm" id=list>
	<jstl:forEach items="${totalNumberOfPatronagesGroupedByStatus}" var="entry">
		<tr>
			<jstl:if test="${entry.key == 'PROPOSED'}">
				<td><acme:message code="patron.dashboard.form.label.numPatronageProposed" /></td>
				<th><acme:print value="${entry.value}" /></th>
			</jstl:if> 
		</tr>
		<tr>
			<jstl:if test="${entry.key == 'DENIED'}">
				<td><acme:message code="patron.dashboard.form.label.numPatronageDenied" /></td>
				<th><acme:print value="${entry.value}" /></th>
			</jstl:if>
		</tr>
		<tr> 
			<jstl:if test="${entry.key == 'ACCEPTED'}">
					<td><acme:message code="patron.dashboard.form.label.numPatronageAccepted" /></td>
					<th><acme:print value="${entry.value}" /></th>
				</jstl:if>
		</tr>
	</jstl:forEach>
</table>
<h3>
	<acme:message code="patron.dashboard.form.title.application-average"/>
</h3>

<table class="table table-sm">
	<jstl:forEach items="${averageBudgetOfPatronagesGroupedByCurrencyAndStatus}" var="entry">
		<tr>
			<td>
				<jstl:set var="pair" value="${entry.key}"/> 
				<jstl:set var="pairSplited" value="${fn:split(pair, '->')}"/> 
				<acme:message code="patron.patron-dashboard.form.label.currency"/>
				<acme:print value="${pairSplited[1]}"/> 
				<br/> 
				<acme:message code="patron.patron-dashboard.form.label.status"/>
				<acme:print value="${pairSplited[0]}"/> 
			</td>
			<th>
				<acme:print value="${entry.value}"/>
			</th>
		</tr>
	</jstl:forEach>
</table>

<br/>

<h3>
	<acme:message code="patron.dashboard.form.title.application-deviation"/>
</h3>

<table class="table table-sm">
	<jstl:forEach items="${deviationBudgetOfPatronagesGroupedByCurrencyAndStatus}" var="entry">
		<tr>
			<td>
				<jstl:set var="pair" value="${entry.key}"/> 
				<jstl:set var="pairSplited" value="${fn:split(pair, '->')}"/> 
				<acme:message code="patron.patron-dashboard.form.label.currency"/>
				<acme:print value="${pairSplited[1]}"/>  
				<br/>
				<acme:message code="patron.patron-dashboard.form.label.status"/>
				<acme:print value="${pairSplited[0]}"/> 
			</td>
			<th>
				<acme:print value="${entry.value}"/>
			</th>
		</tr>
	</jstl:forEach>
</table>

<br/>

<h3>
	<acme:message code="patron.dashboard.form.title.application-max"/>
</h3>

<table class="table table-sm">
	<jstl:forEach items="${maximumBudgetOfPatronagesGroupedByCurrencyAndStatus}" var="entry">
		<tr>
			<td>
				<jstl:set var="pair" value="${entry.key}"/> 
				<jstl:set var="pairSplited" value="${fn:split(pair, '->')}"/> 
				<acme:message code="patron.patron-dashboard.form.label.currency"/>
				<acme:print value="${pairSplited[1]}"/>  
				<br/>
				<acme:message code="patron.patron-dashboard.form.label.status"/>
				<acme:print value="${pairSplited[0]}"/> 
			</td>
			<th>
				<acme:print value="${entry.value}"/>
			</th>
		</tr>
	</jstl:forEach>
</table>

<br/>

<h3>
	<acme:message code="patron.dashboard.form.title.application-min"/>
</h3>

<table class="table table-sm">
	<jstl:forEach items="${minimumBudgetOfPatronagesGroupedByCurrencyAndStatus}" var="entry">
		<tr>
			<td>
				<jstl:set var="pair" value="${entry.key}"/> 
				<jstl:set var="pairSplited" value="${fn:split(pair, '->')}"/> 
				<acme:message code="patron.patron-dashboard.form.label.currency"/>
				<acme:print value="${pairSplited[1]}"/>
				<br/>  
				<acme:message code="patron.patron-dashboard.form.label.status"/>
				<acme:print value="${pairSplited[0]}"/> 
			</td>
			<th>
				<acme:print value="${entry.value}"/>
			</th>
		</tr>
	</jstl:forEach>
	</table>
<acme:return/>
