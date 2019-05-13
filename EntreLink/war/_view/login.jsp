<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
					<div class="navLink" style="padding:0;">
						<img id="userPic" src="${loggedInImg}" style="border-radius: 100%;width: 40px;display: inline-block;float: left;margin-top:14px;margin-left:10px;">
						<input type="Submit" name="submit" value="${loggedInName}" class="navLink">
					</div>
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
			<select name="searchIndex" value="Users" class="navLink" style="float: right;">
				<option>Users</option>
				<option>Projects</option>
			</select>
			<input type="text" name="searchText" id="searchBox">
		</form>
	</div>

	<div class="content">
		<h2>Log In</h2>
		<c:choose>
			<c:when test="${! empty loggedInName}">
				<h3 style="margin-top: 100px;">You are already logged in.</h3>
			</c:when>
			<c:otherwise>
				<form action="${pageContext.servletContext.contextPath}/login" method="post" id="loginForm">
					<!-- Super secret variable names so we don't get rekt by SQL injections :^) -->
					Username or email:<br>
					<input type="text" name="emailAsUsername" size="12" value="${emailAsUsername}" class="loginTextBox" /><br>
					Password:<br>
					<input type="password" name="passwordOfUser" size="12" value="${passwordOfUser}" class="loginTextBox" /><br>
					<center><input type="Submit" name="submitLoginData" value="Log in"></center>	
				</form>
				<form action="${pageContext.servletContext.contextPath}/signup" method="get" id="signupForm">
					<h3>Need an account?</h3>
					<input type="Submit" name="submitLoginData" value="Sign up" style="display: block; margin: 0 auto;">
				</form>
			</c:otherwise>
		</c:choose>
	</div>


</body>
</html>