<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />


<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="MinorProject">
<meta name="author" content="Aman bagri">

<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">


<title>Book Management System-${title}</title>
<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Readable Theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Bootstrap DataTables -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">



</head>

<body>

	<!-- nav bar -->
	<%@include file="./shared/navbar.jsp"%>

	<!-- Header 
  <header class="bg-primary py-5 mb-5">
    <div class="container h-100">
      <div class="row h-100 align-items-center">
        <div class="col-lg-12">
          <h1 class="display-4 text-white mt-5 mb-2">One city One Integrated web site</h1>
           <p class="lead mb-5 text-white-50"></p>
        </div>
      </div>
    </div>
  </header>-->

	<!-- Page Content -->
	<!-- Home page -->
	<c:if test="${userClickOnHome==true}">
		<%@include file="home.jsp"%>
	</c:if>
	<c:if test="${userClickOnAbout==true}">
		<%@include file="about.jsp"%>
	</c:if>
	<c:if test="${userClickAllBooks==true}">
		<%@include file="listBooks.jsp"%>
	</c:if>
	<c:if test="${userClickCategoryBooks == true}">
		<%@include file="listBooks.jsp"%>
	</c:if>

	<c:if test="${userClickShowBook == true}">
		<%@include file="singleBook.jsp"%>
	</c:if>
	<c:if test="${userClickManageBook == true}">
		<%@include file="manageBook.jsp"%>
	</c:if>
	<c:if test="${userClickShowCart == true}">
		<%@include file="cart.jsp"%>
	</c:if>


	<!-- footer -->
	<%@include file="./shared/footer.jsp"%>
	<!-- jQuery -->
	<script src="${js}/jquery.js"></script>

	<script src="${js}/jquery.validate.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="${js}/bootstrap.min.js"></script>

	<!-- DataTable Plugin -->
	<script src="${js}/jquery.dataTables.js"></script>

	<!-- DataTable Bootstrap Script -->
	<script src="${js}/dataTables.bootstrap.js"></script>

	<!-- DataTable Bootstrap Script -->
	<script src="${js}/bootbox.min.js"></script>

	<!-- Self coded javascript -->
	<script src="${js}/myapp.js"></script>
</html>