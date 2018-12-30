<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register spittle</title>
</head>
<body>
	<h1>Register</h1>
	<!-- Since, this form does not has action attribute set, it will send form to same url that displayed the form -->
	<!-- So, this is similar to <form method = "POST" action = "/spittles/register"> -->
	<form method="POST">
		First Name: <input type="text" name="firstName" /><br /> 
		Last Name: <input type="text" name="lastName" /><br />
		Username: <input type="text" name="username" /><br /> 
		Password: <input type="password" name="password" /><br /> 
		<input type="submit" value="Register" />
	</form>
</body>
</html>