<!DOCTYPE html>

<html>
	<head>
		<title>EntreLink - Home</title>
		<style type="text/css">
			html, body{
				background-color: green;
				margin: 0;
				width: 100%;
				height: 100%;
			}
			h1 {
				margin: 100px auto;
				color: white;
				text-align: center;
				font-family: sans-serif;
				font-size 100px;
			}
		</style>
	</head>

	<body>
		
		<h1>Welcome to<br>EntreLink!</h1>
		
		<form action="${pageContext.servletContext.contextPath}/login" method="get">
			<center><input type="Submit" name="submit" value="Log in"></center>
		</form>

	</body>
</html>