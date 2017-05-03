<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page errorPage="errorPage.jsp" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search results</title>
<meta charset="utf-8" />
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />

	<!--     Fonts and icons     -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />

	<!-- CSS Files -->
    <link href="/Youlose/static/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/Youlose/static/css/material-kit.css" rel="stylesheet"/>
    <style>
    	.search-section {
			margin-top: 40px;   	
    	}
    </style>

</head>
<body>
<jsp:include page="header.jsp" />
<table class="search-section">
	<td>
		<jsp:include page="left.jsp" />
	</td>
	<td>
		<h1>Results for <c:out value="${searchedWord}"/></h1>
		<c:forEach var ="current" items="${results}">
			<div>
				<form action="openVideo" method="get">
					<input type="hidden" value="${current.value.path }" name="videoPath">
					<img src="image/thumbnail.png" alt="thumbnail" style="width:120px;heigth;">
					<c:out value = "${current.key }"/>
					<input type="submit" value="Watch Now" class="btn btn-simple btn-primary btn-lg"  style="color:white">
			</div>
			
		</c:forEach>
	</td>
</table>
</body>
</body>
</html>