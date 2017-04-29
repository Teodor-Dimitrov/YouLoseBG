<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
       <%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change ${session.getWhatIsGoingToBeChanged }</title>
</head>
<body>
<jsp:include page="header.jsp" />
	<table >
	<td>
	<jsp:include page="left.jsp" />
	</td>
	<td>
<form action="/changePorifleData" method="post">
		 old ${session.getWhatIsGoingToBeChanged }: <input type="text" placeholder="enter old ${session.getWhatIsGoingToBeChanged }"
			name="old" required="required"> </br>
		new ${session.getWhatIsGoingToBeChanged }: <input type="text" placeholder="enter new ${session.getWhatIsGoingToBeChanged }" name="new"
			required="required"></br> 
		Confirm new ${session.getWhatIsGoingToBeChanged }: <input type="text" 
			placeholder="enter new ${session.getWhatIsGoingToBeChanged }" name="new2"
			required="required"></br>
		<input type="submit", value = "submit"><br>
		</form>
		</td>
		</table>
</body>
</html>