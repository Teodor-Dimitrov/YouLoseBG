<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
         <%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${videoName}</title>
</head>
<body>
<jsp:include page="header.jsp" />
<table >
		<td>
			<jsp:include page="left.jsp" />
		</td>
		<td>
			<jsp:include page="player.jsp" />
			
		<c:forEach var ="video" items="${videoComments}" begin="0" end="videoCommnets.size()-1">
  		 <c:out value="${comment.userID.name }": /> <c:out value="${comment.postedDate }": /></br>
  		  <c:out value="${comment.content }"/></br>
		</c:forEach>
	</td>
</table>
</body>
</html>