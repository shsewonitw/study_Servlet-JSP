<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 활용 - 반복문</title>

<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".odd_dan").css("color","green");
		$(".even_dan").css("color","pink");	
	})
	
</script>
</head>
<body>

<h3>구구단</h3>

<%-- 
c:forEach 태그의 var 속성
현재 제어되고 있는 값을 저장할 변수명을 지정하는 속성
var="num" begin="1" end="3" 이러한 경우 
num 변수는 첫번째 반복시 1의 값
마지막 반복시 3의 값을 가집니다.
 --%>

<c:forEach var="i" begin="2" end="9">

	${style = i%2 == 0 ? 'even_dan':'odd_dan' ; ''}

	<h1 class="${style}">${i}단 출력</h1>
	<c:forEach var="j" begin="1" end="9">
		<h3 class="${style}">${i} * ${j} = ${i*j}</h3>
		${j= j+1; ''}
	</c:forEach>	
	${i= i+1; ''}
	${j= 1; ''}
	<br/></font>
</c:forEach>




</body>
</html>