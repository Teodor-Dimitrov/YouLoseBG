<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
       <%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>header</title>

</head>
<body  style="background-image: url('./static/img/bg2.jpeg'); background-size: cover; background-position: top center;">
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
						<input type="submit" value="${button1}" class="btn btn-simple btn-primary btn-lg" style="color:white">
					</form>
				</td>
				<td>
					<form action=" ${link2} " method="get">
						<input type="submit" value="${button2} "class="btn btn-simple btn-primary btn-lg"  style="color:white">
					</form>
				</td>
				<td>
					<form action=" ${link3}" method="get">
						<input type="submit" value="${button3} "class="btn btn-simple btn-primary btn-lg" style="color:white">
					</form>
				</td>
			</tr>
		</table>
		
	</div>

	<table>
		<tr>
			<td><img src="./static/img/youlose.png" alt="logo"
				style="width: 75px; height:;"></td>
			<td>
			<font style="color:grey"  face="Helvetica Rounded Bold" size="7">YouLose</font>
			
			</td>
			<td>
				<form action="search" method="get">
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