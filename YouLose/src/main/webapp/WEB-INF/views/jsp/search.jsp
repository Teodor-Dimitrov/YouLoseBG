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
    <link href="/MyProject/static/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/MyProject/static/css/material-kit.css" rel="stylesheet"/>

</head>
<body>
<jsp:include page="header.jsp" />
<table>
	<td>
		<jsp:include page="left.jsp" />
	</td>
	<td>
		<h1>Results for ${sessisonScope.searched}</h1>
	<c:forEach var ="current" items="${sess}" begin="0" end="searchedWordList.size()-1">
   <c:out value="$video" type = "video"/></br>
</c:forEach>
	</td>
</table>
</body>
</body>
</html>