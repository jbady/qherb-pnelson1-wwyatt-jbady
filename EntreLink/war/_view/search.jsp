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
				margin-left:auto;
				margin-right:auto;
				display: block;
			}
		</style>
	</head>

	<body>
		<div id="Label">
			<p>Search</p>
		</div>
		<div id="searchBar">
			<input type="text" name="search" size="12"/>
			<input type="Submit" name="submitSearch" value="Search!">
		</div>
			<div id="description">
				This is where users will be able to search for specific projects once implemented. Right now it does nothing.
			</div>
	</body>
</html>
