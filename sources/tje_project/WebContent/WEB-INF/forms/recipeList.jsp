<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form name="recipeListAll" action="<%=request.getContextPath()%>/auth/recipe_list.do" method="get"></form>
<form name="recipeListLevel1" action="<%=request.getContextPath()%>/auth/recipe_list.do" method="post">
<input type="hidden" name="level" value="level1">
</form>
<form name="recipeListLevel2" action="<%=request.getContextPath()%>/auth/recipe_list.do" method="post">
<input type="hidden" name="level" value="level2">
</form>
<form name="recipeListLevel3" action="<%=request.getContextPath()%>/auth/recipe_list.do" method="post">
<input type="hidden" name="level" value="level3">
</form>

<div class="memberCheckDiv">
	<div class="btn-group btn-group-justified" role="group"
		aria-label="...">
		<div class="btn-group" role="group">
			<button style="background-color: #353535;border: 1px solid #000000;color: white;" type="button" class="btn btn-default" onclick="javascript:document.recipeListAll.submit();">전체요리</button>
		</div>
		<div class="btn-group" role="group">
			<button style="background-color: #353535;border: 1px solid #000000;color: white;" type="button" class="btn btn-default" onclick="javascript:document.recipeListLevel1.submit();">초급요리</button>
		</div>
		<div class="btn-group" role="group">
			<button style="background-color: #353535;border: 1px solid #000000;color: white;" type="button" class="btn btn-default" onclick="javascript:document.recipeListLevel2.submit();">중급요리</button>
		</div>
		<div class="btn-group" role="group">
			<button style="background-color: #353535;border: 1px solid #000000;color: white;" type="button" class="btn btn-default" onclick="javascript:document.recipeListLevel3.submit();">고급요리</button>
		</div>
	</div>
	<label>  </label>
	<div>
		<ul class="list-group" >
		<c:forEach var="simpleRecipe" items="${requestScope.SimpleRecipeList}" >
		${ var = simpleRecipe.recipe_id ; '' }
		${var = var.longValue() ; ''}
			  <li class="list-group-item" >
			    <span class="badge">${ccMap[var]}</span>
			    <a id="recipeListATag" href="<%=request.getContextPath()%>/auth/recipe_detail.do?recipe_id=${simpleRecipe.recipe_id}">${simpleRecipe.title }</a>
			  </li>
		</c:forEach>
		</ul>
	</div>
	

	<!--  페이지네이션   -->

<c:if test='${requestScope.whatMethod eq "GET"}'>
	<nav>
	  <ul class="pagination">
	    <li>
	    </li>
	    <c:forEach var="pageNum" begin="1" end="${requestScope.pageSize}">
	   		 <li><a href="<%=request.getContextPath()%>/auth/recipe_list.do?PAGENUMBER=${pageNum-1}" style="background-color: #353535;border-color: black; color: white;">${pageNum}</a></li>
	    </c:forEach>
	    <li>
	    </li>
	  </ul>
	</nav>
</c:if>

<c:if test='${requestScope.whatMethod eq "POST1"}'>
	<nav>
	  <ul class="pagination">
	    <li>
	    </li>
	    <c:forEach var="pageNum" begin="1" end="${requestScope.pageSize}">
	   		 <form style="display: none;" name="pagingForm${pageNum-1}" action="<%=request.getContextPath()%>/auth/recipe_list.do" method="post">
		    	<input type="hidden" name="PAGENUMBER" value="${pageNum-1}">
		    	<input type="hidden" name="level" value="level1">
		     </form>
	   		 <li><a href="javascript:void(0);" onclick="javascript:document.pagingForm${pageNum-1}.submit();" style="background-color: #353535;border-color: black; color: white;">${pageNum}</a></li>
	    </c:forEach>
	    
	    <li>
	    </li>
	  </ul>
	</nav>
</c:if>

<c:if test='${requestScope.whatMethod eq "POST2"}'>
	<nav>
	  <ul class="pagination">
	    <li>
	    </li>
	    <c:forEach var="pageNum" begin="1" end="${requestScope.pageSize}">
	   		 <li><a href="javascript:void(0);" onclick="javascript:document.pagingForm${pageNum-1}.submit();" style="background-color: #353535;border-color: black; color: white;">${pageNum}</a></li>
	   		 <form style="display: none;" name="pagingForm${pageNum-1}" action="<%=request.getContextPath()%>/auth/recipe_list.do" method="post">
	    	<input type="hidden" name="PAGENUMBER" value="${pageNum-1}">
	    	<input type="hidden" name="level" value="level2">
	    </form>
	    </c:forEach>
	    
	    <li>
	    </li>
	  </ul>
	</nav>
</c:if>

<c:if test='${requestScope.whatMethod eq "POST3"}'>
	<nav>
	  <ul class="pagination">
	    <li>
	    </li>
	    <c:forEach var="pageNum" begin="1" end="${requestScope.pageSize}">
	   		 <li><a href="javascript:void(0);" onclick="javascript:document.pagingForm${pageNum-1}.submit();" style="background-color: #353535;border-color: black; color: white;">${pageNum}</a></li>
	   		 <form style="display: none;" name="pagingForm${pageNum-1}" action="<%=request.getContextPath()%>/auth/recipe_list.do" method="post">
	    	<input type="hidden" name="PAGENUMBER" value="${pageNum-1}">
	    	<input type="hidden" name="level" value="level3">
	    </form>
	    </c:forEach>
	    
	    <li>
	    </li>
	  </ul>
	</nav>
</c:if>
 <!--  -->
</div>
