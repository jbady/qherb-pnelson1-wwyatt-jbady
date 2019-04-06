<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>EntreLink - Log in</title>
		<link rel="stylesheet" type="text/css" href="style.css"/>
		
		<style type="text/css">
		
			body, html {
				width: 100%;
				height: 100%;
				background-color: #f2f2f2;
			}
			
			form {
				display: block;
				margin: 0 auto;
				margin-top: 100px;
				background-color: green;
				padding: 50px;
				border-radius: 20px;
				width: 200px;
				color: white;
			}
			
			input {
				margin: 0 auto;
				margin-bottom: 20px;
				width: 100%;
			}
			input:last-child {
				margin-bottom: 20px;
			}
			
		</style>

	</head>

	<body>
			<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		
		<form action="${pageContext.servletContext.contextPath}/login" method="post">
		
			<!-- Super secret variable names so we don't get rekt by SQL injections :^) -->
			Username or email:<br>
			<input type="text" name="emailAsUsername" size="12" value="${emailAsUsername}" /><br>
			Password:<br>
			<input type="password" name="passwordOfUser" size="12" value="${passwordOfUser}" /><br>
			<center><input type="Submit" name="submitLoginData" value="Log in"></center>
			
		</form>

		<c:if test="${! empty loggedInName}">
				<div class="returnData">Welcome, ${loggedInName}!</div>
		</c:if>

	</body>
</html>
