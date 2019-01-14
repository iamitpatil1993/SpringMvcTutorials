<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %> <!-- Spring form-binding tag library -->
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
	<sf:form modelAttribute="spittle" method="POST"> <!-- commandName attribute is deprecated over modelAttribute -->
		First Name : <sf:input path="firstName"/> <br/>
		Last Name : <sf:input path="lastName"/><br/>
		Username : <sf:input path="username"/><br/>
		Email : <sf:input path="email" type = "email"/> <br/><!-- We can provide standard HTML 5 input types as well using type attribute -->
		Password: <sf:password path="password"/><br/>
		<input type="submit" value="Register" />
	</sf:form>
</body>
</html>