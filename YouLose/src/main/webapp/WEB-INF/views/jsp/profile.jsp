<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<form action="../home" method="get">
				<input type="submit" value="Home"></br></br>
		</form>
		<form action="../profile" method="get">
				<input type="submit" value="My profile"></br></br>
		</form>
		<form action="../forLater" method="get">
				<input type="submit" value="Watch later"></br></br>
		</form>
		<form action="../watched" method="get">
				<input type="submit" value="Watched"></br></br>
		</form>
		<form action="../liked" method="get">
				<input type="submit" value="Liked"></br></br>
		</form>
		<form action="../subscriptions" method="get">
				<input type="submit" value="Subscriptions"></br></br>
		</form>
		</td>
		<td>
			<img src="image/<%session.getAttribute("username"); %>">
			<h1><%session.getAttribute("username"); %></h1></br>
			<h3>Change profile picture:</h3>
	<form method="POST" enctype="multipart/form-data">
		<input type="file" id="profilePic" name="profilePicture" accept="image/*">
		<input type="submit" value="Upload">
	</form></br>
	<form action="../changeUsername" method="post">
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