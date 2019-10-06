<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
	<head>
		<title>Meals</title>
	</head>
	<body>
		<h3><a href="index.html">Home</a></h3>
		<hr>
		<h2>Meals</h2>
		<table border="1">
			<thead>
				<tr>
					<th>Date</th>
					<th>Time</th>
					<th>Description</th>
					<th>Calories</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="mealTo" items="${mealToList}">
					<tr style="color:${mealTo.excess ? 'red' : 'green'}">
						<td>${mealTo.dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}</td>
						<td>${mealTo.dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))}</td>
						<td>${mealTo.description}</td>
						<td>${mealTo.calories}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>