<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>EntreLink - My Profile</title>
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
		<h2>My Profile</h2>
		<img src="${loggedInImg}" id="profileImg">
		<h3>${loggedInName}</h3>
		<p id="profileBio">${loggedInBio}</p>
		<table id="profileTable">
			<tr>
				<td class="leftTable">Major: </td>
				<td class="rightTable">${loggedInMajor}</td>
			</tr>
			<tr>
				<td class="leftTable">Status: </td>
				<td class="rightTable">${loggedInStatus}</td>
			</tr>
			<tr>
				<td class="leftTable">Interests: </td>
				<td class="rightTable">${loggedInInterests}</td>
			</tr>
			<tr>
				<td class="leftTable">Skills: </td>
				<td class="rightTable">${loggedInSkills}</td>
			</tr>
			<tr>
				<td class="leftTable">WebSite: </td>
				<td class="rightTable"><a href="${loggedInWebsite}">${loggedInWebsite}</a></td>
			</tr>
		</table>
		<input type="Submit" name="editProfile" value="Edit Profile">
		<h3>My Projects</h3>
		<c:forEach items="${posts}" var="post">
			<div postId="${post.postId}" class="listingPost">
	            <h3 class="projectTitle">${post.title}<span class="projectPosted"> posted by ${post.name} on ${post.timePosted}</span></h3>
	            <div class="hideOverflow">
					<p class="projectDescription">${post.description}</p>
	            </div>
			<a href="#">Read More</a>
	        </div>
	    </c:forEach>
		
	</div>


</body>
</html>