<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!--  캐러샐  -->
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img src="<%=request.getContextPath()%>/img/title.jpg">
      <div class="carousel-caption">
        .
      </div>
    </div>
    <div class="item">
      <img src="<%=request.getContextPath()%>/img/cook.jpg">
      <div class="carousel-caption">
        .
      </div>
    </div>
    .
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
<!--  /캐러샐  -->
<div>

<table>
	<tr>
		<td><div class="mainMenu"><img src="<%=request.getContextPath()%>/img/menu.jpg" width="100%" height="100%"></div></td>
		<td><div class="mainMenu"><img src="<%=request.getContextPath()%>/img/menu.jpg" width="100%" height="100%"></div></td>
		<td><div class="mainMenu"><img src="<%=request.getContextPath()%>/img/menu.jpg" width="100%" height="100%"></div></td>
		<td><div class="mainMenu"><img src="<%=request.getContextPath()%>/img/menu.jpg" width="100%" height="100%"></div></td>
	</tr>
</table>
</div>