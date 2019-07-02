<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
	<table>
		<tr>
			<td>아이디 : </td><td><input type="text" name="id" /></td>
		</tr>
		<tr>
			<td>패스워드 : </td><td><input type="password" name="password" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"></td>
		</tr>
	</table>
</form>
</body>
</html>