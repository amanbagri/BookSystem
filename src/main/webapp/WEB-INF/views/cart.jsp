<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="availableCount" value="${userModel.cart.issue_item}" />
<div class="container">


	<c:if test="${not empty message}">

		<div class="alert alert-info">
			<h3 class="text-center">${message}</h3>
		</div>

	</c:if>

	<c:choose>
		<c:when test="${not empty cartLines}">
			<table id="cart" class="table table-hover table-condensed">
				<thead>
					<tr>
						<th style="width: 50%">Book Name</th>
						<th style="width: 8%">Quantity</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cartLines}" var="issue">
						<c:if test="${issue.available == false}">
							<c:set var="availableCount" value="${availableCount - 1}" />
						</c:if>

						<tr>
							<td data-th="Book">
								<div class="row">
									<div class="col-sm-2 hidden-xs">
										<img src="${images}/${issue.book.code}.jpg"
											alt="${issue.book.name}" class="img-responsive dataTableImg" />
									</div>
									<div class="col-sm-10">
										<h4 class="nomargin">${issue.book.name}
											<c:if test="${issue.available == false}">
												<strong style="color: red">(Not Available)</strong>
											</c:if>
										</h4>
										<p>Author : ${issue.book.author}</p>
										<p>Description : ${issue.book.description}
									</div>
								</div>
							</td>
							<td data-th="Quantity"><input type="number"
								id="count_${issue.id}" class="form-control text-center"
								value="${issue.bookCount}" min="1" max="3"></td>
							<td class="actions" data-th=""><c:if
									test="${issue.available == true}">
									<button type="button" name="refreshCart"
										class="btn btn-info btn-sm" value="${issue.id}">
										<span class="glyphicon glyphicon-refresh"></span>
									</button>
								</c:if> <a href="${contextRoot}/cart/${issue.id}/remove"
								class="btn btn-danger btn-sm"><span
									class="glyphicon glyphicon-trash"></span></a></td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td class="text-center"><strong>Total =
								${userModel.cart.total}</strong></td>
					</tr>
					<tr>
						<td><a href="${contextRoot}/show/all/books"
							class="btn btn-warning"><span
								class="glyphicon glyphicon-chevron-left"></span> Continue
								Shopping</a></td>
						<td colspan="2" class="hidden-xs"></td>

						<c:choose>
							<c:when test="${availableCount != 0}">
								<td><a href="${contextRoot}/cart/validate"
									class="btn btn-success btn-block">Checkout <span
										class="glyphicon glyphicon-chevron-right"></span></a></td>
							</c:when>
							<c:otherwise>
								<td><a href="javascript:void(0)"
									class="btn btn-success btn-block disabled"><strike>Checkout
											<span class="glyphicon glyphicon-chevron-right"></span>
									</strike></a></td>
							</c:otherwise>
						</c:choose>

					</tr>
				</tfoot>
			</table>

		</c:when>

		<c:otherwise>

			<div class="jumbotron">

				<h3 class="text-center">Your Cart is Empty!</h3>

			</div>

		</c:otherwise>
	</c:choose>




</div>
