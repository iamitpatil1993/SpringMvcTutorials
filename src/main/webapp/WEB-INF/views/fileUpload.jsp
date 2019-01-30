<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!-- Spring form-binding tag library -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<s:url value="/files/upload" var="fileUploadAction"></s:url>
	<form action="${fileUploadAction}" method="post" enctype="multipart/form-data">
		<p>
			Select File up upload :: <input type="file" name="file" />
		</p>
		<br /> <input type="submit" name="submit" />
	</form>
</body>
</html>