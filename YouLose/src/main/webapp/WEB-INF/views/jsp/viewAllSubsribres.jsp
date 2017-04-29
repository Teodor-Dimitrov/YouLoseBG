<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="lightgreen">
<jsp:include page="header.jsp" />
	<table>
		<td>
			<jsp:include page="left.jsp" />
		</td>
		<td>
			<h1>Subscribers for {sessisonScope.user}</h1>
				<c:forEach var ="current" items="${session.user.userPlaylist.subsrib.size()}" begin="0" end="session.user.userPlaylist.subsrib.size()-1">
	 				<img src="${ current.profilePicture}" alt="profile picture" style="width:75px;heigth;">
  					<c:out value="${ current.name}" />
   					<img src="subscribers.jpeg" alt="subscribers" style="width:30px;">
   					<c:out value="${ current.userPlaylist.subsrib.size()}" />
  					<form action="subscribe" method="get" name = "${ current.name}">
						<input type="submit" style="background-color:red;" value="Subscribe">
					</form>
				</c:forEach>
		</td>
	</table>
</body>
</body>
</html>