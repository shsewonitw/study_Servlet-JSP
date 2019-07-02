<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="tje.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 처리 화면</title>
</head>
<body>

<%-- 
	Member member = new Member() 
	request.setAttribute("member",member);
--%>
<jsp:useBean id="member" class="tje.model.Member" scope="request"/>
<jsp:setProperty property="*" name="member"/>

<%
	Member m = (Member)request.getAttribute("member");
	out.println(m.getId() + "<br/>");
	out.println(m.getName() + "<br/>");
%>
<%
/*
	String strId = request.getParameter("id").trim();
	String strPw = request.getParameter("password").trim();
	String strName = request.getParameter("name").trim();
	String strAge = request.getParameter("age").trim();
	String strTel = request.getParameter("tel").trim();
	String strAddress = request.getParameter("address").trim();
	
	Member member = new Member();
	
	member.setId(strId);
	member.setPassword(strPw);
	member.setName(strName);
	member.setAge(Integer.parseInt(strAge));
	member.setTel(strTel);
	member.setAddress(strAddress);
	
	request.setAttribute("member",member);
*/
%>
</body>
</html>