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
<body>
<body bgcolor="lightgreen">
<jsp:include page="header.jsp" />
	<table>
		<td>
			<jsp:include page="left.jsp" />
		</td>
		<td>
			<h1>Results for ${sessisonScope.searchedUsers}</h1>
				<c:forEach var ="current" items="${session.searchedUsers}" begin="0" end="searchedUsers.size()-1">
	 				<img src="${ searchedUsers.profilePicture}" alt="profile picture" style="width:75px;heigth;">
  					<c:out value="${ searchedUsers.name}" />
   					<img src="subscribers.jpeg" alt="subscribers" style="width:30px;">
   					<c:out value="${ searchedUsers.userPlaylist.subsrib.size()}" />
  					<form action="follow" method="post" name = "${ searchedUsers.name}">
						<input type="submit" style="background-color:red;" value="Subscribe">
					</form>
				</c:forEach>
		</td>
	</table>
</body>
</body>
</html>