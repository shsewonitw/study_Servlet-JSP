<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login check</title>
</head>
<body>


<c:if test="${name ne null}" var="result">
	<h3>${name} 님 로그인성공</h3>
</c:if> 

<c:if test="${ !result }">
	<h3>로그인 실패!</h3>
</c:if>



</body>
</html>