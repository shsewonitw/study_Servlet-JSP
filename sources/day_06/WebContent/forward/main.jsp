<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
</head>
<body>
<%
	String strAge = request.getParameter("age");
	int age = Integer.parseInt(strAge);
	String forwardPage = null;
	
	if(age >= 20)
		forwardPage = "/forward/adult.jsp";
	else
		forwardPage = "/forward/child.jsp";
%>

<jsp:forward page="<%= forwardPage %>"></jsp:forward>
</body>
</html>