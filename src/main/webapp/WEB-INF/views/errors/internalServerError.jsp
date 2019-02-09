
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/internalServerErrorPageLayout.css" />"/>
</head>
<body>
	<div class="wrapper">
		<div class="box">
			<h1>500</h1>
			<p>Sorry, it's me, not you.</p>
			<p>&#58;&#40;</p>
			
			<div class="mb-4 lead">${errorMessage}.</div>
			<p><a href="<s:url value="/" />" class="btn btn-link">Back to Home</a></p>
		</div>
	</div>
</body>
</html>