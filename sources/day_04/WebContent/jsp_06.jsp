<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
</head>
<body>

<%-- <form action="./jsp_05.jsp" method="post"> --%>
<%-- 
	request 기본 객체의 getContextPath() 메소드 
	- 현재 웹 어플리케이션의 이름을 반환하는 메소드
	- day_04 프로젝트인 경우 /day_04 의 문자열 값을 반환합니다.
	- 클라이언트 사이드 스크립트에서 링크를 지정하는 경우
	  WebContent 디렉토리를 기준으로 경로를 작성하는 경우 유용합니다.
--%>
<form action="<%= request.getContextPath() %>/result/jsp_07.jsp" method="post">
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






