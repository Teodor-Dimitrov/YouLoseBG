<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ page errorPage="errorPage.jsp" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Invalid login</title>
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
								<p class="text-divider" style="color:red"><b><i>Invalid username or password</i></b></p>
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
								
							</form>
							<form action="login" method="get">
							<div class="footer text-center">
									<input type="submit"  value="Log in" class="btn btn-simple btn-primary btn-lg">
								</div>
								</form>
								
							<div class="footer text-center">
							<p class="text-divider">Forgotten password? Enter your email/ username here: </p>
										<form action="forgottenPassword" method="post">
									
										<input type="text" required="required" name="usernameOrEmail" type="text" class="form-control" placeholder="Email / username">
									<input type="submit" value = "submit"></br>
									</form>
									</div>
								</div>
						</div>
					</div>
				</div>
			</div>
	</td>
	</table>
</body>
</html>