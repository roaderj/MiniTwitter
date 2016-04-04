<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><title>Login</title></head>
<body>
<h1>Login</h1>
<c:url var="loginUrl" value="/login" />
<form action="${loginUrl}" method="POST" class="form-horizontal">
   <table>
      <tr>
         <td>User:</td>
         <td><input type='text' name='username' /></td>
      </tr>
      <tr>
         <td>Password:</td>
         <td><input type='password' name='password' /></td>
      </tr>
      <tr>
         <td><input name="submit" type="submit" value="submit" /></td>
         <td><input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" /></td>
      </tr>
   </table>
</form>
</body>
</html>