<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정확인</title>
</head>
<body>

<h3>게시글 수정이 완료되었습니다.</h3>

<p><a href='<%= request.getContextPath() %>/auth/article_detail.do?article_id=${requestScope.article_id}'>게시글 확인</a></p>
<p><a href='<%= request.getContextPath() %>/main.do'>메인화면으로 이동</a></p>

</body>
</html>






