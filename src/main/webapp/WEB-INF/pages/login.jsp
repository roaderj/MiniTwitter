<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MiniTwitter Login</title>
</head>
<body>
	<h1>MiniTwitter</h1>
	<div>
		<sf:form action="doLogin.obj" modelAttribute="userBean" >
			<sf:errors />
			<br />
			<sf:label path="userId">Username: </sf:label>
			<sf:input path="userId" />
			<br />
			<sf:errors path="userId" />
			<br />
			<sf:label path="password">Password: </sf:label>
			<sf:password path="password" />
			<br />
			<sf:errors path="password" />
			<br />
			<input type="submit" value="Login" /><br />
		</sf:form>
	</div>
</body>
</html>