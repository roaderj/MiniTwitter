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
	<h4>You're following:</h4> 
 	<ul>
 		<c:forEach var="following" items="${followings}">
 			<li>
 			<form action="<c:url value="/followToggle" />" method="POST">
				<a>${following}</a>
				<input name="submit" type="submit" value="Unfollow" />
 				<input type="hidden" name="followUser" value="${following}" />
 				<input type="hidden" name="relation" value="1" />
 				<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
 			</form>
 			</li>
 		</c:forEach>
 	</ul>
</body>
</html>