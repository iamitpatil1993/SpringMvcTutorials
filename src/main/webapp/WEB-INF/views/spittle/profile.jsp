<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>
	<h1>Your Profile</h1>
	<c:out value="${spitter.username}" />
	<br />
	<c:out value="${spitter.firstName}" />
	<c:out value="${spitter.lastName}" />
	<div>
		<span><c:out value="${spitter.receiveNewsletter}" /></span>
	</div>
	<div>
		<c:forEach var="interest" items="${spitter.interests}">
			<p><c:out value="${interest}" /></p>
		</c:forEach>
	</div>
	<div>
		<span><c:out value="${spitter.favouriteWord}" /></span>
	</div>
	
	<div>
		<p>Gender: ${spitter.gender}</p>
	</div>
</body>
</html>