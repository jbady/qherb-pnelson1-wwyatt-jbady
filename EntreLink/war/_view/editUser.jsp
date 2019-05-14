<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>EntreLink - Edit User</title>
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
		<h2>Edit User</h2>
				<form action="${pageContext.servletContext.contextPath}/edituser" method="post">
					<table id="newUserTable">
						<tr>
							<td class="leftTable">Major </td>
							<td class="rightTable"><input type="text" name="usermajor" size="12" value="${usermajor}" class="signupBox"></td>
						</tr>
						<tr>
							<td class="leftTable">Bio </td>
							<td class="rightTable"><input type="text" name="userbio" size="12" value="${userbio}" class="signupBox"></td>
						</tr>
						<tr>
							<td class="leftTable">Status </td>
							<td class="rightTable"><input type="text" name="userstatus" size="12" value="${userstatus}" class="signupBox"></td>
						</tr>
						<tr>
							<td class="leftTable">Interests </td>
							<td class="rightTable"><input type="text" name="userinterests" size="12" value="${userinterests}" class="signupBox"></td>
						</tr>
						<tr>
							<td class="leftTable">Skills </td>
							<td class="rightTable"><input type="text" name="userskills" size="12" value="${userskills}" class="signupBox"></td>
						</tr>
						<tr>
							<td class="leftTable">Website </td>
							<td class="rightTable"><input type="text" name="userwebsite" size="12" value="${userwebsite}" class="signupBox"></td>
						</tr>
						<tr>
							<td class="leftTable">Picture </td>
							<td class="rightTable"><input type="text" name="userpicture" size="12" value="${userpicture}" class="signupBox"></td>
						</tr>						
					</table>
					<input type="Submit" name="edituser" value="Confirm" style="display: block; margin: 10px auto;" onclick="verifyEquals()">
				</form>
	</div>

	
</body>
</html>