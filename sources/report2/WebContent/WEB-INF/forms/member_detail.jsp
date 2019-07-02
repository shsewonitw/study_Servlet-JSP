<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 확인</title>
</head>

<body>

	<h3>'${ login_member.name }' 님의 개인정보 확인 페이지 입니다.</h3>
	
	<table>
		<tr>
			<th>ID</th>
			<td>${ login_member.member_id }</td>
		</tr>		
		<tr>
			<th>NAME</th>
			<td>${ login_member.name }</td>
		</tr>
		<tr>
			<th>GENDER</th>
			<td>${ login_member.genderString }</td>
		</tr>
		<tr>
			<th>AGE</th>
			<td>${ login_member.age eq 0 ? '나이 입력안함' : login_member.age }</td>
		</tr>
		<tr>
			<th>BIRTH</th>
			<td>${ login_member.birthString }</td>
		</tr>
		<tr>
			<th>TEL</th>
			<td>${ empty login_member.tel ? '연락처 입력안함' : login_member.tel }</td>
		</tr>
		<tr>
			<th>ADDRESS</th>
			<td>${ empty login_member.address ? '주소 입력안함' : login_member.address }</td>
		</tr>
		<tr>
			<th>REGIST_DATE</th>
			<td>${ login_member.regist_dateString }</td>
		</tr>
		<tr>
			<th>LAST_ACCESS_TIME</th>
			<td>${ login_member.last_access_timeString }</td>
		</tr>
		<tr>
			<th colspan="2">
				<a href='${pageContext.request.contextPath}/auth/member_update.do'>개인정보 수정화면 이동</a>
			</th>
		</tr>
		<tr>
			<th colspan="2">
				<a href='${pageContext.request.contextPath}/main.do'>메인화면으로 이동</a>
			</th>
		</tr>
	</table>
	
<table border="1px">
<caption>최근 게시물</caption>
<tr>
	<th>게시글번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성날자</th>
	<th>조회수</th>
</tr>
<br/><br/><br/>
<c:if test="${ empty articleFive }" var="r" >
	<tr>
	<th colspan="5"><h4>작성한 게시글이 존재하지 않습니다.</h4></th>
	</tr>
</c:if>

<c:if test="${ not r }">
	
	<c:forEach items="${ articleFive }" var="article">
		<tr>
		<th>${ var = article.article_id }</th>
		<th><a href='<%=request.getContextPath()%>/auth/article_detail.do?article_id=${ article.article_id }'>${ article.title }</a></th>
		<th>${ article.name }</th>
		<th>${ article.write_timeString }</th>
		<th>${ article.read_count }</th>
		</tr>
	</c:forEach>
	
</c:if>
</table>

<br/><br/><br/>
<c:if test="${ not empty commentFive }" var="r">
		<!-- 댓글 목록을 출력.... -->
		<table border="1">
		<caption>최근 댓글</caption>
			<c:forEach items="${ commentFive }" var="comment">
				<tr>
					<td>${ comment.member_id }</td>
					<td>${ comment.content }</td>
					<td>${ comment.write_timeString }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${ not r }">
		<h4> 최근 댓글이 존재하지 않습니다.</h4>
	</c:if>
</body>
</html>




