<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %> <!-- Spring form-binding tag library -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register spittle</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/errors.css" />"/>
</head>
<body>
	<h1>Register</h1>
	<!-- Since, this form does not has action attribute set, it will send form to same url that displayed the form -->
	<!-- So, this is similar to <form method = "POST" action = "/spittles/register"> -->
	<sf:form modelAttribute="spittle" method="POST"> <!-- commandName attribute is deprecated over modelAttribute -->
		<sf:errors path="*" element="div" cssClass="errors"/> <br/> <!--this tag can be added inside form tag only, it will work only if it is inside this form tag, because binding exists inside this form tag only and not outside. If used outside, it will have no effect and also will not give any error. -->
		First Name : <sf:input path="firstName"/> <sf:errors path="firstName" cssClass="errors"/> <br/> 
		Last Name : <sf:input path="lastName"/>  <sf:errors path="lastName" cssClass="errors"/> <br/>
		Username : <sf:input path="username"/> <sf:errors path="username" cssClass="errors" /> <br/>
		Email : <sf:input path="email"/> <sf:errors path="email" cssClass="errors"/>  <br/>  <!-- We can provide standard HTML 5 input types as well using type attribute -->
		Password: <sf:password path="password"/> <sf:errors path="password" cssClass="errors"/> <br/>
		<input type="submit" value="Register" />
	</sf:form>
</body>
</html>