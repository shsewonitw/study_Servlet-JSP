<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃 결과 화면</title>
</head>
<body>

<h3>${ requestScope.logout_member.name }님의 로그아웃이 처리되었습니다.</h3>
<p><a href="<%= request.getContextPath() %>/main.do">메인화면으로 이동</a>
</body>
</html>