<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %> <!-- Spring form-binding tag library -->
	<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
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
	<sf:form modelAttribute="spittle" method="POST" enctype="multipart/form-data"> <!-- commandName attribute is deprecated over modelAttribute -->
		<sf:errors path="*" element="div" cssClass="errors"/> <br/> <!--this tag can be added inside form tag only, it will work only if it is inside this form tag, because binding exists inside this form tag only and not outside. If used outside, it will have no effect and also will not give any error. -->
		<sf:label path="firstName" cssErrorClass="errors">First Name : </sf:label><sf:input path="firstName" cssErrorClass="errors"/> <sf:errors path="firstName" cssClass="errors"/> <br/> 
		<sf:label path="lastName" cssErrorClass="errors">Last Name : </sf:label><sf:input path="lastName" cssErrorClass="errors"/>  <sf:errors path="lastName" cssClass="errors"/> <br/>
		<sf:label path="username" cssErrorClass="errors">Username : </sf:label> <sf:input path="username" cssErrorClass="errors"/> <sf:errors path="username" cssClass="errors" /> <br/>
		<sf:label path="email" cssErrorClass="errors">Email : </sf:label><sf:input path="email" cssErrorClass="errors"/> <sf:errors path="email" cssClass="errors"/>  <br/>  <!-- We can provide standard HTML 5 input types as well using type attribute -->
		<sf:label path="password" cssErrorClass="errors">Password: </sf:label> <sf:password path="password" cssErrorClass="errors"/> <sf:errors path="password" cssClass="errors"/> <br/>
		<span>Subscribe to newsletter?: <sf:checkbox path="receiveNewsletter"/></span> <!-- this will bind boolean value -->
		
		<div style="margin: 20px; border: thin; border-color: black; background: gray; padding: 20px;">
			Select profile picture : <span><sf:input type="file" name="profilepicture" path = "profilepicture" accept="image/*"/></span>
		</div>
		<div>
		<span>Interests:</span> <!-- This will bind multiple values to collection/array in model attribute property -->
		  <p>Quidditch: <sf:checkbox path="interests" value="Quidditch"/></p>
          <p>Herbology: <sf:checkbox path="interests" value="Herbology"/></p>
          <p>Defence Against the Dark Arts: <sf:checkbox path="interests" value="Defence Against the Dark Arts"></sf:checkbox></p> 
		</div>
		
		<div>
			<p>Gender:</p>
			<p>Male: <sf:radiobutton path="gender" value="Male"/></p>
			<p>Female: <sf:radiobutton path="gender" value="Female"/></p>
		</div>
		<input type="submit" value="Register" />
	</sf:form>

	<pre>
	<img alt="image"  src="<c:url value="/files/baeldung-rest-widget-main-1.2.0.jpg" />">
</pre>
</body>
</html>