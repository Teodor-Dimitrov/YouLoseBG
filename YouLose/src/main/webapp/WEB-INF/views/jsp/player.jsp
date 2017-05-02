<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page errorPage="errorPage.jsp" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${videoPath}</title>
	<style>
		div#video_player_box{ width:550px; background:#000; margin:0px auto;}
		div#video_controls_bar{ background: #333; padding:10px;}
	</style>

</head>
<body>
<div id="video_player_box">
  <video id="my_video" width="550" height="300" controls>
    <source src="video/${videoPath}">
  </video>
 
</div>
</body>
</html>