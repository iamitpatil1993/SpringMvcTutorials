<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %> <!-- Spring form-binding tag library -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Please sign in</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet">
<link href="<c:url value="/resources/css/signin.css" />" rel="stylesheet" />
</head>

<body>
	<div class="container">
		<c:url value="/login" var="loginUrl"/> <!-- We are submitting login form to spring build in login handler -->
		<sf:form class="form-signin" method="post" action="${loginUrl}">
			<h2 class="form-signin-heading">Please login</h2>
			
			<!-- If login page redirected from invalid login, there will be query parameter with name 'error' in that case we should show invalid credentials error -->
			<c:if test="${param.error != null}">
				<div class="alert alert-danger" role="alert">Invalid username and password.</div>
			</c:if>
			<!-- If login page redirected from successful logout, there will be query parameter with name 'logout' in that case we should show successful logout message -->
			<c:if test="${param.logout != null}">
				<div class="alert alert-success">You have been logged out successfully.</div>
			</c:if>
			<p>
				<label for="username" class="sr-only">Username</label> <input
					type="text" id="username" name="username" class="form-control"
					placeholder="Username" required autofocus> <!-- this form parameter name should match with sprig login handler expectation which is 'username' we can change this in WebSecurityConfigurer.configure(HttSecurity) method and set whatever we want -->
			</p>
			<p>
				<label for="password" class="sr-only">Password</label> <input
					type="password" id="password" name="password" class="form-control"
					placeholder="Password" required> <!-- this form parameter name should match with sprig login handler expectation which is 'password' we can change this in WebSecurityConfigurer.configure(HttSecurity) method and set whatever we want -->
			</p>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
		</sf:form>
	</div>
</body>
</html>