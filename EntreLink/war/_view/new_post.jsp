<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>EntreLink - New Post</title>
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
				<h2>New Post</h2>
				<form action="${pageContext.servletContext.contextPath}/new_post" method="post" id="newPostForm">
					<!--<h4 style="text-align:center;color:red;">${errorMessage}</h4>-->
					<table id="newPostTable">		
						<c:choose>
							<c:when test="${loggedInType != 'Business'}">
								<tr>
									<td class="leftTable">Post Type: </td>
									<td class="rightTable">
										<select name="postType" value="${postType}">
											<option>Student Proposal</option>
											<option>Student Skill</option>
										</select>
									</td>
								</tr>
							</c:when>
						</c:choose>
						<tr>
							<td class="leftTable">Post Title:</td>
							<td class="rightTable"><input type="text" name="postTitle" size="30" value="${postTitle}" class="newPostBox"><input hidden type="text" name="loggedInId" size="30" value="${loggedInId}" class="newPostBox"></td>
						</tr>
						<tr>
							<td class="leftTable">Post Description:</td>
							<td class="rightTable"><textarea name="postDescription" size="30" value="${postDescription}" id="postDescription"></textarea></td>
						</tr>
						<tr>
							<td class="leftTable">Post Tags:<br><span style="color: #999; font-size: 10px; font-style: italic;">Separate with spaces.</span></td>
							<td class="rightTable"><input type="text" name="tags" size="30" value="${tags}" class="newPostBox"></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="Submit" name="submitPost" value="Submit"style="float: right;"></td>
						</tr>
					</table>
				</form>
			</c:when>
			
			
			<c:otherwise>
				<div style="font-size: 20px; text-align: center; width: 100%; font-family: sans-serif; margin-top: 100px;">You must be logged in to make a new post.</div>
				<form action="${pageContext.servletContext.contextPath}/login" method="get">
					<input type="Submit" name="submit" value="Log In" style="display: block; margin: 10px auto;">
				</form>
			</c:otherwise>
		</c:choose>
	</div>


</body>
</html>
