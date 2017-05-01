<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
</head>
<body bgcolor="lightgreen">
<jsp:include page="header.jsp" />
<table >
		<td>
			<jsp:include page="left.jsp" />
		</td>
		<td>
				<img src="${ user.profilePicture}" alt="profile picture" style="width:75px;heigth;">
  					<c:out value="${ user.name}" />
   					<img src="subscribers.jpeg" alt="subscribers" style="width:30px;">
   					<c:out value="${ user.userPlaylist.subsrib.size()}" />
  					<form action="viewAll" method="get" name = "${ user.name}">
						<input type="submit" style="background-color:red;" value="View all">
					</form>
	<form action="changeUsername" method="post">
		<h3>Change username:</h3>
		<input type="submit" value="Change">
	</form>
	<form action="../changeEmail" method="post">
		<h3>Change e-mail:</h3>
		<input type="submit" value="Change">
	</form>
	<form action="../changePassword" method="post">
		<h3>Change password:</h3>
		<input type="submit" value="Change">
	</form>
		</td>
	</table>
</body>
</html>