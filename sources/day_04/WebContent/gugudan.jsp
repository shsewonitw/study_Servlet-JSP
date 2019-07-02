<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 <%--
	<% for(int i = 2 ; i < 10 ; i++){ %>
		<h1> <%= i %>단을 출력합니다.</h1>
		<ul>
		<% for(int j = 1 ; j < 10 ; j++){ %>
		
			<li><%= i %> * <%= j %> = <%= i*j %></li>
		
		<% } %>
		</ul>
		<br/>
	<% } %>
--%>


<% for(int i = 2 ; i < 10 ; i++){
		out.println("<h1>"+i+"단을 출력합니다.</h1>");
		for(int j = 1 ; j < 10 ; j++){
			out.println(i+" * "+j+" = "+i*j+"<br/>");
		}
		out.println("<br/>");
	}
%>



</body>
</html>