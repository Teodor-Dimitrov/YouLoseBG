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
		<jsp:include page="left.jsp" />
		</td>
	<td>
	<h1>Register Here</h1>
	<form action="register" method="post">
		Username: <input type="text" placeholder="enter username"
			name="username" required="required"> </br>
		E-mail: <input type="email" placeholder="enter email" name="email"
			required="required"></br> 
		Password: <input type="password"
			placeholder="enter password" name="password" required="required"></br>
		Confirm Password: <input type="password"
			placeholder="enter password again" name="password2"
			required="required"></br>
		<input type="submit", value = "Register"><br>
		
	</form>
	<form action="login" method="get">
				<input type="submit" value="Already a registered user? Login here."></br></br>
		</form>
</td>
</table>
</body>
</html>