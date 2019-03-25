<!DOCTYPE html>

<html>
	<head>
		<title>EntreLink - Home</title>
	</head>

	<body>
		
		<form action="${pageContext.servletContext.contextPath}/login" method="get">
			<input type="Submit" name="submit" value="Log in">
		</form>
		
		<form action="${pageContext.servletContext.contextPath}/projects" method="get">
			<input type="Submit" name="submit" value="Projects">
		</form>
		
		<form action="${pageContext.servletContext.contextPath}/profile" method="get">
			<input type="Submit" name="submit" value="Profile">
		</form>
		
		<form action="${pageContext.servletContext.contextPath}/search" method="get">
			<input type="Submit" name="submit" value="Search">
		</form>

	</body>
</html>
