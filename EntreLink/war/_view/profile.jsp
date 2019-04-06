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
				height: 80px
				border-style: solid;
				border-color: white;
				border-radius: 20px
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
		</style>
	</head>

	<body>
	<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/profile" method="post">
		<div id="content">
			<div id="Label">
				<img id="userPic" src="IMG_1039.JPG">
				<p>Profile</p>
				
			</div>
			<div id="description">
				This is where users will be able to edit their profiles when logged in.
			</div>
			<div id=buttons>
				<input type="Submit" name="goToProjects" value="Projects">
				<input type="Submit" name="goToMessaging" value="Messages">
			</div>
		</div>	
	</body>
</html>
