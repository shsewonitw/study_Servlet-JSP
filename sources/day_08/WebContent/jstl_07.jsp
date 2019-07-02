<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL활용 - 제어문</title>
</head>
<body>

<%--
	JSTL의 c:if 태그
	조건식의 결과에 따라 내부 코드의 실행 여부를 결정하는 태그
	test 속성에 EL을 사용하여 참과 거짓으로 분리되는 조건식을 작성합니다.
	
	주의사항!
	else 태그가 없기 때문에 대수개의 조건은 다수개의 c:if 태그를 사용합니다.
	(c:choose 태그를 사용하여 대체할 수 있습니다.)
 --%>
 
<c:set var="number" value="16" />

<c:if test="${number % 2 == 0 }">
	<h3>짝수 입니다.</h3>
</c:if>

<c:if test="${number % 2 == 1 }">
	<h3>홀수 입니다.</h3>
</c:if>


</body>
</html>