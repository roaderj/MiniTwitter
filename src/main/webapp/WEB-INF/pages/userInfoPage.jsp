<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>MiniTwitter</title>
</head>
<body>
	<h2>Welcome to MiniTwitter!</h2>
	<h3>Hello ${message}!</h3>  
 	<a href="<c:url value="/logout" />" >Logout</a>
 	
</body>
</html>