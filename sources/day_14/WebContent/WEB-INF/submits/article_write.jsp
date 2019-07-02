<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성 완료</title>
</head>
<body>
<h3> 게시글 작성이 완료 되었습니다. </h3>
<p><a href='<%= request.getContextPath() %>/auth/article.do'>게시판으로 이동</a></p>
<p><a href='<%= request.getContextPath() %>/main.do'>메인화면으로 이동</a></p>

</body>
</html>