<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include 기능</title>
</head>
<body>

<h2>main 페이지의 내용 1</h2>

<%-- flush="true" : 서브페이지 로딩까지 기다리지 않고 기존의 버퍼 내용 먼저 출력  --%>
<jsp:include page="/include/jsp_26_sub.jsp" flush="true"/>

<h2>main 페이지의 내용 2</h2>

</body>
</html>