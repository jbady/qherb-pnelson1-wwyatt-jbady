<!DOCTYPE html>
<html>
<head>
	<title>EntreLink - Home</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	
	<style type="text/css">
		html, body {
		background-color: #fcfcfc;
		margin: 0;
	}

	#navbar {
		width: 100%;
		background-color: #0b6623;
		position: fixed;
	}

	.navLink {
		display: inline-block;
		float: left;
		border: none;
		height: 100%;
		padding: 20px;
		background-color: transparent;
		font-size: 20px;
		color: #fcfcfc;
		position: relative;
		transition: 0.2s ease;
	}	

	.navLink:hover {
		background-color: #50C878;
		color: #000000;
		cursor: pointer;
	}

	#searchBox {
		box-sizing: border-box;
		background-color: #fcfcfc;
		border: none;
		border-top: 19px solid #0b6623;
		border-bottom: 19px solid #0b6623;
		font-size: 20px;
		position: relative;
		transition: 0.2s ease;
		float: right;
		margin-right: 10px;
	}

	.content {
		width: 100%;
		min-height: 400px;
		padding-top: 60px;
		margin: 0 auto;
	}

	.content h2 {
		margin: 0;
		width: 100%;
		display: block;
		padding: 50px 0;
		background-color: #444C38;
		text-align: center;
		font-family: sans-serif;
		color: #fcfcfc;
		font-weight: normal;
		font-size: 50px;
	}

	.getStartedButton {
		margin: 50px auto;
		width: 768px;
		padding: 40px 0px;
		display: block;
		background-color: #A9BA9D;
		border-radius: 20px;
		border: none;
		color: #fcfcfc;
		font-size: 30px;
		transition: 0.2s ease;
	}
	
	.getStartedButton:hover {
		background-color: #0b6623;
		cursor: pointer;
	}

	.content table {
		width: 100%;
	}

	.content table tr th {
		width: 50%;
		box-sizing: border-box;
		font-size: 30px;
		font-family: sans-serif;
		padding: 50px 0;
	}
	</style>
</head>
<body>

	<div id="navbar">
		<c:if test="${empty loggedInName}">
			<form action="${pageContext.servletContext.contextPath}/login" method="get">
				<input type="Submit" name="submit" value="Log In" class="navLink">
			</form>
		</c:if>
		<c:if test="${! empty loggedInName}">
			<form action="${pageContext.servletContext.contextPath}/profile" method="openProfile">
				<input type="Submit" name="submit" value="${loggedInName}" class="navLink">
			</form>
		</c:if>

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
