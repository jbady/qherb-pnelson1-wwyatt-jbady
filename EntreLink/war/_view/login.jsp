<%@ page contentType="text/html;charset=utf8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>EntreLink - Login</title>
	<link rel="stylesheet" type="text/css" href="_view/css/style.css">
</head>
<body>

	<div id="navbar">
		<c:choose>
			<c:when test="${empty loggedInName}">
				<form action="${pageContext.servletContext.contextPath}/login" method="get">
					<input type="Submit" name="submit" value="Log In" class="navLink">
				</form>
			</c:when>
			<c:otherwise>
				<form action="${pageContext.servletContext.contextPath}/profile" method="openProfile">
					<input type="Submit" name="submit" value="${loggedInName}" class="navLink">
				</form>
			</c:otherwise>
		</c:choose>

		<form action="${pageContext.servletContext.contextPath}/index" method="openHome">
			<input type="Submit" name="viewIndex" value="Home" class="navLink">
		</form>
		<form action="${pageContext.servletContext.contextPath}/projects" method="openProjects">
			<input type="Submit" name="viewProjects" value="Projects" class="navLink">
		</form>
		<form action="${pageContext.servletContext.contextPath}/search" method="openSearch">
			<input type="Submit" name="viewSearch" value="Search" class="navLink" style="float: right;">
			<input type="text" name="searchText" id="searchBox">
		</form>
	</div>

	<div class="content">
		<h2>Log In</h2>
		<form action="${pageContext.servletContext.contextPath}/login" method="post" id="loginForm">
			<!-- Super secret variable names so we don't get rekt by SQL injections :^) -->
			Username or email:<br>
			<input type="text" name="emailAsUsername" size="12" value="${emailAsUsername}" class="loginTextBox" /><br>
			Password:<br>
			<input type="password" name="passwordOfUser" size="12" value="${passwordOfUser}" class="loginTextBox" /><br>
			<center><input type="Submit" name="submitLoginData" value="Log in"></center>	
		</form>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	</div>


</body>
</html>
