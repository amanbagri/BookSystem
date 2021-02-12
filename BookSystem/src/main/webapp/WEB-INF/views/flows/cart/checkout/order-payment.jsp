<%@include file="../../flows-shared/header.jsp"%>
<div class="container">

	<div class="row">
		<!--  To display all the goods -->
		<div class="col-md-6">

			<div class="row">
				<c:forEach items="${checkoutModel.cartIssue}" var="cartLine">
					<div class="col-xs-3">

						<img src="${images}/${cartLine.book.code}.jpg"
							class="img img-responsive" />

					</div>
					<div class="col-xs-9">
						<h3>Book Name : ${cartLine.book.name}</h3>
						<h4>Quantity ${cartLine.bookCount}</h4>
					</div>
				</c:forEach>
			</div>

			<h3>
				Total Book Issue <span class=" pull-right">
					${checkoutModel.checkoutTotal}</span>
			</h3>
			<br /> <a href="${flowExecutionUrl}&_eventId_pay"
				class="btn btn-success btn-lg btn-block" role="button">Confirm</a>

		</div>

	</div>
</div>
<%@include file="../../flows-shared/footer.jsp"%>