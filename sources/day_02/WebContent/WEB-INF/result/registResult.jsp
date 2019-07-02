<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>회원가입 유효성 체크 결과</h2>

	<br />ID: <%= request.getParameter("id") %> (<%= request.getAttribute("idCheck") %>)
	<br />PW: <%= request.getParameter("pw") %> (<%= request.getAttribute("pwCheck") %>)
	<br />NAME: <%= request.getParameter("name") %> (<%= request.getAttribute("nameCheck") %>)
	<br />AGE: <%= request.getParameter("age") %> (<%= request.getAttribute("ageCheck") %>)
	<br />TEL: <%= request.getParameter("tel") %> (<%= request.getAttribute("tel[Check") %>)

</body>
</html>