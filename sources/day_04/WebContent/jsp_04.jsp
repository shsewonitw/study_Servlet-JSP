<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
</head>
<body>

<form action="./jsp_05.jsp" method="post">
	<table>
		<caption>로그인</caption>
		<tr>
			<th>ID</th>
			<th><input type="text" name="id" required></th>				
		</tr>
		<tr>
			<td>PW</td>
			<td><input type="password" name="password" required></td>			
		</tr>
		<tr>
			<th colspan="2"><input type="submit" value="로그인">
			<label>아이디 저장<input type="checkbox" name="save_id"></label></th>
		</tr>
	</table>
</form>

</body>
</html>






