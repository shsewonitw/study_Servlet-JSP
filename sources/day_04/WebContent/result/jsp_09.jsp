<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>취미 입력 결과창</title>
</head>
<body>

<%
	// 체크박스와 같은 경우 동일한 name으로 다수개의 value가 전달됩니다.
	// 이러한 경우 request 객체의 getParameterValues 메소드를 사용하여
	// 동일한 이름으로 전달된 다수개의 값을 읽어올 수 있습니다.
	String [] arr = request.getParameterValues("id");
%>
<form action="#" method="post">
	<table>
		<caption>선택한 취미는??</caption>
		<tr>
			<%
				for(int i = 0 ; i < arr.length ; i++){
					out.println("<th>"+arr[i]+"</th>");
				}
			%>
			
			<% for(int i = 0 ; i < arr.length ; i++) { %>
					<th><%= arr[i]%></th>
			<% } %>			
		</tr>
	</table>
</form>

</body>
</html>






