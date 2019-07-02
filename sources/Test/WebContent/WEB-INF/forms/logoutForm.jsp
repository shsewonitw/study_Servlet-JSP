<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSP 내부에서 JDBC 프로그래밍을 위한 java.sql 패키지를 import -->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath }/auth/logout.test" method="post">

	<input type="hidden" name="name" value="${name }">
	<h3>정말 로그아웃 하시겠습니까?</h3>
	<input type="submit" value="로그아웃">

</form>

<p><a href='${pageContext.request.contextPath }/main.test'>메인화면으로 이동</a></p>

</body>
</html>















