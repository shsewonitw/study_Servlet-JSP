<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
</head>
<body>

<h3>${ sessionScope.login_member.name }님 안녕하세요</h3>
<p><a href="<%= request.getContextPath() %>/main.do">메인화면으로 이동</a>
</body>
</html>