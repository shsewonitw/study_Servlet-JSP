<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP의 스크립트 요소</title>
</head>
<body>

<%-- 
	JSP의 스크립트 요소
	- 스크립트릿 : 자바 코드의 실행을 위해서 사용되는 스크립트 표현
	  (<% 자바코드 ... %>)
	- 표현식 : 특정 변수의 값 또는 연산의 결과, 메소드의 실행 결과를
	          현재위치에 출력할 때 사용되는 스크립트 표현
	  (표현식 사용 시 주의사항 ; 은 생략합니다.)        
	  (<%= 자바변수, 연산식, 메소드 호출 %>)
	- 선언부 : 현재 JSP 문서에서 사용할 메소드의 선언
	  (<%! 메소드 선언 %>)
--%>

<%!
	// 선언부 정의
	// - 메소드를 선언할 수 있는 스크립트 표현
	// - 현재 JSP 문서 내부에서 활용할 메소드를 선언합니다.
	public int sum(int n1, int n2) {
		return n1 + n2;
	}
%>

<%
	// 스크립트릿 선언
	// - 변수의 선언 및 초기화 또는 연산을 수행하는 영역
	// - 스크립트릿 내부에서 선언된 변수들은 표현식을 사용하여
	//   HTML 문서에 출력될 수 있습니다.
	int n1 = 10;
	int n2 = 7;
%>

<%-- 
	표현식
	- 일반적으로 스크립트릿 내부에서 선언된 변수를 
	  HTML 문서 내부에 출력하기 위한 용도로 활용되는 스크립트 표현
	- 선언부 내부에 선언된 메소드를 사용할 수 있습니다.
--%>
<h3><%= n1 %> + <%= n2 %> = <%= sum(n1, n2) %></h3>



</body>
</html>






