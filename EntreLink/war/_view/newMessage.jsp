<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>EntreLink - New Message</title>
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

	<!--THIS IS WHERE THE PAGE CONTENT BEGINS-->

	<div class="content">

		<c:choose>
		
		
			<c:when test="${! empty loggedInName}">
				<h2>Compose New Message</h2>
				<form action="${pageContext.servletContext.contextPath}/newMessage" method="post" id="newMessageForm">
					<table id="newPostTable">
						<tr>
							<td class="leftTable">Subject:</td>
							<td class="rightTable"><input type="text" name="msgSubject" size="30" value="${msgSubject}" class="newPostBox"><input hidden type="text" name="loggedInId" size="30" value="${loggedInId}" class="newPostBox"></td>
						</tr>
						<tr>
							<td class="leftTable">Body Text:</td>
							<td class="rightTable"><textarea name="msgBody" size="30" value="${msgBody}" id="msgBody"></textarea></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="Submit" name="sendMessage" value="Submit"style="float: right;"></td>
						</tr>
					</table>
				</form>
			</c:when>
			
			
			<c:otherwise>
				<div style="font-size: 20px; text-align: center; width: 100%; font-family: sans-serif; margin-top: 100px;">You must be logged in to send a message.</div>
				<form action="${pageContext.servletContext.contextPath}/login" method="get">
					<input type="Submit" name="submit" value="Log In" style="display: block; margin: 10px auto;">
				</form>
			</c:otherwise>
		</c:choose>
	</div>


</body>
</html>
