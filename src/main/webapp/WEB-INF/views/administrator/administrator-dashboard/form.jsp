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
	<tr>
		<td scope="row"><acme:message
				code="administrator.administrator-dashboard.form.label.total-number-of-tools" />

		</td>
		<th></th>
		<th><acme:print value="${totalNumberOfTools}" /></th>
	</tr>
</table>
<h2>
	<acme:message
		code="administrator.dashboard.form.title.components" />
</h2>
<table class="table table-sm">
	<tr>
		<th><acme:message
				code="administrator.administrator-dashboard.form.title.measure" />
		</th>
		<th><acme:message
				code="administrator.administrator-dashboard.form.title.groupby" />
		</th>
		<th></th>
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
				<acme:print value="${pairSplited[0]}" /> <br /> <acme:message
					code="administrator.administrator-dashboard.form.label.technology" />
				<acme:print value="${pairSplited[1]}" /></td>
			<th><acme:print value="${entry.value}" /></th>
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
				<acme:print value="${pairSplited[0]}" /> <br /> <acme:message
					code="administrator.administrator-dashboard.form.label.technology" />
				<acme:print value="${pairSplited[1]}" /></td>
			<th><acme:print value="${entry.value}" /></th>
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
				<acme:print value="${pairSplited[0]}" /> <br /> <acme:message
					code="administrator.administrator-dashboard.form.label.technology" />
				<acme:print value="${pairSplited[1]}" /></td>
			<th><acme:print value="${entry.value}" /></th>
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
				<acme:print value="${pairSplited[0]}" /> <br /> <acme:message
					code="administrator.administrator-dashboard.form.label.technology" />
				<acme:print value="${pairSplited[1]}" /></td>
			<th><acme:print value="${entry.value}" /></th>
		</tr>
	</jstl:forEach>
</table>