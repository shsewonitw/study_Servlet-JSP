<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정 화면</title>
</head>
<body>

<form action="<%= request.getContextPath() %>/auth/article_update.do" method="post">
	<input type="hidden" name="article_id" value="${detailArticle.article_id }">
	<table>
		<caption>게시글 수정</caption>
		<tr>
			<th>작성자</th>
			<td>${ login_member.name }(${ login_member.member_id })</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="${detailArticle.title}" required></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="30" cols="30" name="content" required>${detailArticle.content} </textarea></td>
		</tr>
		<tr>
			<th colspan="2"><input type="submit" value="수정"></th>
		</tr>
	</table>
</form>

<p><a href='<%=request.getContextPath()%>/main.do'>메인화면</a></p>
<p><a href='<%=request.getContextPath()%>/auth/article_detail.do?article_id=${ article.article_id }'>게시글로 돌아가기</a></p>

</body>
</html>