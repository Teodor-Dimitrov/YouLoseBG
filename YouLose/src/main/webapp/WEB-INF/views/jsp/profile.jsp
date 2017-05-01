<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"><meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />

	<!--     Fonts and icons     -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />

	<!-- CSS Files -->
    <link href="./static/css/bootstrap.min.css" rel="stylesheet" />
    <link href="./static/css/material-kit.css" rel="stylesheet"/>
</head>

<jsp:include page="header.jsp" />
<table >
		<td>
			<jsp:include page="left.jsp" />
		</td>
		
		<td>
		 <div class="wrapper">
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
						<div class="card card-signup">
							<form class="form" action="register" method="post">
								<p class="text-divider"><b><i>Profile data</i></b></p>
								<div class="content">
								<div>
									<center><img src="/profilePics/${user.username}.jpg" alt="profile picture" style="width:120px;heigth;"></center>
  										<center><c:out value="${user.username} }" /></center></br>
  											<center><c:out value="Subscribers:" />
  											 <img src="subscribers.jpeg" alt="subscribers" style="width:30px;">
    										<c:out value="4" />
 									 		<form action="viewSubscribers" method="get" name = "${user.username }Subscribers">
											<input type="submit" style="background-color:red;" value="view all">
											</form>
													</center>				
								</div>
								</div>
							</form>
									<form action="changeProfilePicture" method="post">
									<div class="footer text-center">
							
										
										<input type="submit" value="Change profile picture"  class="btn btn-simple btn-primary btn-lg">
											<center><input  type="file" id="file" name="profilePicture" accept="image/*"></center>
											</div>
										</form>
							
							<form action="changeUsername" method="get">
							<div class="footer text-center">
									<input type="submit"  value="Change username"  class="btn btn-simple btn-primary btn-lg">
								</div>
								</form>
							<form action="changeEmail" method="get">
								<div class="footer text-center">
									<input type="submit"  value=" Change E-mail" class="btn btn-simple btn-primary btn-lg">
								</div>
							</form>
							<form action="changePassword" method="get">
								<div class="footer text-center">
									<input type="submit"  value=" Change password" class="btn btn-simple btn-primary btn-lg">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</td>
	</table>
</body>
</html>