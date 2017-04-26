<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
</head>
<body>
<jsp:include page="header.jsp" />
<table >
	<td>
		<jsp:include page="left.jsp" />
	</td>
	<td>
		<form action="../uploadVideo">
			Upload video:<method="POST" enctype="multipart/form-data">
			<input type="file" id="video" name="profilePicture" accept="video/*">
			<input type="submit" value="Upload">
		</form>
	</td>
	</table>
</body>
</html>