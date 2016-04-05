<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>MiniTwitter</title>
</head>
<body>
	<h2>Welcome to MiniTwitter!</h2>
 	<button onclick="goBack()">Go Back</button>
	<script>
		function goBack() {
		    window.history.back();
		}
	</script>
	<h4>Find Result:</h4> 
 	<ul>
 		<c:forEach var="user" items="${users}">
 			<li>
			<form action="<c:url value="/followToggle" />" method="POST">
				<a>${user.getUsername()}</a>
				<c:if test="${user.getRelation() == 0}">
					<input name="submit" type="submit" value="Start Following" />
 				</c:if>
 				<c:if test="${user.getRelation() == 1}">
					<input name="submit" type="submit" value="Unfollow" />
 				</c:if>
 				<input type="hidden" name="followUser" value="${user.getUsername()}" />
 				<input type="hidden" name="relation" value="${user.getRelation()}" />
 				<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
 			</form>
 			</li>			
 		</c:forEach>
 	</ul>
</body>
</html>