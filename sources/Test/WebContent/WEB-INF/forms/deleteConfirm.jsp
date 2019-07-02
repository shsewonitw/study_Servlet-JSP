<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tje.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${member.name}님 정말 지우시겠습니까!? </h1>
<form action="${pageContext.request.contextPath }/delete.test" method="post">
<input type="hidden" name="id" value="${member.id}">
<input type="submit" value="삭제">
<p><a href='${pageContext.request.contextPath }/detail.test?id=${member.id}'>상제정보화면으로 이동</a></p>
</form>
</body>
</html>