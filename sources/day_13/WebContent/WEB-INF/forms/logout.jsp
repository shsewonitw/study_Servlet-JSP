<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃 화면</title>
</head>
<body>

<h2>정말 로그아웃 하시겠습니까?</h2>
<form action="<%= request.getContextPath() %>/auth/logout.do" method="post">
	<input type="submit" value="예">
</form>
<a href="<%= request.getContextPath()%>/main.do"><button>아니요</button></a>
</body>
</html>