<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page errorPage="errorPage.jsp" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Subscribers</title>
<meta charset="utf-8" />
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />

	<!--     Fonts and icons     -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />
	<!-- CSS Files -->
    <link href="./static/css/bootstrap.min.css" rel="stylesheet" />
    <link href="./static/css/material-kit.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="header.jsp" />

<table>
		<td>
			<jsp:include page="left.jsp" />
		</td>
		<td>
			<h1>Results for Subscriptions</h1>
			<c:if test="${subscribers.isEmpty()}">
			<h1>You are not subscribed</h1>
			</c:if>
				<c:forEach var ="current" items="${subscribers}" >
	 				<img src="${ current.profilePicture}" alt="profile picture" style="width:75px;heigth;">
  					<c:out value="${ current.name}" />
   					<img src="subscribers.jpeg" alt="subscribers" style="width:30px;">
   					<c:out value="${current.userPlaylist.subsrib.size()}" />
  					<form action="subscribe" method="get" name = "${ current.subscribers.name}">
						<input type="submit" style="background-color:red;" value="Subscribe">
					</form>
				</c:forEach>
		</td>
</table>

</body>
</html>