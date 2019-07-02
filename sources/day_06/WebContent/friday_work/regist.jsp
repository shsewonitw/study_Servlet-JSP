<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="./registCheck.jsp" method="post">
	<table>
		<tr>
			<td>아이디</td> <td><input type="text" name="id"></td>
		</tr>
		<tr>
			<label><td>패스워드</td> <td><input type="text" name="password"></td></label>
		</tr>
		<tr>
			<label><td>이름</td> <td><input type="text" name="name"></td></label>
		</tr>
		<tr>
			<label><td>나이</td> <td><input type="text" name="age"></td></label>
		</tr>
		<tr>
			<label><td>전화번호</td> <td><input type="text" name="tel"></td></label>
		</tr>
		<tr>
			<label><td>주소</td> <td><input type="text" name="address"></td></label>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="가입"></td>
		</tr>
	</table>
</form>

</body>
</html>