<!DOCTYPE html>

<html>
	<head>
		<title>EntreLink - Profile</title>
		<style type="text/css">
			#Label{
				background-color: green;
				color: white;
				font-family: sans-serif;
				font-size: 32px;
				text-align: center;
				height: 80px;
				border-style: solid;
				border-color: white;
				border-radius: 20px;
				margin-top: 100px;
			}
			#description {
				margin: 20px auto;
				color: green;
				text-align: center;
				font-family: sans-serif;
				font-size 24px;
				border-color: green;
			}
			#userPic{
				float: right;
			}
			#userBar {
				width: 100%;
				background-color: green;
				position: fixed;
				top: 0;
			}
			#userBar h2 {
				font-size: 16px;
				font-weight: normal;
				margin: 20px;
				color: black;
				display: block;
				float: left;
			}
			input {
				display: inline-block;
				font-size 16px;
				margin: 20px 0;
				margin-right: 20px;
				float: right;
			}
		</style>
	</head>

	<body>
		<div id="userBar">
			<h2>${loggedInName}</h2>
			<form action="${pageContext.servletContext.contextPath}/projects" method="openProjects">
				<input type="Submit" name="viewProjects" value="Projects">
			</form>
			<form action="${pageContext.servletContext.contextPath}/profile" method="openProfile">
				<input type="Submit" name="viewProjects" value="Profile">
			</form>
			<form action="${pageContext.servletContext.contextPath}/search" method="openSearch">
				<input type="Submit" name="viewProjects" value="Search">
			</form>
			<form action="${pageContext.servletContext.contextPath}/index" method="openHome">
				<input type="Submit" name="viewProjects" value="Home">
			</form>
		</div>
	
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/profile" method="post">
		<div id="content">
			<div id="Label">
				<img id="userPic" src="${loggedInImg}" style="border-radius: 100%;width: 100px;">
				<p>Profile</p>
				
			</div>
			<div id="description">
				This is ${loggedInName}'s profile.
			</div>
			<div id=buttons>
				<form action="${pageContext.servletContext.contextPath}/projects" method="get">
					<input type="Submit" name="goToProjects" value="Projects">
				</form>
				<form action="${pageContext.servletContext.contextPath}/messages" method="get">
					<input type="Submit" name="goToMessaging" value="Messages">
				</form>
			</div>
		</div>	
	</body>
</html>
