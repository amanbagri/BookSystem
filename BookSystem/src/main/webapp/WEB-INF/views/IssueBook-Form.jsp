<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
<title>Save Customer</title>
</head>

<body>


	<div id="container">
		<h3>Save Customer</h3>
		

		<form:form action="save" modelAttribute="issuedBook" method="POST">

			<!-- need to associate this data with customer id -->
			<form:hidden path="id" />

			<table>
				<tbody>
					<tr>
						<td><label>User id:</label></td>
						<td><form:input path="user.id" /></td>
					</tr>

					<tr>
						<td><label>Book id:</label></td>
						<td><form:input path="book.id" /></td>
					</tr>
					<tr>
						<td><label>order_count:</label></td>
						<td><form:input path="orderCount" /></td>
					</tr>
					<tr>
						<td><label>Issue date:</label></td>
						<td><form:input path="orderDate" value="<%= (new java.util.Date()).toLocaleString()%>" /></td>
					</tr>


					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>


				</tbody>
			</table>


		</form:form>

		<div style=""></div>

		<p>
			<a href="${pageContext.request.contextPath}/issuedbook/list">Back
				to List</a>
		</p>

	</div>

</body>

</html>










