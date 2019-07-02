<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="memberCheckDiv">
	<div class="alert alert-info" role="alert" id="memberCheckAlert">회원님이 작성한 레시피입니다.</div>
	
	<div>
		<ul class="list-group">
		<c:forEach var="simpleRecipe" items="${requestScope.myRecipeList}">
		${ var = simpleRecipe.recipe_id ; '' }
		${var = var.longValue() ; ''}
			  <li class="list-group-item">
			    <span class="badge">${ccMap[var]}</span>
			    <a id="recipeListATag" href="<%=request.getContextPath()%>/auth/recipe_detail.do?recipe_id=${simpleRecipe.recipe_id}">${simpleRecipe.title }</a>
			  </li>
		</c:forEach>
		</ul>
	</div>
</div>