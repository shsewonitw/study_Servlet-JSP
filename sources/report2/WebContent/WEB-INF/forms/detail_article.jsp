<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 화면</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>

<script type="text/javascript">
	// 자바스크립트 함수 정의
	// 댓글을 지우기위한 버튼이 클릭될 때
	// 실행될 함수
	// (비동기 통신 (AJAX) 방법을 사용하여 서버와 데이터를 송수신)
	function delete_comment(comment_id){
		$.ajax({
			// 비동기 방식으로 데이터를 전송할 주소
			url:"<%=request.getContextPath()%>/auth/delete_comment.do",
			// 메소드 호출 방식
			type:"post",
			// 서버에 전달할 데이터
			// JSON 포멧을 활용
			data:"{comment_id : '"+comment_id+"'}",
			// 서버로부터 전달받을 데이터의 유형
			dataType:"text",
			// 데이터 송수신이 성공한 경우 실행할 함수
			// (매개변수에는 서버에서 전송하는 값을 전달받을 수 있음)
			succenss:function(result){
				alert(result);		
			}
		})
	}
	
</script>

</head>
<body>

	<table border="1">
		<tr>
			<td>${ detailArticle.article_id }</td>
			<td>${ detailArticle.name }(${ detailArticle.member_id })</td>
			<td>${ detailArticle.title }</td>
			<td>${ detailArticle.write_timeString }</td>
			<td>${ detailArticle.read_count }</td>
		</tr>
		<tr>
			<td colspan="5">${ detailArticle.content }</td>
		</tr>

	</table>
	<c:if test="${requestScope.isArticleWriter}">
		
		[<a href="<%=request.getContextPath()%>/auth/article_update.do?article_id=${detailArticle.article_id}">수정</a>] 
		
		[<a href="<%=request.getContextPath()%>/auth/article_delete.do?article_id=${detailArticle.article_id}">삭제</a>]
	</c:if>
	<h3>댓글 ${commentSize}</h3>
	<form action="<%=request.getContextPath()%>/auth/write_comment.do"
		method="post">
		<input type="hidden" name="article_id"
			value="${ detailArticle.article_id }">
		<textarea rows="3" cols="20" name="content"></textarea>
		<input type="submit" value="댓글등록">
	</form>

	<c:if test="${ not empty commentList }" var="r">
		<!-- 댓글 목록을 출력.... -->
		<table border="1">
			<c:forEach items="${ commentList }" var="comment">
				<tr>
					<td>${ comment.member_id }</td>
					<td>${ comment.content }
				<c:if test="${ comment.member_id == sessionScope.login_member.member_id }">
					[<a href="<%=request.getContextPath()%>/auth/comment_update.do?comment_id=${comment.comment_id}&article_id=${detailArticle.article_id}">수정</a>] 
					
					[<a href="<%=request.getContextPath()%>/auth/comment_delete.do?comment_id=${comment.comment_id}&article_id=${detailArticle.article_id}">삭제</a>]
					
				</c:if>
					</td>
					<td>${ comment.write_timeString }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${ not r }">
		<h4>댓글이 존재하지 않습니다.</h4>
	</c:if>
	<p>
		<a href='<%=request.getContextPath()%>/main.do'>메인화면</a>
	</p>
	<p>
		<a href='<%=request.getContextPath()%>/auth/article.do'>자유게시판</a>
	</p>

</body>
</html>