<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<html>
<head>
	<title>Spittr</title>
</head>
<body>
	<h1><s:message code="spittle.welcome"/></h1> <!-- This messages will be searched and resolved from configured message source -->
	<a href="<c:url value="/spittles" />">Spittles</a> | <a href="<c:url value="/spittles/register" />">Register</a>
	| <a href="<c:url value="/fileupload" />">File Upload</a>
</body>
</html>