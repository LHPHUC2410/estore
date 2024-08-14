<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Customer', 'Revenue'],
          <c:forEach var="e" items="${data}">
          ["${e[0]}", ${e[2]}],
          </c:forEach>

        ]);


        var options = {
                title: 'Revenue by Customer',
                curveType: 'function',
                legend: { position: 'bottom' }
              };

              var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

              chart.draw(data, options);
            }
          </script>
</head>
<body>
	<div id="curve_chart"></div>
</body>
</html>
<style>
#curve_chart{
	width: 900px!important;
	height: 500px!important;
}
</style>