<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL의 활용</title>
</head>
<body>

<%-- 
	EL(표현언어)
	JSP를 사용하여 동적인 페이지를 작성하는 경우
	기존의 자바코드가 HTML 코드와 혼용되어 사용되기 때문에
	전체적인 내용을 한눈에 파악하기 어렵고, 작성하기에 불편합니다.
	
	표현언어를 통해서 JSP의 기본영역 객체인 request, session, application, page 에서
	손쉽게 값을 추출할 수 있습니다.
	그리고 컬렉션 객체에서 손쉽게 데이터에 접근할 수 있는 방법을 제공하는 언어입니다.
 --%>

<%-- 간단한 연산의 결과를 표현식과 EL을 사용하여 출력하는 예제 --%>
<h3> 표현식을 사용한 출력 : <%= 10 + 5 %></h3>
<h3> EL을 사용한 출력 : ${ 10+5 }</h3>

<%-- 표현식을 사용하여 클라이언트가 전달한 ID 파라미터값을 출력하세요 --%>
<h3>표현식을 사용 : <%= request.getParameter("id") %></h3>
<h3>EL을 사용 : ${param.id }</h3>

</body>
</html>