<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러처리 페이지</title>
</head>
<body>
 <h3>에러가 발생했습니다.</h3>
 
 <h3>관리자에게 문의하세요. (발생한 에러 : <%= exception.getClass().getName() %>)</h3>
 
 
</body>
</html>