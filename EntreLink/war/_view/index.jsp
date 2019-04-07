<!DOCTYPE html>

<html>
	<head>
		<title>EntreLink - Home</title>
		<style type="text/css">
			html, body{
				background-color: green;
				margin: 0;
				width: 100%;
				height: 100%;
			}
			h1 {
				margin: 100px auto;
				color: white;
				text-align: center;
				font-family: sans-serif;
				font-size 100px;
			}
			#userBar {
				width: 100%;
				background-color: green;
				position: fixed;
				top: 0;
			}
			#userBar h2 {
				font-size: 16px;
				font-weight: normal;
				margin: 20px;
				color: black;
				display: block;
				float: left;
			}
			input {
				display: inline-block;
				font-size 16px;
				margin: 20px 0;
				margin-right: 20px;
				float: right;
			}
		</style>
	</head>

	<body>
		
		<div id="userBar">
			<h2>${loggedInName}</h2>
			<form action="${pageContext.servletContext.contextPath}/projects" method="openProjects">
				<input type="Submit" name="viewProjects" value="Projects">
			</form>
			<form action="${pageContext.servletContext.contextPath}/profile" method="openProfile">
				<input type="Submit" name="viewProjects" value="Profile">
			</form>
			<form action="${pageContext.servletContext.contextPath}/search" method="openSearch">
				<input type="Submit" name="viewProjects" value="Search">
			</form>
			<form action="${pageContext.servletContext.contextPath}/index" method="openHome">
				<input type="Submit" name="viewProjects" value="Home">
			</form>
		</div>
		
		<h1>Welcome to<br>EntreLink!</h1>
		<form action="${pageContext.servletContext.contextPath}/login" method="get">
			<center><input type="Submit" name="submit" value="Log in"></center>
		</form>

	</body>
</html>