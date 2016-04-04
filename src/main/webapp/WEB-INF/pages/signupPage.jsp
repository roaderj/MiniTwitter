<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Signup</title>
</head>
<body>
	<h1>Signup</h1>
	<a href="<c:url value="/login" />" >Login</a>
	<div>
		<sf:form action="doSignup.obj" modelAttribute="userBean" >
			<sf:errors style="color:RED"/>
			<sf:errors style="color:RED" path="userId" />
			<br />
			<sf:label path="userId">Username: </sf:label>
			<sf:input path="userId" />
			<br />
			<sf:errors style="color:RED" path="password" />
			<br />
			<sf:label path="password">Password: </sf:label>
			<sf:password path="password" />
			<br />
			<input type="submit" value="Signup" /><br />
		</sf:form>
	</div>
</body>
</html>