<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 활용 - 반복문</title>

<!-- 
<style type="text/css">
	.odd_dan{
		color: red;
	}
	
	.even_dan{
		color: blue;
	}
</style>
 -->
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
<c:set var="i">2</c:set>
<c:set var="j">1</c:set>
<c:forEach begin="2" end="9">

	${style = i%2 == 0 ? 'even_dan':'odd_dan' ; ''}

	<h1 class="${style}">${i}단 출력</h1>
	<c:forEach begin="1" end="9">
		<h3 class="${style}">${i} * ${j} = ${i*j}</h3>
		${j= j+1; ''}
	</c:forEach>	
	${i= i+1; ''}
	${j= 1; ''}
	<br/></font>
</c:forEach>




</body>
</html>