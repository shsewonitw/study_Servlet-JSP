<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h3>게시글 작성</h3>

<form action="<%= request.getContextPath() %>/auth/articleWrite.do" method="post">
<table>
	<tr>
		제목
	</tr>
	<tr>
		<input type="text" name="title">
	</tr>
</table>
<table>
	<tr>
		내용
	</tr>
	<tr>
		<input type="text" name="content">
	</tr>
	<tr>
		<input type="submit" value="작성">
	</tr>
</table>
</form>
</body>
</html>