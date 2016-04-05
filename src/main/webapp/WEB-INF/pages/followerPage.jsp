<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>MiniTwitter</title>
</head>
<body>
	<h2>Welcome to MiniTwitter!</h2>
	<h3>Hello ${user}!  <a href="<c:url value="/logout" />" >Logout</a></h3>  
 	<button onclick="goBack()">Go Back</button>
	<script>
		function goBack() {
		    window.history.back();
		}
	</script>
	<h4>Your Followers:</h4> 
 	<ul>
 		<c:forEach var="follower" items="${followers}">
 			<li>${follower}</li>
 		</c:forEach>
 	</ul>
</body>
</html>