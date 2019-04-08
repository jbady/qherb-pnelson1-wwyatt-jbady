<!DOCTYPE html>

<html>
	<head>
		<title>EntreLink - Projects</title>
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
			tr {
				display: block;
			}
			.postTags {
				margin-bottom: 20px;
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
			<p>Projects</p>
		</div>
		
		<!--I'm not sure what this does, but this does it.-->
		
		<form action="${pageContext.servletContext.contextPath}/projects" method="get">
			<table>
			    <c:forEach items="${posts}" var="post">
			        <tr class="postHead">
			            <td class="postName">Patrick Nelson - </td>
			            <td class="postTitle">Corn Simulator</td>
			            <td class="postTime">12:27pm</td>		            
			        </tr>
			        <tr class="postBody">
			        	<td class="postDesc" colspan="3">I think it would be pretty neat if we could simulate the difficult life corn has, because people need to be aware of what they are eating.</td>
			        </tr>
			        <tr class="postTags">
			        	<td class="tagsHead">Tags: </td>
			        	<td class="tags" colspan="2">corn, food, simulation, programming, computer science</td>
			        </tr>
			        
			        
			        
			        <tr class="postHead">
			            <td class="postName">Quintin Herb - </td>
			            <td class="postTitle">Lightbulb Analyzer</td>
			            <td class="postTime">01:55pm - <a href="">Edit</a></td>		            
			        </tr>
			        <tr class="postBody">
			        	<td class="postDesc" colspan="3">There are times when I wish I could tell if my lights are turned on or not, so I'm looking for someone who can make something to tell me if my lights are turned on or not.</td>
			        </tr>
			        <tr class="postTags">
			        	<td class="tagsHead">Tags: </td>
			        	<td class="tags" colspan="2">light, engineering, analysis</td>
			        </tr>
			        
			        
			        
			        <tr class="postHead">
			            <td class="postName">Jason Bady - </td>
			            <td class="postTitle">Two-legged Chair</td>
			            <td class="postTime">02:45pm</td>		            
			        </tr>
			        <tr class="postBody">
			        	<td class="postDesc" colspan="3">I feel threatened when a chair has more legs than me, so I'm looking for someone who can make me a chair with only two.</td>
			        </tr>
			        <tr class="postTags">
			        	<td class="tagsHead">Tags: </td>
			        	<td class="tags" colspan="2">design, arts</td>
			        </tr>
			        
			        
			        
			        <tr class="postHead">
			            <td class="postName">William Wyatt - </td>
			            <td class="postTitle">Cheese Grater Phone Case</td>
			            <td class="postTime">06:25pm</td>		            
			        </tr>
			        <tr class="postBody">
			        	<td class="postDesc" colspan="3">I would like the functionality of a cheese grater, but the portability of a smart phone.</td>
			        </tr>
			        <tr class="postTags">
			        	<td class="tagsHead">Tags: </td>
			        	<td class="tags" colspan="2">food, arts, engineering, 3d printing</td>
			        </tr>
			    </c:forEach>
			</table>
				
	</body>
</html>

