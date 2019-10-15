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
					<th>ID</th>
					<th>Date / Time</th>
					<th>Description</th>
					<th>Calories</th>
					<th colspan=2>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="mealTo" items="${mealToList}">
					<tr style="color:${mealTo.excess ? 'red' : 'green'}">
						<td>${mealTo.id}</td>
						<td>${mealTo.dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}</td>
						<td>${mealTo.description}</td>
						<td>${mealTo.calories}</td>
						<td><a href="?action=edit&mealId=${mealTo.id}">Edit</a></td>
						<td><a href="?action=delete&mealId=${mealTo.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>