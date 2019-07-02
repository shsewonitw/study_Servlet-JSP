<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 처리</title>
</head>
<body>

<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String msg = "";
	if(id.equals(password)){
		msg = "로그인 성공";
		session.setAttribute("id", id);
	}
	else
		msg = "로그인 실패";
	
	
%>

<h1><%= msg %></h1>
<h2><a href="<%= request.getContextPath() %>/jsp_16.jsp">돌아가기</h2>
</body>
</html>