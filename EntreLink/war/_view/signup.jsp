<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>EntreLink - Sign Up</title>
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
		<h2>Sign Up</h2>
		<c:choose>
			<c:when test="${! empty loggedInName}">
				<h3 style="margin-top: 100px;">You are already logged in.</h3>
			</c:when>
			<c:otherwise>
				<form action="${pageContext.servletContext.contextPath}/signup" method="post" id="signupForm">
					<table id="newUserTable">
						<tr>
							<td class="leftTable">First Name: </td>
							<td class="rightTable"><input type="text" name="firstname" size="12" value="${firstname}" class="signupBox"></td>
						</tr>
						<tr>
							<td class="leftTable">Last Name: </td>
							<td class="rightTable"><input type="text" name="lastname" size="12" value="${lastname}" class="signupBox"></td>
						</tr>
						<tr>
							<td class="leftTable">Username: </td>
							<td class="rightTable"><input type="text" name="newUsername" size="12" value="${newUsername}" class="signupBox"></td>
						</tr>
						<tr>
							<td class="leftTable">Email: </td>
							<td class="rightTable"><input type="text" name="newEmail" size="12" value="${newEmail}" class="signupBox"></td>
						</tr>
						<tr>
							<td class="leftTable">Confirm Email: </td>
							<td class="rightTable"><input type="text" name="confirmEmail" size="12" value="${confirmEmail}" class="signupBox"></td>
						</tr>
						<tr>
							<td class="leftTable">Password: </td>
							<td class="rightTable"><input type="password" name="newPassword" size="12" value="${newPassword}" class="signupBox"></td>
						</tr>
						<tr>
							<td class="leftTable">Confirm Password: </td>
							<td class="rightTable"><input type="password" name="confirmPassword" size="12" value="${confirmPassword}" class="signupBox"></td>
						</tr>
						<tr>
							<td>I am a:</td>
							<td>
								<select style="width: 200px; text-align: center;" name="accountType" value="${accountType}">
									<option style="text-align: center;">Student</option>
									<option style="text-align: center;">Business</option>
								</select>
							</td>
						</tr>
					</table>
					<input type="Submit" name="signUp" value="Sign up" style="display: block; margin: 10px auto;" onclick="verifyEquals()">
				</form>
			</c:otherwise>
		</c:choose>
	</div>

	<script type="text/javascript">
		function verifyEquals(){
			var email = document.getElementById('userEmail');
			var confirmEmail = document.getElementById('confirmEmail');
			var password = document.getElementById('userPassword');
			var confirmPassword = document.getElementById('confirmPassword');

			if(email.value != confirmEmail.value){
				email.style.backgroundColor = "#ffcccc";
				confirmEmail.style.backgroundColor = "#ffcccc";
			}

			if(password.value != confirmPassword.value){
				password.style.backgroundColor = "#ffcccc";
				confirmEmail.style.backgroundColor = "#ffcccc";
			}
		}
	</script>


</body>
</html>