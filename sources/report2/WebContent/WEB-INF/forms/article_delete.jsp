<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제 화면</title>
</head>
<body>

<form action="<%= request.getContextPath() %>/auth/article_delete.do" method="post">
	<input type="hidden" name="article_id" value="${param.article_id }">
	정말 삭제하시겠습니까?<br/>
	<input type="submit" value="예">		
</form>

<p><a href='<%=request.getContextPath()%>/main.do'>메인화면</a></p>
<p><a href='<%=request.getContextPath()%>/auth/article_detail.do?article_id=${ param.article_id }'>게시글로 돌아가기</a></p>

</body>
</html>