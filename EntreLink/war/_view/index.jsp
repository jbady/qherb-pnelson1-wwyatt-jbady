<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>EntreLink - Home</title>
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
		<h2>Welcome to EntreLink!</h2>
		<form action="${pageContext.servletContext.contextPath}/new_post" method="makeNewProject">
			<input type="Submit" name="newProject" value="New Project" class="getStartedButton">
		</form>
		<form action="${pageContext.servletContext.contextPath}/projects" method="openProjects">
			<input type="Submit" name="viewProjects" value="View Projects" class="getStartedButton">
		</form>

		<h2>Recent Listings</h2>
		<table id="homeTable">
			<tr>
				<th>Student Posts</th>
				<th>Business Posts</th>
			</tr>
			<tr>
				<td>
					<c:forEach items="${posts}" var="post">
						<div postId="${post.postId}" class="listingPost">
	            			<h3 class="projectTitle">${post.title}<span class="projectPosted"> posted by ${post.name} on ${post.timePosted}</span></h3>
	            			<div class="hideOverflow">
								<p class="projectDescription">${post.description}</p>
								
		            			<table style="width:100%">
		            				<tr>
										<td><p class="projectTags">Tags: ${post.tags}</p></td>
										<td style="text-align:right"><form action="${pageContext.servletContext.contextPath}/newmessage" method="compMessage">
											<input type="Submit" name="${post.name}" value="Message Student" class="msgUser">
										</form></td>
									</tr>
								</table>
	            			</div>
	        			</div>
	    			</c:forEach>
				</td>
				<td>
					<c:forEach items="${bPosts}" var="bPost">
						<div postId="${bPost.postId}" class="listingPost">
	            			<h3 class="projectTitle">${bPost.title}<span class="projectPosted"> posted by ${bPost.name} on ${bPost.timePosted}</span></h3>
	            			<div class="hideOverflow">
							<p class="projectDescription">${bPost.description}</p>
							
							<table style="width:100%">
	            				<tr>
									<td><p class="projectTags">Tags: ${bPost.tags}</p></td>
									<td style="text-align:right"><form action="${pageContext.servletContext.contextPath}/newmessage" method="compMessage">
										<input type="Submit" name="${bPost.name}" value="Message Business" class="msgUser">
									</form></td>
								</tr>
							</table>
							
	            			</div>
	        			</div>
	    			</c:forEach>
				</td>
			</tr>
		</table>
	</div>


</body>
</html>