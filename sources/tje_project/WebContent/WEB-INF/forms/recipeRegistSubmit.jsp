<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form action="<%=request.getContextPath()%>/auth/recipe_detail.do" name="nowRecipe">
	<input type="hidden" name="recipe_id" value="${requestScope.last_insert_id}">
</form>

<div class="memberCheckDiv">
<div class="alert alert-info" role="alert">우리동네 쉐프  '${login_member.nickname}'님의 레시피[${param.title}]가 잘 등록 되었습니다. </div>
	<div align="center">
	<!-- 방금작성한 recipe_id = ${requestScope.last_insert_id} -->
		<button type="button" class="btn btn-primary" onclick="javascript:document.nowRecipe.submit();">작성글 확인</button>
		<button type="button" class="btn btn-danger" onclick="javascript:document.goToMain.submit();">홈으로</button>
	</div>
</div>

