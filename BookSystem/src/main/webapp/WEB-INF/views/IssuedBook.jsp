<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="availableCount" value="${userModel.cart.issue_item}" />
<div class="container">


	<c:if test="${not empty message}">

		<div class="alert alert-info">
			<h3 class="text-center">${message}</h3>
		</div>

	</c:if>

	<c:choose>
		<c:when test="${not empty issuedBook}">
			<table id="cart" class="table table-hover table-condensed">
				<thead>
					<tr>
						<th>Book Image</th>
						<th>Book Name</th>
						<th>Author Name</th>
						<th>Issue Date</th>
						<security:authorize access="hasAuthority('ADMIN')">
							<th>Actions</th>
						</security:authorize>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${issuedBook}" var="issue">
					<c:url var="Issue" value="/issuedbook/showFormForIssueReserveBookByUser">
							<c:param name="issuedBookid" value="${issue.id}" />
						</c:url>

						<tr>

							<td><img src="${images}/${issue.book.code}.jpg" border=3
								height=100 width=100 /></td>
							<td>${issue.book.name}</td>
							<td>${issue.book.author}</td>
							<td>${issue.orderDate}</td>
							<security:authorize access="hasAuthority('ADMIN')">
							<td><a href="${Issue}">Issue</a>
							</security:authorize>
						</tr>

					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<th>Book Image</th>
						<th>Book Name</th>
						<th>Author Name</th>
						<th>Issue Date</th>
					</tr>
				</tfoot>

			</table>

		</c:when>

		<c:otherwise>

			<div class="jumbotron">

				<h3 class="text-center">Your have no book issued!</h3>

			</div>

		</c:otherwise>
	</c:choose>




</div>
