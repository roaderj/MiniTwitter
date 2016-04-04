<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<a href="<c:url value="/signup" />" >New User?</a>
	<c:url var="loginUrl" value="/login" />
	<form action="${loginUrl}" method="POST" class="form-horizontal">
	   	<table>
			<c:if test="${param.error != null}">
				<div style="color:RED">
					<a>Invalid username and password.</a>
				</div>
			</c:if>
			<c:if test="${param.logout != null}">
				<div style="color:GREEN">
					<a>You have been logged out successfully.</a>
				</div>
			</c:if>
			<c:if test="${param.signup != null}">
				<div style="color:GREEN">
					<a>You have been signed up successfully.</a>
				</div>
			</c:if>
	      <tr>
	         <td>Username:</td>
	         <td><input type='text' name='username' /></td>
	      </tr>
	      <tr>
	         <td>Password:</td>
	         <td><input type='password' name='password' /></td>
	      </tr>
	      <tr>
	         <td><input name="submit" type="submit" value="login" /></td>
	         <td><input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" /></td>
	      </tr>
	   </table>
	</form>
</body>
</html>