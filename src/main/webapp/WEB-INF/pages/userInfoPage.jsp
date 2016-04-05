<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>MiniTwitter</title>
</head>
<body>
	<h2>Welcome to MiniTwitter!</h2>
	<h3>Hello ${user}!  <a href="<c:url value="/logout" />" >Logout</a></h3>  
 	
 	<form action="<c:url value="/following" />" method="GET">
 		<input type="submit" value="Following"/>
 	</form>
 	<form action="<c:url value="/followers" />" method="GET">
 		<input type="submit" value="Followers"/>
 	</form>
 	<form action="<c:url value="/findUser" />" method="Get">
 		<a>Find user:</a>
 		<input type='text' name='findUser' size="30"/>
 		<input type="submit" value="Find"/>
 	</form>
 	<form action="<c:url value="/tweetMessage" />" method="POST">
 		<a>What's happening?</a>
 		<input type='text' name='message' size="70"/>
 		<input type="submit" value="Tweet"/>
 		<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
 	</form>
 	<h4>Tweets:</h4> 
 	<form action="<c:url value="/tweetsSearch" />" method="GET">
	    <input type='text' name='search' size="50"/>
 		<input type="submit" value="Search"/>
 	</form>
 	<ul>
 		<c:forEach var="tweet" items="${tweets}">
 			<li>${tweet}</li>
 		</c:forEach>
 	</ul>
</body>
</html>