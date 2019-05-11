<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>EntreLink - Search</title>
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
			#searchBar{
				
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
	
		<div id="Label">
			<p>Search</p>
		</div>
		<div id="searchBar">
			<input type="text" name="search" size="12"/>
			<input type="Submit" name="submitSearch" value="Search!">
		</div>
			<div id="description">
				it works
			</div>
	</body>
</html>
