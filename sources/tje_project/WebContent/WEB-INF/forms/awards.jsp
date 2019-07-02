<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <label>  </label>
    
<div class="memberCheckDiv">
    
<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="false">
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          	초급요리 우수작
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
        <!--  -->
				<c:forEach var="bestRecipe_beginner" items="${requestScope.bestRecipeList_beginner}">
					<div class="jumbotron">
						<h2>${bestRecipe_beginner.title }</h2>
						<p><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${bestRecipe_beginner.nickname }</p>
						<p>
							<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> ${bestRecipe_beginner.like_count }
						</p>
						<p>
							<a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/auth/recipe_detail.do?recipe_id=${bestRecipe_beginner.recipe_id}" role="button" style="background-color: #272727; border-color: #ffffff;">레시피 보기</a>
						</p>
						
					</div>
				</c:forEach>
		<!-- -->
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingTwo">
      <h4 class="panel-title">
        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
          	중급요리 우수작
        </a>
      </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
      <div class="panel-body">
        	 <!--  -->
				<c:forEach var="bestRecipe_intermediate" items="${requestScope.bestRecipeList_intermediate}">
					<div class="jumbotron">
						<h2>${bestRecipe_intermediate.title }</h2>
						<p><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${bestRecipe_intermediate.nickname }</p>
						<p>
							<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> ${bestRecipe_intermediate.like_count }
						</p>
						<p>
							<a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/auth/recipe_detail.do?recipe_id=${bestRecipe_intermediate.recipe_id}" role="button" style="background-color: #272727; border-color: #ffffff;">레시피 보기</a>
						</p>
					</div>
				</c:forEach>
		<!-- -->
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingThree">
      <h4 class="panel-title">
        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
          	고급요리 우수작
        </a>
      </h4>
    </div>
    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
      <div class="panel-body">
        	 <!--  -->
				<c:forEach var="bestRecipe_advanced" items="${requestScope.bestRecipeList_advanced}">
					<div class="jumbotron">
						<h2>${bestRecipe_advanced.title }</h2>
						<p><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${bestRecipe_advanced.nickname }</p>
						<p>
							<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> ${bestRecipe_advanced.like_count }
						</p>
						<p>
							<a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/auth/recipe_detail.do?recipe_id=${bestRecipe_advanced.recipe_id}" role="button" style="background-color: #272727; border-color: #ffffff;">레시피 보기</a>
						</p>
					</div>
				</c:forEach>
		<!-- -->
      </div>
    </div>
  </div>
</div>
</div>