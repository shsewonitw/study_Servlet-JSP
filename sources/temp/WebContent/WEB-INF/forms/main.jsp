<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.css">

<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>

<script type="text/javascript" 	src="<%=request.getContextPath()%>/js/jquery.js"></script>

<style type="text/css">
	.row {
		display: block;
		margin-left: auto;
		margin-right: auto;
	}
	
	.element{
		row();
	}
</style>

<title>메인 페이지</title>

</head>

<body>
	<a href="<%=request.getContextPath()%>/main.do"><img
		class="image-responsive center-block"
		src="<%=request.getContextPath()%>/img/food1.jpg" alt="img-responsive"></a>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">전체</a>
			</div>
			<div>
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="flase">카테고리
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">면</a></li>
							<li><a href="#">밥</a></li>
							<li><a href="#">국</a></li>
							<li><a href="#">반찬</a></li>
						</ul></li>
					<li class="dropdown"><a href="#">레시피</a></li>
					<li><a href="<%=request.getContextPath() %>/article.do">자유게시판</a></li>
				</ul>
				<c:if test="${empty sessionScope.login_member }" var="result">
				<form class="navbar-form navbar-right" role="search">
					<div class="formgroup">
						<a href="<%=request.getContextPath()%>/login.do"
							class="btn btn-success">로그인</a> <a
							href="<%=request.getContextPath()%>/regist.do"
							class="btn btn-info">회원가입</a>
					</div>					
				</form>		
				</c:if>		
				<c:if test="${not result }">
				<form class="navbar-form navbar-right" role="search">
					<div class="formgroup">
						<a href="<%=request.getContextPath()%>/logout.do"
							class="btn btn-danger">로그아웃</a>							
					</div>					
				</form>		
				</c:if>
			</div>
		</div>
	</nav>	
	<div class="row">
		<div class="col-sm-5 col-md-3">
			<div class="thumbnail">
				<img src="<%=request.getContextPath()%>/img/mara.jpg"
					alt="img-responsive">
				<div class="caption">
					<h3>마라샹궈</h3>
					<p>산초의 매운맛과 얼얼함이 중독되는 요리</p>
					<p>
						<a href="#" class="btn btn-primary" role="button">레시피</a>
					</p>
				</div>
			</div>
		</div>

		<div class="col-sm-5 col-md-3">
			<div class="thumbnail">
				<img src="<%=request.getContextPath()%>/img/beicon.jpg"
					alt="img-responsive">
				<div class="caption">
					<h3>베이컨야채말이</h3>
					<p>신선한 야채와 베이컨이 만난 특별한 맛의 봄나들이 메뉴입니다.</p>
					<p>
						<a href="#" class="btn btn-primary" role="button">레시피</a> 
					</p>
				</div>
			</div>
		</div>

		<div class="col-sm-5 col-md-3">
			<div class="thumbnail">
				<img src="<%=request.getContextPath()%>/img/minari.jpg"
					alt="img-responsive">
				<div class="caption">
					<h3>우삼겹 미나리구이</h3>
					<p>향이 진한 미나리와 쫄깃하고 담백한 우삼겹구이의 만남</p>
					<p>
						<a href="#" class="btn btn-primary" role="button">레시피</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-5 col-md-3">
			<div class="thumbnail">
				<img src="<%=request.getContextPath()%>/img/shirimp.jpg"
					alt="img-responsive">
				<div class="caption">
					<h3>코코넛 쉬림프</h3>
					<p>코코넛 튀김살과 새우의 환상적인 궁합이 돋보이는 특별한 새우 튀김요리입니다.</p>
					<p>
						<a href="#" class="btn btn-primary" role="button">레시피</a>
					</p>
				</div>
			</div>
		</div>
		<div class="col-sm-5 col-md-3">
			<div class="thumbnail">
				<img src="<%=request.getContextPath()%>/img/nabe.jpg"
					alt="img-responsive">
				<div class="caption">
					<h3>우삼겹나베</h3>
					<p>숙주와 생강채, 담백한 우삼겹을 담아 부드럽게 쪄낸 일본식 전골요리</p>
					<p>
						<a href="#" class="btn btn-primary" role="button">레시피</a>
					</p>
				</div>
			</div>
		</div>
			<div class="col-sm-5 col-md-3">
			<div class="thumbnail">
				<img src="<%=request.getContextPath()%>/img/ddd.jpg"
					alt="img-responsive">
				<div class="caption">
					<h3>차돌박이 된장찌개</h3>
					<p>부드러운 차돌박이와 구수한 된장이 만들어낸 찌개</p>
					<p>
						<a href="#" class="btn btn-primary" role="button">레시피</a>
					</p>
				</div>
			</div>
		</div>
</div> 

	<div class="container">
		<h3></h3>
	</div>
</body>
</html>

