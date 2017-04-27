<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>header</title>
</head>
<body bgcolor="lightgreen">
	<c:set var="button1" scope="session" value="Home" />
	<c:set var="button2" scope="session" value="Log out" />
	<c:set var="button3" scope="session" value="Upload" />
	<c:set var="link1" scope="session" value="index" />
	<c:set var="link2" scope="session" value="logout" />
	<c:set var="link3" scope="session" value="uploadVideo" />
	<c:if test="${user == null}">
			<c:set var="button1" scope="session" value="Register" />
			<c:set var="button2" scope="session" value="Log in" />
			<c:set var="button3" scope="session" value="Upload" />
			<c:set var="link1" scope="session" value="register" />
			<c:set var="link2" scope="session" value="login" />
			<c:set var="link3" scope="session" value="login" />
	</c:if>
	<div align="right">
		<c:if test="${user != null}">
			<c:out value="${username}" />
			</c:if>
		<table>
			<tr>
			
				<td>
					<form action="${link1}" method="get">
						<input type="submit" value="${button1}">
					</form>
				</td>
				<td>
					<form action=" ${link2} " method="get">
						<input type="submit" value="${button2} ">
					</form>
				</td>
				<td>
					<form action=" ${link3}" method="get">
						<input type="submit" value="${button3} ">
					</form>
				</td>
			</tr>
		</table>
		
	</div>

	<table>
		<tr>
			<td><img src="youlose.png" alt="logo"
				style="width: 75px; height:;"></td>
			<td><font face="Helvetica Rounded Bold" size="7">YouLose</font>
			</td>
			<td>
				<form action="search" method="post">
					<input type="text" placeholder="Search" name="serchWord" required="required" style="width: 250px;">
					 <select name="searched" style="width: 150px;">
						    <option value="video">Videos</option>
						    <option value="user">Users</option>
						    <option value="playlist">Playlists</option>
					  </select>
					<input type="submit" value="Search"> 
				</form>
			</td>
		</tr>
	</table>
	<hr>

</body>
</body>
</html>