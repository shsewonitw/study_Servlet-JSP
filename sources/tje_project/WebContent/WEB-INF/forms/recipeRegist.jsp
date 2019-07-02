<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="memberCheckDiv">
<div class="alert alert-info" role="alert">우리동네 쉐프 '${login_member.nickname}'님의 레시피를 등록해주세요 </div>
	<form action="<%=request.getContextPath()%>/auth/recipe_regist.do" method="POST" name="recipeRegistForm">
	<div class="row">
	  <div class="col-lg-6">
	    <div class="input-group">
	      <div class="input-group-btn">
	         <select name="category" class="categoryMenu btn btn-default dropdown-toggle">
		          <option value="beginner">초급요리 (15분 미만)</option>
		          <option value="intermediate">중급요리 (30분 미만)</option>
		          <option value="advanced">고급요리 (30분 이상)</option>
	          </select>
	      </div><!-- /btn-group -->
	      <input type="text" class="form-control" name="title" placeholder="제목 - 40자 미만">
	    </div><!-- /input-group -->
	  </div><!-- /.col-lg-6 -->
	</div><!-- /.row -->
	<br/>
	<div>
		<textarea name="content" cols="40" rows="20" class="form-control" placeholder="내용 - 1000자 미만"></textarea>
	</div>
	</form>
	<br/>
	<div align="center">
		<button type="button" class="btn btn-primary" onclick="javascript:document.recipeRegistForm.submit();">작성</button>
		<button type="button" class="btn btn-danger" onclick="javascript:document.goToMain.submit();">취소</button>
	</div>
</div>