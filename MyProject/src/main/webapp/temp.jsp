<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>HTML5 Document type</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<style type="text/css">
.chart {
	max-width: 700px;
	margin: 3% auto 0;
}
.chart table{width:100%; border:1px solid #444; border-collapse: collapse; border-spacing: 0; margin-bottom:30px;}
.chart table th,
.chart table td{padding:5px; border-bottom:1px solid #444;}
.chart table th{background:#eee; border-right:1px solid #444;}
.chart table td{border-right:1px solid #444; text-align:center;}
</style>
</head>
<body>
	<div class="chart">
		<div>
			<table>
				<tr>
					<th>학년</th>
					<th>중간고사</th>
					<th>기말고사</th>
				</tr>
				<tr>
					<th>1학년</th>
					<td><fmt:formatNumber type="number" value="${chart[0]}"/></td>
					<td><fmt:formatNumber type="number" value="${chart[1]}"/></td>
				</tr>
				<tr>
					<th>2학년</th>
					<td><fmt:formatNumber type="number" value="${chart[2]}"/></td>
					<td><fmt:formatNumber type="number" value="${chart[3]}"/></td>
				</tr>
				<tr>
					<th>3학년</th>
					<td><fmt:formatNumber type="number" value="${chart[4]}"/></td>
					<td><fmt:formatNumber type="number" value="${chart[5]}"/></td>
				</tr>
			</table>
		</div>
		<div><canvas id="myChart"></canvas></div>
	</div>
	<script>
	const ctx = document.getElementById('myChart');
	
	new Chart(ctx, {
		type: 'bar',
		data: {
			labels: ['1학년','2학년','3학년'],
			datasets: [{
				label: '중간고사',
				data: [${chart[0]},${chart[2]},${chart[4]}],
				borderWidth: 1
			},{
				label: '기말고사',
				data: [${chart[1]},${chart[3]},${chart[5]}],
				borderWidth: 1
			}]
		},
		options: {
		    scales: {
				y: {
					beginAtZero: true,
					max:100
				}
		    }
		}
	});
</script>
</body>
</html>