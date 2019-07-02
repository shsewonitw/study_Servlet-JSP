<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 활용 - 제어문(if)</title>
</head>
<body>

<%--  
	JSTL의 c:if 태그
	조건식의 결과에 따라 내부 코드의 실행 여부를 결정하는 태그
	
	var 속성
	- test 속성의 결과를 저장할 변수명을 작성합니다.
	- var 속성에 기술된 변수명을 EL을 사용하여 차후에 접근할 수 있습니다.
	scope 속성
	- test 속성의 결과를 저장할 var 변수를 어느 영역에 저장할지 
	  결정하는 속성
	- page, request, session, application 을 사용할 수 있음
	- 기본값은 page
--%>
<c:set var="number" value="15" />

<c:if test="${ number % 2 == 0 }" var="result" scope="page">
	<h3>짝수 입니다.</h3>
</c:if>

<c:if test="${ !result }">
	<h3>홀수 입니다.</h3>
</c:if>

<%--
	사용자가 입력한 age 파라메터의 값을 사용하여
	성인/미성년을 출력하세요(20세를 기준으로 함)
	- 만약 age 파라메터가 입력되지 않은 경우
	  에러메세지를 출력하세요.  
--%>

<c:if test="${ empty param.age }" var="isAgeEmpry">
	<h3>나이 정보를 age 파라메터로 전달해야 합니다.</h3>
</c:if>

<c:if test="${ !isAgeEmpry && param.age >= 20 }">
	<h3>당신은 성인입니다(${param.age}).</h3>
</c:if>

<c:if test="${ !isAgeEmpry && param.age < 20 }">
	<h3>당신은 미성년자입니다(${param.age}).</h3>
</c:if>


</body>
</html>















