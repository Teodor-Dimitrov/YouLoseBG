<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
       <%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	.search-engine{
		dipslay:inline-block;
		position:relative;
		width:60%;
		float:left;
	}
	.nav-bar-custom{
		display:inline-block;
		position:relative;
		width:40%;
		fload:left;
	}
	.nav-bar-custom table {
	    float:right;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>header</title>

</head>
<body  style="background-image: url('/Youlose/static/img/bg2.jpeg'); background-size: cover; background-position: top center;">
	<c:set var="button1" scope="session" value="Home" />
	<c:set var="button2" scope="session" value="Log out" />
	<c:set var="button3" scope="session" value="Upload" />
	<c:set var="link1" scope="session" value="index" />
	<c:set var="link2" scope="session" value="logout" />
	<c:set var="link3" scope="session" value="main" />
	<c:if test="${user == null}">
			<c:set var="button1" scope="session" value="Register" />
			<c:set var="button2" scope="session" value="Log in" />
			<c:set var="button3" scope="session" value="Upload" />
			<c:set var="link1" scope="session" value="register" />
			<c:set var="link2" scope="session" value="login" />
			<c:set var="link3" scope="session" value="login" />
	</c:if>
	<table class="search-engine">
		<tr>
			<td><img src="/Youlose/static/img/youlose.png" alt="logo"
				style="width: 175px; height:100px;"></td>
			
			<td>
				<form action="search" method="get">
					<input type="text" placeholder="Search" name="searchWord" required="required" style="width: 250px;">
					<input type="hidden" name="searched" value="Videos"> 
					<input type="submit" value="Search"> 
				</form>
			</td>
		</tr>
	</table>
	<div class="nav-bar-custom">
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
</body>
</html>