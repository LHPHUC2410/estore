<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<h2 class="alert alert-success">REVENUE BY CUSTOMER</h2>
<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#tab1">Data</a></li>
  <li><a data-toggle="tab" href="#tab2">Chart</a></li>
</ul>

<div class="tab-content">
  <div id="tab1" class="tab-pane fade in active">
  	<table class="table table-hover">
<thead>
	<tr>
		<th>Customer</th>
		<th>Quantity</th>
		<th>Revenue</th>
		<th>Min</th>
		<th>Max</th>
		<th>Avg</th>
		<th></th>
	</tr>
</thead>
<tbody>
<c:forEach var="e" items="${data}">
	<tr>
		<td>${e[0]}</td>
		<td>${e[1]}</td>
		<td>$<f:formatNumber value="${e[2]}" pattern="#,###.00"/></td>
		<td>$<f:formatNumber value="${e[3]}" pattern="#,###.00"/></td>
		<td>$<f:formatNumber value="${e[4]}" pattern="#,###.00"/></td>
		<td>$<f:formatNumber value="${e[5]}" pattern="#,###.00"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>
  	
  </div>
  <div id="tab2" class="tab-pane fade">
  <jsp:include page="_revenue_customer_chart.jsp"/>
  </div>
</div>

