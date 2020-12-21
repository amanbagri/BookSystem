<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
	<script>
		window.userRole = '${userModel.role}';
	</script>
<!-- Navigation -->
<nav
	class="navbar navbar-expand-lg navbar-dark bg-dark navbar-fixed-top ">
	<div class="container" ">
		<a class="navbar-brand" href="${contextRoot}/home">Book Management
			System</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ">
				<li class="nav-item" id="home"><a class="nav-link"
					href="${contextRoot}/home">Home <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item" id="book"><a class="nav-link"
					href="${contextRoot}/show/all/books">Books <span
						class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item about" id="about"><a class="nav-link"
					href="${contextRoot}/about">About</a></li>
					
					<security:authorize access="hasAuthority('ADMIN')">
				<li class="nav-item"><a class="nav-link"
					href="${contextRoot}/manage/book">Manage Books</a></li>
					</security:authorize>
				<li class="nav-item"><a class="nav-link" href="#">Contact</a></li>
			</ul>
			<ul class="navbar-nav  navbar-right">
				<security:authorize access="isAnonymous()">
					<li id="signup"><a href="${contextRoot}/membership">Sign
							Up</a></li>
					<li id="login"><a href="${contextRoot}/login">Login</a></li>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<li class="dropdown" id="userModel"><a
						class="btn btn-default dropdown-toggle" href="javascript:void(0)"
						id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="true"> ${userModel.fullName} <span
							class="caret"></span>
					</a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<security:authorize access="hasAuthority('USER')">
								<li id="cart"><a href="${contextRoot}/cart/show"> <span
										class="glyphicon glyphicon-shopping-cart"></span>&#160;<span
										class="badge">${userModel.cart.issue_item}</span> 
								</a></li>
								<li role="separator" class="divider"></li>
							</security:authorize>
							<li id="logout"><a href="${contextRoot}/logout">Logout</a></li>
						</ul></li>
				</security:authorize>
			</ul>
		</div>
	</div>
</nav>
