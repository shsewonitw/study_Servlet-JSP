<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정 화면</title>
</head>
<body>

<form action="<%= request.getContextPath() %>/auth/comment_update.do" method="post">
	<input type="hidden" name="comment_id" value="${param.comment_id}">
	<input type="hidden" name="article_id" value="${param.article_id}">
	<input type="text" name="content" value="${requestScope.comment.content }">
	<input type="submit" value="수정">
</form>


<p><a href='<%=request.getContextPath()%>/auth/article_detail.do?article_id=${ param.article_id }'>게시글로 돌아가기</a></p>

</body>
</html>