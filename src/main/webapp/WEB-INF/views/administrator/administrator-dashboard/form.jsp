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
	<acme:message
		code="administrator.dashboard.form.title.general-indicators" />
</h2>

<table class="table table-sm">
    <caption>Administrator Dashboard</caption>
	<tr>
		<td><acme:message
				code="administrator.administrator-dashboard.form.label.total-number-of-tools" />
		</td>
		<th id="blank"></th>
		<th id="tools"><acme:print value="${totalNumberOfTools}" /></th>
	</tr>
	<jstl:forEach items="${totalNumberofPatronagesGroupedByStatus}"
		var="entry">
		<tr>
			<jstl:if test="${entry.key == 'PROPOSED'}">
				<td><acme:message
						code="administrator.administrator-dashboard.form.label.numPatronageProposed" /></td>
				<th id="blank"></th>
				<th id="value"><acme:print value="${entry.value}" /></th>
			</jstl:if>
		</tr>
		<tr>
			<jstl:if test="${entry.key == 'DENIED'}">
				<td><acme:message
						code="administrator.administrator-dashboard.form.label.numPatronageDenied" /></td>
				<th id="blank"></th>
				<th id="value"><acme:print value="${entry.value}" /></th>
			</jstl:if>
		</tr>
		<tr>
			<jstl:if test="${entry.key == 'ACCEPTED'}">
				<td><acme:message
						code="administrator.administrator-dashboard.form.label.numPatronageAccepted" /></td>
				<th id="blank"></th>
				<th id="value"><acme:print value="${entry.value}" /></th>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>
<h2>
	<acme:message code="administrator.dashboard.form.title.components" />
</h2>
<table class="table table-sm">
<caption>Administrator Dashboard</caption>
	<tr>
		<th id="value"><acme:message
				code="administrator.administrator-dashboard.form.title.measure" />
		</th>
		<th id="value"><acme:message
				code="administrator.administrator-dashboard.form.title.groupby" />
		</th>
		<th id="value"><acme:message
				code="administrator.administrator-dashboard.form.title.value" /></th>
	</tr>
	<jstl:forEach
		items="${averageRetailPriceOfComponentsGroupedByTechnologyAndCurrency}"
		var="entry">
		<tr>
			<td><acme:message
					code="administrator.dashboard.form.title.components.average" /></td>
			<td><jstl:set var="pair" value="${entry.key}" /> <jstl:set
					var="pairSplited" value="${fn:split(pair, '->')}" /> <acme:message
					code="administrator.administrator-dashboard.form.label.currency" />
				<acme:print value="${pairSplited[1]}" /> <br /> <acme:message
					code="administrator.administrator-dashboard.form.label.technology" />
				<acme:print value="${pairSplited[0]}" /></td>
			<th id="value"><acme:print value="${entry.value}" /></th>
		</tr>
	</jstl:forEach>
	<jstl:forEach
		items="${deviationRetailPriceOfComponentsGroupedByTechnologyAndCurrency}"
		var="entry">
		<tr>
			<td><acme:message
					code="administrator.dashboard.form.title.components.deviation" /></td>
			<td><jstl:set var="pair" value="${entry.key}" /> <jstl:set
					var="pairSplited" value="${fn:split(pair, '->')}" /> <acme:message
					code="administrator.administrator-dashboard.form.label.currency" />
				<acme:print value="${pairSplited[1]}" /> <br /> <acme:message
					code="administrator.administrator-dashboard.form.label.technology" />
				<acme:print value="${pairSplited[0]}" /></td>
			<th id="value"><acme:print value="${entry.value}" /></th>
		</tr>
	</jstl:forEach>

	<jstl:forEach
		items="${minimumRetailPriceOfComponentsGroupedByTechnologyAndCurrency}"
		var="entry">

		<tr>
			<td><acme:message
					code="administrator.dashboard.form.title.components.minimum" /></td>
			<td><jstl:set var="pair" value="${entry.key}" /> <jstl:set
					var="pairSplited" value="${fn:split(pair, '->')}" /> <acme:message
					code="administrator.administrator-dashboard.form.label.currency" />
				<acme:print value="${pairSplited[1]}" /> <br /> <acme:message
					code="administrator.administrator-dashboard.form.label.technology" />
				<acme:print value="${pairSplited[0]}" /></td>
			<th id="value"><acme:print value="${entry.value}" /></th>
		</tr>
	</jstl:forEach>
	<jstl:forEach
		items="${maximumRetailPriceOfComponentsGroupedByTechnologyAndCurrency}"
		var="entry">
		<tr>
			<td><acme:message
					code="administrator.dashboard.form.title.components.maximum" /></td>
			<td><jstl:set var="pair" value="${entry.key}" /> <jstl:set
					var="pairSplited" value="${fn:split(pair, '->')}" /> <acme:message
					code="administrator.administrator-dashboard.form.label.currency" />
				<acme:print value="${pairSplited[1]}" /> <br /> <acme:message
					code="administrator.administrator-dashboard.form.label.technology" />
				<acme:print value="${pairSplited[0]}" /></td>
			<th id="value"><acme:print value="${entry.value}" /></th>
		</tr>
	</jstl:forEach>
</table>

<h2>
	<acme:message code="administrator.dashboard.form.title.tools" />
</h2>
<table class="table table-sm">
	<caption>Administrator Dashboard</caption>
	<tr>
		<th id ="measure"><acme:message
				code="administrator.administrator-dashboard.form.title.measure" />
		</th>
		<th id="groupby"><acme:message
				code="administrator.administrator-dashboard.form.title.groupby" />
		</th>
		<th id="value"><acme:message
				code="administrator.administrator-dashboard.form.title.value" /></th>
	</tr>
	<jstl:forEach items="${averageRetailPriceOfToolsGroupedByCurrency}"
		var="entry">
		<tr>
			<td><acme:message
					code="administrator.dashboard.form.title.components.average" /></td>
			<td><acme:print value="${entry.key}" /></td>
			<th id="value"><acme:print value="${entry.value}" /></th>
		</tr>
	</jstl:forEach>
	<jstl:forEach items="${deviationRetailPriceOfToolsGroupedByCurrency}"
		var="entry">
		<tr>
			<td><acme:message
					code="administrator.dashboard.form.title.components.deviation" /></td>
			<td><acme:print value="${entry.key}" /></td>
			<th id="value"><acme:print value="${entry.value}" /></th>
		</tr>
	</jstl:forEach>

	<jstl:forEach items="${minimumRetailPriceOfToolsGroupedByCurrency}"
		var="entry">
		<tr>
			<td><acme:message
					code="administrator.dashboard.form.title.components.minimum" /></td>
			<td><acme:print value="${entry.key}" /></td>
			<th id="value"><acme:print value="${entry.value}" /></th>
		</tr>
	</jstl:forEach>
	<jstl:forEach items="${maximumRetailPriceOfToolsGroupedByCurrency}"
		var="entry">
		<tr>
			<td><acme:message
					code="administrator.dashboard.form.title.components.maximum" /></td>
			<td><acme:print value="${entry.key}" /></td>
			<th id="value"><acme:print value="${entry.value}" /></th>
		</tr>
	</jstl:forEach>
</table>

<h2>
	<acme:message code="administrator.dashboard.form.title.patronages" />
</h2>
<table class="table table-sm">
	<caption>Administrator Dashboard</caption>
	<tr>
		<th id="measure"><acme:message
				code="administrator.administrator-dashboard.form.title.measure" />
		</th>
		<th id="value"></th>
		<th id="value"><acme:message
				code="administrator.administrator-dashboard.form.title.value" /></th>
	</tr>
	<jstl:forEach items="${averageOfPatronagesGroupedByStatus}" var="entry">
		<tr>
			<jstl:if test="${entry.key == 'PROPOSED'}">
				<td><acme:message
						code="administrator.administrator-dashboard.form.label.avgPatronageProposed" /></td>
				<th id="value"></th>
				<th id="value"><acme:print value="${entry.value}" /></th>
			</jstl:if>
		</tr>
		<tr>
			<jstl:if test="${entry.key == 'DENIED'}">
				<td><acme:message
						code="administrator.administrator-dashboard.form.label.avgPatronageDenied" /></td>
				<th id="blank"></th>
				<th id="value"><acme:print value="${entry.value}" /></th>
			</jstl:if>
		</tr>
		<tr>
			<jstl:if test="${entry.key == 'ACCEPTED'}">
				<td><acme:message
						code="administrator.administrator-dashboard.form.label.avgPatronageAccepted" /></td>
				<th id="blank"></th>
				<th id="value"><acme:print value="${entry.value}" /></th>
			</jstl:if>
		</tr>
	</jstl:forEach>
	<jstl:forEach items="${deviationOfPatronagesGroupedByStatus}"
		var="entry">
		<tr>
			<jstl:if test="${entry.key == 'PROPOSED'}">
				<td><acme:message
						code="administrator.administrator-dashboard.form.label.devPatronageProposed" /></td>
				<th id="blank"></th>
				<th id="value"><acme:print value="${entry.value}" /></th>
			</jstl:if>
		</tr>
		<tr>
			<jstl:if test="${entry.key == 'DENIED'}">
				<td><acme:message
						code="administrator.administrator-dashboard.form.label.devPatronageDenied" /></td>
				<th id="blank"></th>
				<th id="value"><acme:print value="${entry.value}" /></th>
			</jstl:if>
		</tr>
		<tr>
			<jstl:if test="${entry.key == 'ACCEPTED'}">
				<td><acme:message
						code="administrator.administrator-dashboard.form.label.devPatronageAccepted" /></td>
				<th id="blank"></th>
				<th id="value"><acme:print value="${entry.value}" /></th>
			</jstl:if>
		</tr>
	</jstl:forEach>

	<jstl:forEach items="${minimumOfPatronagesGroupedByStatus}" var="entry">
		<tr>
			<jstl:if test="${entry.key == 'PROPOSED'}">
				<td><acme:message
						code="administrator.administrator-dashboard.form.label.minPatronageProposed" /></td>
				<th id="blank"></th>
				<th id="value"><acme:print value="${entry.value}" /></th>
			</jstl:if>
		</tr>
		<tr>
			<jstl:if test="${entry.key == 'DENIED'}">
				<td><acme:message
						code="administrator.administrator-dashboard.form.label.minPatronageDenied" /></td>
				<th id="blank"></th>
				<th id="value"><acme:print value="${entry.value}" /></th>
			</jstl:if>
		</tr>
		<tr>
			<jstl:if test="${entry.key == 'ACCEPTED'}">
				<td><acme:message
						code="administrator.administrator-dashboard.form.label.minPatronageAccepted" /></td>
				<th id="blank"></th>
				<th id="value"><acme:print value="${entry.value}" /></th>
			</jstl:if>
		</tr>
	</jstl:forEach>
	<jstl:forEach items="${maximumOfPatronagesGroupedByStatus}" var="entry">
		<tr>
			<jstl:if test="${entry.key == 'PROPOSED'}">
				<td><acme:message
						code="administrator.administrator-dashboard.form.label.maxPatronageProposed" /></td>
				<th id="blank"></th>
				<th id="value"><acme:print value="${entry.value}" /></th>
			</jstl:if>
		</tr>
		<tr>
			<jstl:if test="${entry.key == 'DENIED'}">
				<td><acme:message
						code="administrator.administrator-dashboard.form.label.maxPatronageDenied" /></td>
				<th id="blank"></th>
				<th id="value"><acme:print value="${entry.value}" /></th>
			</jstl:if>
		</tr>
		<tr>
			<jstl:if test="${entry.key == 'ACCEPTED'}">
				<td><acme:message
						code="administrator.administrator-dashboard.form.label.maxPatronageAccepted" /></td>
				<th id="blank"></th>
				<th id="value"><acme:print value="${entry.value}" /></th>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>