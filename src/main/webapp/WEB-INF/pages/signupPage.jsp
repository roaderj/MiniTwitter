<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Signup</title>
</head>
<body>
	<h1>Signup</h1>
	<a href="<c:url value="/login" />" >Login</a>
	<form action="<c:url value="/signupForm" />" method="POST" class="form-horizontal">
	   	<table>
			<c:if test="${param.error != null}">
				<div style="color:RED">
					<a>Invalid username and password.</a>
				</div>
			</c:if>
			<c:if test="${param.userExist != null}">
				<div style="color:RED">
					<a>Username already used.</a>
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