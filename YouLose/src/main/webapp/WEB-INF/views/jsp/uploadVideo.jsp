<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
       <%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload video</title>
</head>
<body>
<jsp:include page="header.jsp" />
<h1>Upload video here</h1>
<table>
		<td>
			<jsp:include page="left.jsp" />
		</td>
		<td>
			<form method="POST" enctype="multipart/form-data">
			<input type="file" id="video" name="newVideo" accept="video/*">
			<input type="submit" value="Upload now">
			</form>
			<h2>Uploading of ${filename} successful!</h2>	
		</td>
</table>
</body>
</html>