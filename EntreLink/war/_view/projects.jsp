<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>EntreLink - Projects</title>
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
			<input type="text" name="searchText" id="searchBox">
		</form>
	</div>

	<div class="content">
		<h2>Projects</h2>
		<div id="filterProjects">
			Filter by:&emsp;
			<select>
				<option>All Listings</option>
				<option>Business Proposals</option>
				<option>Student Proposals</option>
				<option>Student Skills</option>
			</select>
		</div>

		<div class="listingPost">
			<h3 class="projectTitle">Corn Scanner<span class="projectPosted">posted on April 28, 2019 at 1:11pm</span></h3>
			<div class="hideOverflow">
				<p class="projectDescription">I'm looking to develop an app where the user may scan a farmer's entire field and count exactly how many kernels of corn there are on the given plot.  I will use this information to assure the destruction of the world economy via corn.  Corn would also be a fantastic fuel source in addition to being fun to eat, thus the demand will skyrocket.  At this point I am just filling in random stuff to get this to overflow.  One more line should do it, please be patient I am trying my best.</p>
			</div>
			<a href="#">Read More</a>
		</div>
		<div class="listingPost">
			<h3 class="projectTitle">Poop Logger<span class="projectPosted">posted on April 28, 2019 at 8:15am</span></h3>
			<div class="hideOverflow">
				<p class="projectDescription">A device that attaches to the toilet and scans your poop.  This will connect to an app where you can view your high scores, compete with friends, and ensure your colon is healthy.</p>
			</div>
			<a href="#">Read More</a>
		</div>
	</div>


</body>
</html>
