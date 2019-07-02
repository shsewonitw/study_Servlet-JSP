<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>


<%
String id = null;
Cookie[] cookies = request.getCookies();
for(int i = 0 ; cookies != null && i < cookies.length ; i++) {
	if(cookies[i].getName().equals("id"))
		id = cookies[i].getValue();
}

%>
<form action="./loginCheck.jsp" method="post">
	<table>
		<tr>
		<%if(id == null){ %>
			<td>아이디</td> <td><input type="text" name="id"></td>
		<%} else{ %>
		
			<td>아이디</td> <td><input type="text" name="id" value="<%=id %>"></td>
		<%} %>
		</tr>
		<tr>
			<label><td>패스워드</td> <td><input type="password" name="password"></td></label>
		</tr>
		<tr>
			<td><input type="submit" value="로그인"></td>
			<%if(id == null){ %>
				<td><label><input type="Checkbox">아이디 저장</label></td>
			<%} else{ %>
				<td><label><input type="Checkbox" checked>아이디 저장</label></td>
			<%} %>	
		</tr>
	</table>
</form>
</body>
</html>