<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%
session.invalidate();
synchronized (application) {
	Integer accessUser = (Integer)application.getAttribute("accessUser");
	try {
	accessUser--;
	} catch(Exception e){
		;
	}
	application.setAttribute("accessUser", accessUser);	
}
response.sendRedirect("./main.jsp");

%>
</body>
</html>