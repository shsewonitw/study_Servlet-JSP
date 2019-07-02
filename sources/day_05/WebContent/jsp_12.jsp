<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--
	page 디렉티브의 autoFlush 속성이 false로 지정된 경우
	버퍼의  flush는 남은 버퍼의 크기를 고려하여 진행되어야 한다.
	만약 버퍼의 용량이 남지 않는 경우 500 에러가 발생
 --%>

<%@ page buffer="1kb" autoFlush="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>버퍼의 활용</title>
</head>
<body>

<% 
	for( int i = 1 ; i < 5000 ; i++){
		out.print("<h3>");
		out.print(i);
		out.println("</h3>");
		
		out.print("<h2>");
		// 현재 출력 버퍼에 남은 크기를 의미
		out.print(out.getRemaining());
		out.println("</h2>");
		
		// 현재 남은 버퍼의 크기를 고려하여
		// 적절한 시점에 클라이언트로 현재 버퍼의 내용을 전달
		// (버퍼는 비워짐)
		if(out.getRemaining() < 50)
			out.flush();
		
	}
%>


</body>
</html>