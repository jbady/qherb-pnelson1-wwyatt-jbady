<!DOCTYPE html>
<html>
<head>
	<title>EntreLink - Home</title>
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
		<h2>Get Started</h2>
		<form action="${pageContext.servletContext.contextPath}/newProject" method="makeNewProject">
			<input type="Submit" name="newProject" value="New Project" class="getStartedButton">
		</form>
		<form action="${pageContext.servletContext.contextPath}/projects" method="openProjects">
			<input type="Submit" name="viewProjects" value="View Projects" class="getStartedButton">
		</form>

		<h2>Recent Listings</h2>
		<table>
			<tr>
				<th>Student Posts</th>
				<th>Business Posts</th>
			</tr>
		</table>
	</div>


</body>
</html>