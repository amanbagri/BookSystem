<div class="container" style="background-color: rgb(158, 167, 162);">

	<div class="row">


		<!-- Would be to display sidebar -->
		<div class="col-md-2">


			<%@include file="./shared/booksidebar.jsp"%>

		</div>

		<!-- to display the actual books -->
		<div class="col-md-10">

			<!-- Added breadcrumb component -->
			<div class="row" style="background-color: rgb(235, 196, 235)">

				<div class="col-lg-12">

					<c:if test="${userClickAllBooks == true}">

						<script>
							window.categoryId = '';
						</script>

						<ol class="breadcrumb  "
							style="background-color: rgb(47, 53, 50);">


							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">All Books</li>


						</ol>
					</c:if>


					<c:if test="${userClickCategoryBooks == true}">
						<script>
							window.categoryId = '${category.id}';
						</script>

						<ol class="breadcrumb " style="background-color: rgb(47, 53, 50);">


							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Category</li>
							<li class="active">${category.name}</li>


						</ol>
					</c:if>


				</div>


			</div>


			<div class="row">

				<div class="col-xs-12" style="background-color: rgb(216, 196, 216);">
					<div class="container-fluid">
						<div class="table-responsive ">
							<table id="bookListTable"
								class="table  table-borderd table-hover">
								<caption>List of Books</caption>

								<thead class="thead-dark">

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