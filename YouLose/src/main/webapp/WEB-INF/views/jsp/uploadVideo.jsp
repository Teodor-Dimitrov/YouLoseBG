<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload video</title>
</head>
<jsp:include page="header.jsp" />
<h1>Upload video here</h1>
<form method="POST" enctype="multipart/form-data">
	<input type="file" id="video" name="neVideo" accept="video/*">
	<input type="submit" value="Upload now">
</form>
<h2>File uploaded with name = ${filename}</h2>	
<img src="video/${filename}">
</body>
</html>