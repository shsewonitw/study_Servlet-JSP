<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	

	String login_id = null;
	Integer login_member_count = null;
	
	if( session != null ) {
		login_id = (String)session.getAttribute("login_id");
		login_member_count = (Integer)application.getAttribute("login_member_count");
	}

	response.setContentType("text/html;charset=utf-8");
%>



	<h2>메인 메뉴</h2>
	 <% if( login_id != null && login_member_count != null ) {%>
			<h3>현재 접속된 클라이언트의 수 : <%= login_member_count %> </h3>
		<% } else if( login_id != null && login_member_count == null ) {%>
			<h3>현재 접속된 클라이언트의 수 : 0</h3>
		<% } %>
		<ul>
		<% if( login_id != null ) {  %>
			<li><a href='./auth/list.test'>회원목록 보기</a></li>
			<li><a href='./auth/logout.test'>로그아웃</a></li>
		<%  } else { %>		
			<li><a href='./regist.test'>회원가입</a></li>
			<li><a href='./login.test'>로그인</a></li>
		<% } %>
		</ul>
</body>
</html>