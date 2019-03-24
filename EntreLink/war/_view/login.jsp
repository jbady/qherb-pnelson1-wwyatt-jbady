<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>EntreLink - Log in</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>

	<body>
	
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		
		<div class = "header">Enter Your Login Information</div>
		<form action="${pageContext.servletContext.contextPath}/login" method="post">
			<!-- Super secret variable names so we don't get rekt by SQL injections :^) -->
			<p>Username or Email</p>
				<input type="text" name="emailAsUsername" size="12" value="${emailAsUsername}" /><br>
			<p>Password</p>
				<input type="text" name="passwordOfUser" size="12" value="${passwordOfUser}" /><br>
			<input type="Submit" name="submitLoginData" value="Log in">
		</form>

		<c:if test="${! empty loggedInName}">
				<div class="returnData">Welcome, ${loggedInName}!</div>
		</c:if>

	</body>
</html>
