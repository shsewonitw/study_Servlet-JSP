<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">    
function thumbsUp() {
	var recipe_id = ${selectedRecipe.recipe_id};
	var login_member_id = "${login_member.member_id}";
	var params = "login_member_id="+login_member_id+"&recipe_id="+recipe_id;

	$.ajax({
		url:'<%=request.getContextPath()%>/auth/thumbs_up.do',
		type:"get",
		contentType: 
			"application/x-www-form-urlencoded; charset=utf-8",
		data: params,
		dataType:"text",
		success:function(result){
			
			if( eval(result) == 1) {
				var temp = $(".likeCount").text();
				temp *= 1;
				$(".likeCount").text(temp+1);
				$(".likeCountATag").attr("style","color:#8080f1;text-decoration:none;");
			} else if( eval(result) == 2 ){
				var temp = $(".likeCount").text();
				temp *= 1;
				$(".likeCount").text(temp-1);
				$(".likeCountATag").attr("style","color:white;text-decoration:none;");
			} else {
				alert("좋아요 기능 실패!");
			}
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("좋아요 기능 실패!");
		}
	});
}

// 댓글등록 ajax
function commentRegist() {
	
	var recipe_id = ${selectedRecipe.recipe_id};
	var login_member_id = "${login_member.member_id}";
	var comment_content = $("#comment_content").val();
	var recipeDetailUrl = window.location.href;
	var params = "login_member_id="+login_member_id+"&recipe_id="+recipe_id+"&comment_content="+comment_content;
	if(comment_content.length == 0){
		return;
	}
	$.ajax({
		url:'<%=request.getContextPath()%>/auth/recipe_detail.do',
		type:"post",
		contentType: 
			"application/x-www-form-urlencoded; charset=utf-8",
		data: params,
		dataType:"text",
		success:function(result){
			var splitResult = result.split('|+|');
			// comment_content = splitResult[0];
			// boolean result = splitResult[1];
			// last_insert_id = splitResult[2];
			if( splitResult[1] == false ) {
				alert("로그인 정보를 확인해주세요");
			} else {
				$("#noComment").attr("style","display:none");
				// $("#newComment").prepend("<li class='list-group-item'>"+result+"</li>");
				$("#newComment").prepend("<li class='list-group-item'>방금 전<span class='noselect'>|</span>"+splitResult[0]+"<span style='vertical-align: sub;font-size:xx-small;'>&nbsp;&nbsp;<a href='javascript:void(0)' onclick='commentDelete("+splitResult[2]+");$(this).parent().parent().remove();'>삭제</a></span></li>");
				
				$("#comment_content").val("");
			}
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("댓글 등록 기능 실패!");
		}
	});
}

//댓글삭제 ajax
function commentDelete(deleteCommentId) {
	
	var params = "deleteCommentId="+deleteCommentId;
	$.ajax({
		url:'<%=request.getContextPath()%>/auth/comment_delete.do',
		type:"get",
		contentType: 
			"application/x-www-form-urlencoded; charset=utf-8",
		data: params,
		dataType:"text",
		success:function(result){
			if( result == false ) {
				alert("로그인 정보를 확인해주세요");
			} else {
				$("#comment"+result).remove();
			}
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("댓글 삭제 기능 실패!");
		}
	});
}

</script>
    
<div class="memberCheckDiv">
<!-- 
	<h1>레시피아이디: ${selectedRecipe.recipe_id }</h1>
	<h1>멤버아이디: ${selectedRecipe.member_id }</h1>
	<h1>콘텐츠: ${selectedRecipe.content }</h1>
	<h1>카테고리: ${selectedRecipe.category }</h1>
	<h1>글쓴시간: ${selectedRecipe.write_time }</h1>
	<h1>조회수: ${selectedRecipe.read_count }</h1>
	<h1>좋아요: ${selectedRecipe.like_count }</h1>
	<h1>닉네임: ${selectedSimpleRecipe.nickname }</h1>
-->




  <div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">
  	<table>
  		<tr><td><label>  </label></td></tr>
  		<tr>
  			<td colspan="4"><h1 style="display:inline;">${selectedRecipe.title }</h1>
  			<c:if test="${ selectedRecipe.category.equals('advanced') }">
  				<span class="noselect">|</span>고급요리 (30분 이상)
  			</c:if>
  			<c:if test="${ selectedRecipe.category.equals('beginner') }">
  				<span class="noselect">|</span>초급요리 (15분 미만)
  			</c:if>
  			<c:if test="${ selectedRecipe.category.equals('intermediate') }">
  				<span class="noselect">|</span>중급요리 (30분 미만)
  			</c:if>
  			</td>
  		</tr>
  		<tr><td><label>  </label></td></tr>
  		<tr><td><label>  </label></td></tr>
  		<tr><td>작성자 : ${selectedSimpleRecipe.nickname }<span class="noselect">|</span></td>
  		<td>${selectedRecipe.write_time }<span class="noselect">|</span></td>
  		
  		<td>조회수 : ${selectedRecipe.read_count }<span class="noselect">|</span></td>
  		
  		
		<c:if test="${requestScope.isLike}">
			<td><a class="likeCountATag" href="javascript:void(0)" onclick="thumbsUp();" style="color:#8080f1;text-decoration:none;"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> <span class="likeCount">${selectedRecipe.like_count }</span></a></td></tr>
		</c:if>
		<c:if test="${!requestScope.isLike}">
			<td><a class="likeCountATag" href="javascript:void(0)" onclick="thumbsUp();" style="color:white;text-decoration:none;"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> <span class="likeCount">${selectedRecipe.like_count }</span></a></td></tr>
		</c:if>
	</table>
  </div>
  <div class="panel-body" style="min-height: 200px;" id="recipeDetailContent">
    <p>${selectedRecipe.content}</p>
  </div>


  <!-- List group -->
  <ul class="list-group">
   <div class="input-group">
  <span class="input-group-addon" id="basic-addon1"><button style="border:0px;background-color: rgba(1,1,1,0);" onclick="commentRegist()">등록</button></span>
  <input type="text" id="comment_content" class="form-control" placeholder="댓글을 적는데 욕은 쓰지마세요~" aria-describedby="basic-addon1">
</div>
<c:if test="${requestScope.commentList.size() == 0}">
	<li class="list-group-item" id="noComment"> 등록된 댓글이 없습니다. </li>	
</c:if>
<div id="newComment">
	
</div>
<c:forEach var="comment" items="${requestScope.commentList}" >

	<c:if test="${comment.member_id == sessionScope.login_member.member_id}" var="ifVal">
		<li class="list-group-item" id="comment${comment.comment_id}">
		<!-- 댓글작성시간  -->
		<c:set var="temp" value="<%=new java.util.Date()%>" />
		${past = comment.write_timeLong ; ''}
		${now = temp.getTime() ; '' }
		${dif = (now-past)/1000 ; '' }
		<c:if test="${dif < 60 }"> 방금 전</c:if>
		<fmt:parseNumber var="dif_min" integerOnly="true" value="${dif/60 }"/>
		<c:if test="${dif >=60 and dif < 60*60}"> ${dif_min}분 전</c:if>
		<fmt:parseNumber var="dif_hour" integerOnly="true" value="${dif/(60*60) }"/>
		<c:if test="${dif >= 60*60 and dif < 60*60*24 }"> ${dif_hour}시간 전</c:if>
		<fmt:parseNumber var="dif_day" integerOnly="true" value="${dif/(60*60*24) }"/>
		<c:if test="${dif >= 60*60*24 }"> ${dif_day}일 전</c:if>
		<!-- ----------------- -->
		<span class="noselect">|</span>${comment.content }<span style="vertical-align: sub;font-size:xx-small;">&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="commentDelete(${comment.comment_id});">삭제</a></span></li>
	</c:if>
	<c:if test="${!ifVal}">
		<li class="list-group-item">
		<!-- 댓글작성시간  -->
		<c:set var="temp" value="<%=new java.util.Date()%>" />
		${past = comment.write_timeLong ; ''}
		${now = temp.getTime() ; '' }
		${dif = (now-past)/1000 ; '' }
		<c:if test="${dif < 60 }"> 방금 전</c:if>
		<fmt:parseNumber var="dif_min" integerOnly="true" value="${dif/60 }"/>
		<c:if test="${dif >=60 and dif < 60*60}"> ${dif_min}분 전</c:if>
		<fmt:parseNumber var="dif_hour" integerOnly="true" value="${dif/(60*60) }"/>
		<c:if test="${dif >= 60*60 and dif < 60*60*24 }"> ${dif_hour}시간 전</c:if>
		<fmt:parseNumber var="dif_day" integerOnly="true" value="${dif/(60*60*24) }"/>
		<c:if test="${dif >= 60*60*24 }"> ${dif_day}일 전</c:if>
		<!-- ----------------- -->
		<span class="noselect">|</span>${comment.content }${comment.content }</li>
	</c:if>
</c:forEach>

  </ul>
</div>
	
</div>