<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css">

<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>

<title>자유게시판</title>

</head>

<body>

<form method="post" id="article" action="<%=request.getContextPath()%>/article.do">
<div class="container">
<table class="table table-striped">
	<thead>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>날짜</th>
		<th>조회수</th>
		<th>댓글수</th>
	</tr>
	<tr>
		<th colspan="5"><h4>게시글을 등록하세요.</h4></th>
	</tr>
	</thead>
</table>
</div>
<p>
	<a href="<%=request.getContextPath() %>/auth/article_write.do" class="btn btn-default" role="submit">글쓰기</a>
</p>
<div class="text-center">
	<ul class="pagination">
		<li class="active"><a href="#">1</a></li>
		<li><a href="#">2</a></li>
		<li><a href="#">3</a></li>
		<li><a href="#">4</a></li>
		<li><a href="#">5</a></li>
	</ul>
</div>
 
</form>
</body>
</html>

