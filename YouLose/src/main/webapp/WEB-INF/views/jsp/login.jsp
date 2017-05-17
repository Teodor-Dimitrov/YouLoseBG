<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	        <%@ page errorPage="errorPage.jsp" %>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />

	<!--     Fonts and icons     -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />

	<!-- CSS Files -->
    <link href="/Youlose/static/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/Youlose/static/css/material-kit.css" rel="stylesheet"/>

</head>

<body>
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
							<form class="form" action="login" method="post">
								<p class="text-divider">Please, Log in</p>
								<div class="content">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">face</i>
										</span>
										<input type="text" required="required" name="email" type="email" class="form-control" placeholder="Email...">
									</div>

									<div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">lock_outline</i>
										</span>
										<input type="password" placeholder="Password..." name="password" required="required"class="form-control" />
									</div>
									
								</div>
								<div class="footer text-center">
									<input type="submit"  value="Log in" class="btn btn-simple btn-primary btn-lg">
									
								</div>
							</form>
				
								<form action="register" method="get">
							<div class="footer text-center">
									<input type="submit"  value=" Register here" class="btn btn-simple btn-primary btn-lg">
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