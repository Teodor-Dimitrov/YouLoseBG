<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
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
	<h1>Register Here</h1>
	<form action="../register" method="post">
		Username: <input type="text" placeholder="enter username"
			name="username" required="required"> </br>
		E-mail: <input type="email" placeholder="enter email" name="email"
			required="required"></br> 
		Password: <input type="password"
			placeholder="enter password" name="password" required="required"></br>
		Confirm Password: <input type="password"
			placeholder="enter password again" name="password2"
			required="required"></br>
		Profile picture:<method="POST" enctype="multipart/form-data">
	<input type="file" id="profilePic" name="profilePicture" accept="image/*">
	<input type="submit" value="Upload">
		</select></br> 
		
	</form>
	<form action="../login" method="get">
				<input type="submit" value="Already a registered user? Login here."></br></br>
		</form>
</td>
</table>
</body>
</html>