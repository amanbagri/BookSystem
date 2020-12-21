<div class="container">

	<div class="row">


		<!-- Would be to display sidebar -->
		<div class="col-md-2">

			<p class="lead">Category</p>
			<div class="list-group">
				<c:forEach items="${categories}" var="category">
					<a href="${contextRoot}/show/category/${category.id}/books"
						class="list-group-item">${category.name}</a>
				</c:forEach>
			</div>

		</div>

		<!-- to display the actual books -->
		<div class="col-md-10">

			<!-- Added breadcrumb component -->
			<div class="row">

				<div class="col-lg-12">

					<c:if test="${userClickAllBooks == true}">

						<script>
							window.categoryId = '';
						</script>

						<ol class="breadcrumb">


							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">All Books</li>


						</ol>
					</c:if>


					<c:if test="${userClickCategoryBooks == true}">
						<script>
							window.categoryId = '${category.id}';
						</script>

						<ol class="breadcrumb">


							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Category</li>
							<li class="active">${category.name}</li>


						</ol>
					</c:if>


				</div>


			</div>


			<div class="row">

				<div class="col-xs-12">
					<div class="container-fluid">
						<div class="table-responsive">
						<table id="bookListTable"
						class="table table-striped table-borderd">


						<thead>

							<tr>
								<th></th>
								<th>Name</th>
								<th>Author</th>
								<th>Qty. Available</th>
								<th></th>
							</tr>

						</thead>



					</table>
						
						
						</div>
					</div>

					
				</div>

			</div>


		</div>



	</div>






</div>