<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body style="
    padding-left: 506px;">
	<h1>Social Media Application!</h1>

	<P>${errorMsg}</p>

	<form action="verifyUser" method="post">
		<h3>User Name:</h3> <input type="text" name="userName" text-align="centre"><br>
		<h3>Password:</h3><input type="password" name="password" text-align="centre"><br> <h3><input
			type="submit" value="Login" text-align="centre"></h3>
	</form>
</body>
</html>