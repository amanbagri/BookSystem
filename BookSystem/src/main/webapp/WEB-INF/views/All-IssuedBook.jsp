<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="container">


	<c:if test="${not empty message}">

		<div class="alert alert-info">
			<h3 class="text-center">${message}</h3>
		</div>

	</c:if>

	<c:choose>
		<c:when test="${not empty issuedBook}">
			<input type="button" value="Issue New Book"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button" />
			<form:form action="search" method="GET">
                Search IssuedBook By user: <input type="number"
					name="userid" />

				<input type="submit" value="Search" class="add-button" />
			</form:form>
			<form:form action="searchReserveBookByUser" method="GET">
                Search ReserveBook By user: <input type="number"
					name="userid" />

				<input type="submit" value="Search" class="add-button" />
			</form:form>
			<table class="table table-hover table-condensed">
				<thead>
					<tr>
						<th>Book Image</th>
						<th>User Id</th>
						<th>Book Name</th>
						<th>Author Name</th>
						<th>Issue Date</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${issuedBook}" var="issue">
						<c:url var="reIssue" value="/issuedbook/showFormForReIssueBook">
							<c:param name="issuedBookid" value="${issue.id}" />
						</c:url>
						<c:url var="returnLink" value="/issuedbook/returnIssuedBook">
							<c:param name="issuedBookid" value="${issue.id}" />
						</c:url>
						<tr>
							<td><img src="${images}/${issue.book.code}.jpg" border=3
								height=100 width=100 /></td>
							<td>${issue.user.id}</td>
							<td>${issue.book.name}</td>
							<td>${issue.book.author}</td>
							<td>${issue.orderDate}</td>
							<td><a href="${reIssue}">ReIssueBook</a> | <a
								href="${returnLink}"
								onclick="if (!(confirm('Are you sure you want to Return this IssuedBook?'))) return false">Return</a></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</c:when>

		<c:otherwise>

			<div class="jumbotron">

				<h3 class="text-center">Your have no book issued!</h3>
				<p>
					<a href="${pageContext.request.contextPath}/issuedbook/list">Back
						to List</a>
				</p>

			</div>

		</c:otherwise>
	</c:choose>
</div>


