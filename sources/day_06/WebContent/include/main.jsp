<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/include/error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
</head>
<body>
<%@ include file="/include/global_variables.jspf" %>
<h2><%= msg %></h2>

<%
	request.setCharacterEncoding("utf-8");
	String strAge = request.getParameter("age");
	int age = Integer.parseInt(strAge);
	
	String strData = "성인 입니다.";
	if( age < 20 )
		strData = "미성년자 입니다.";
	
%>

<jsp:include page="/include/contents.jsp">
	<jsp:param name="data" value="<%= strData %>"/>
</jsp:include>

<h1><%= request.getParameter("data") %></h1>

<%@ include file="/include/sub.jsp" %>
</body>
</html>