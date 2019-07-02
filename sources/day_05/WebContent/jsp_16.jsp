<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>

<%-- 
	현재 페이지는 로그인을 위한 JSP 입니다.
	현재 접속된 사용자가 로그인이 되어있지 않은 상황이라면
	로그인 정보를 입력받을 수 있는 폼을 화면에 출력하고
	로그인 된 사용자라면 "이미 로그인 되어 있습니다." 메세지를 화면에 출력하세요
	(로그인 정보의 처리는 jsp_17.jsp 에서 수행합니다.)
	(로그인 정보는 session객체에 저장하며 login_id 속성에 로그인 된 
	 ID 값을 저장하여 처리합니다.)
	(로그인은 ID와 PW가 동일한 경우 로그인으로 처리) 
 --%>
<%
	String id = null;
	id = (String)session.getAttribute("id");
	
	if( id == null ){ %>
		
<form action="<%=request.getContextPath() %>/jsp_17.jsp" method="post">
<table>
	<caption>로그인</caption>
	<tr>
		<td>아이디</td><td><input type="text" name="id" required></td>
	</tr>
	<tr>
		<td>비밀번호</td><td><input type="password" name="password" required></td>
	</tr>
	<tr>
		<th colspan="2"><input type="submit" value="로그인"></th>
	</tr>
</table>
</form>
<%
	}
	
else { %>
	<h1><%= id  %>님은 이미 로그인 되었습니다.</h1>
		
		
<% } %>

</body>
</html>