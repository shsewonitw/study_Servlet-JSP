<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>

<% 
	Integer accessUser= null;
	synchronized(application){
		accessUser = (Integer)application.getAttribute("accessUser");
	}
	if(accessUser == null){
		accessUser = 0;
		application.setAttribute("accessUser", accessUser);
	}
	
	
	String id = (String) session.getAttribute("login_id");
	String name = (String) session.getAttribute("login_name");
%>

<h2> 메인화면 </h2>
<h2> 현재 접속된 클라이언트 수 : <%= accessUser %></h2>
<ul>
	<%if(id!=null) {%>
	<li><a href="./select.jsp">회원 목록 보기</a></li>
	<li><a href="./logout.jsp">로그아웃</a></li>
	<% }else{ %>
	<li><a href="./login.jsp">로그인</a></li>
	<li><a href="./regist.jsp">회원가입</a></li>
	<%} %>
</ul>

</body>
</html>