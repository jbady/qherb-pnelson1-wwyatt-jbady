<!DOCTYPE html>

<html>
	<head>
		<title>EntreLink - Profile</title>
		<link rel="stylesheet" type="text/css" href="style.css"/>
	</head>

	<body>
	<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/profile" method="post">
		<div id="content">
			<p>Profile</p>
			<div id="description">
				This is where users will be able to edit their profiles when logged in.
			</div>
			<input type="Submit" name="goToProjects" value="Projects">
		</div>	
	</body>
</html>
