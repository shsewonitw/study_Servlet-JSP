<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 공유 사이트</title>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.css?ver=1211">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css?ver=02">

<script type="text/javascript">
$(function(){
	if( '${logined = sessionScope.login_member.member_id}' ){
		$('#signUpButton').attr('style','display: none');
		$('#myPageButton').attr('style','');
		$('#signInButton').attr('style','display: none');
		$('#signOutButton').attr('style','');
	} else{
		$('#signUpButton').attr('style','');
		$('#myPageButton').attr('style','display: none');
		$('#signInButton').attr('style','');
		$('#signOutButton').attr('style','display: none');
	}
})
	


var idCheck = false;
var pwCheck = false;
var nicknameCheck = false;
var emailCheck = false;
var wholeCheck = false;
// 회원가입 ajax
function signUp() {
	$("input[name=member_id]").blur();
	$("input[name=password]").blur();
	$("input[name=nickname]").blur();
	$("input[name=email]").blur();
	wholeCheck = idCheck && pwCheck && nicknameCheck && emailCheck;
	if( !wholeCheck ){
		return;
	}
	
	
	var signup_data = $("#signupform").serialize();
	
	$.ajax({
		url:'<%=request.getContextPath()%>/member_join.do',
		type:"post",		
		contentType: 
			"application/x-www-form-urlencoded; charset=utf-8",
		data: signup_data,
		dataType:"text",
		success:function(result){			
			// JSON 데이터 포멧으로 구성된 result 변수에서
			// 값을 추출
			//alert(result)		
			
			if( eval(result) ) {
				alert("회원가입 성공!");
				$("input[name=member_id]").val("");
				$("input[name=password]").val("");
				$("input[name=nickname]").val("");
				$("input[name=email]").val("");
				$('#signUpModal').modal('hide');
				idCheck = false;
				pwCheck = false;
				nicknameCheck = false;
				emailCheck = false;
				wholeCheck = false;
				imgChange();
			} else {
				alert("회원가입 실패!");
			}
			
		},
		// ajax 호출이 실패한 경우 실행되는 함수
		error: function(jqXHR, textStatus, errorThrown) {
			alert("회원가입 실패!");
		}
	});
}


//로그인 ajax
function signIn() {
	// 특정 폼 내부의 모든 데이터를 직렬화하여 변수로 저장		
	var signin_data = $("#signinform").serialize();
	
	$.ajax({
		url:'<%=request.getContextPath()%>/member_login.do',
		type:"post",		
		contentType: 
			"application/x-www-form-urlencoded; charset=utf-8",
		data: signin_data,
		dataType:"text",
		success:function(result){			
			// JSON 데이터 포멧으로 구성된 result 변수에서
			// 값을 추출
			//alert(result)		
			
			if( eval(result) ) {
				$("input[name=member_id]").val("");
				$("input[name=password]").val("");
				$('#signInModal').modal('hide');
				$('#signInCheckLabel').text(" ");
				$('#signUpButton').attr('style','display: none');
				$('#myPageButton').attr('style','');
				$('#signInButton').attr('style','display: none');
				$('#signOutButton').attr('style','');
				$('.signInImg').attr('src','<%=request.getContextPath()%>/img/chef.png')
			}
	 		else {
				$('#signInCheckLabel').text("아이디와 비밀번호를 확인해주세요");
				$('.signInImg').attr('src','<%=request.getContextPath()%>/img/chef_red.png')
			}
			
		},
		// ajax 호출이 실패한 경우 실행되는 함수
		error: function(jqXHR, textStatus, errorThrown) {
			$('#signInCheckLabel').text("아이디와 비밀번호를 확인해주세요");
			$('.signInImg').attr('src','<%=request.getContextPath()%>/img/chef_red.png')
		}
	});
}


// close method
function closeButton(){
	$("input[name=member_id]").val("");
	$("input[name=password]").val("");
	$("input[name=nickname]").val("");
	$("input[name=email]").val("");
	$("#idCheck").text("");
	$("#pwCheck").text("");
	$("#nicknameCheck").text("");
	$("#emailCheck").text("");
	idCheck = false;
	pwCheck = false;
	nicknameCheck = false;
	emailCheck = false;
	wholeCheck = false;
	imgChange();
}

function LoginCloseButton(){
	$("input[name=member_id]").val("");
	$("input[name=password]").val("");
	$("#signInCheckLabel").text("");
}

// sign up img change
function imgChange(){
	if(idCheck && pwCheck && nicknameCheck && emailCheck){
		$(".signUpImg").attr("src","<%=request.getContextPath()%>/img/chef_green2.png");
	} else {
		$(".signUpImg").attr("src","<%=request.getContextPath()%>/img/chef.png");
	}
}

$(function(){
	// 아이디체크 ajax
	$("input[name=member_id]").on("focusout",function() {
		if( $("input[name=member_id]").val().length < 1 ){
			$("#idCheck").text("아이디를 입력해주세요");
			idCheck = false;
			imgChange();
			return;
		} else if( $("input[name=member_id]").val().length > 10 || $("input[name=member_id]").val().length <4 ){
			$("#idCheck").text("아이디는 4~10자리만 입력 가능합니다");
			idCheck = false;
			imgChange();
			return;
		}
		
		var member_id = $("input[name=member_id]").val();
		var type = "idCheck";
		var params = "member_id="+member_id+"&type="+type;
		$.ajax({
			url:'<%=request.getContextPath()%>/member_join.do',
			type:"get",		
			contentType: 
				"application/x-www-form-urlencoded; charset=utf-8",
			data: params,
			dataType:"text",
			success:function(result){
				if( eval(result) ) {
					$("#idCheck").text("이미 존재하는 아이디입니다.");
					idCheck = false;
					imgChange();
				} else {
					$("#idCheck").text("");
					idCheck = true;
					imgChange();
				}                                                                                                                           
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$("#idCheck").text("이미 존재하는 아이디입니다.");
				idCheck = false;
				imgChange();
			}
		});
	});
	
	// 패스워드체크 ajax
	$("input[name=password]").on("focusout",function() {
		if( $("input[name=password]").val().length < 1 ){
			$("#pwCheck").text("패스워드를 입력해주세요")
			pwCheck = false;
			imgChange();
			return;
		} else if( $("input[name=password]").val().length > 16 || $("input[name=password]").val().length < 8 ){
			$("#pwCheck").text("패스워드는 8~16자리만 입력 가능합니다")
			pwCheck = false;
			imgChange();
			return;
		} else{
			$("#pwCheck").text("")
			pwCheck = true;
			imgChange();
			return;
		}
	});
	
	// 닉네임 ajax
	$("input[name=nickname]").on("focusout",function() {
		if( $("input[name=nickname]").val().length < 1 ){
			$("#nicknameCheck").text("닉네임을 입력해주세요")
			nicknameCheck = false;
			imgChange();
			return;
		} else if( $("input[name=nickname]").val().length > 8 || $("input[name=nickname]").val().length < 2 ){
			$("#nicknameCheck").text("닉네임은 2~8자리만 입력 가능합니다")
			nicknameCheck = false;
			imgChange();
			return;
		}
		
		var nickname = $("input[name=nickname]").val();
		var type = "nickNameCheck";
		var params = "nickName="+nickname+"&type="+type;
		$.ajax({
			url:'<%=request.getContextPath()%>/member_join.do',
			type:"get",		
			contentType: 
				"application/x-www-form-urlencoded; charset=utf-8",
			data: params,
			dataType:"text",
			success:function(result){
				if( eval(result) ) {
					$("#nicknameCheck").text("이미 존재하는 닉네임입니다.");
					nicknameCheck = false;
					imgChange();
				} else {
					$("#nicknameCheck").text("");
					nicknameCheck = true;
					imgChange();
				}                                                                                                                           
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$("#nicknameCheck").text("이미 존재하는 닉네임입니다.");
				nicknameCheck = false;
				imgChange();
			}
		});
	});
	
	// email ajax
	$("input[name=email]").on("focusout",function() {
		if( $("input[name=email]").val().length < 1 ){
			$("#emailCheck").text("이메일을 입력해주세요")
			emailCheck = false;
			imgChange();
			return;
		} else if( $("input[name=email]").val().length > 20 || $("input[name=email]").val().length < 5 ){
			$("#emailCheck").text("이메일을 확인해주세요")
			emailCheck = false;
			imgChange();
			return;
		}
		
		var email = $("input[name=email]").val();
		var type = "emailCheck";
		var params = "email="+email+"&type="+type;
		$.ajax({
			url:'<%=request.getContextPath()%>/member_join.do',
			type:"get",		
			contentType: 
				"application/x-www-form-urlencoded; charset=utf-8",
			data: params,
			dataType:"text",
			success:function(result){
				if( eval(result) ) {
					$("#emailCheck").text("이미 존재하는 이메일입니다.");
					emailCheck = false;
					imgChange();
				} else {
					$("#emailCheck").text("");
					emailCheck = true;
					imgChange();
				}                                                                                                                           
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$("#emailCheck").text("이미 존재하는 이메일입니다.");
				emailCheck = false;
				imgChange();
			}
		});
	});

});


</script>



</head>
<body>
	<form action="<%=request.getContextPath()%>/main.do" name="goToMain"></form>

	<div class="bodyDiv">

		<div>
			<a href="<%=request.getContextPath()%>/main.do"><label><img style="cursor:pointer" src="<%=request.getContextPath()%>/img/logowhite.png" width="8%"
				height="auto">  <img style="cursor:pointer" src="<%=request.getContextPath()%>/img/title1.png" width="40%" height="auto"></label></a>
		</div>

		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="<%=request.getContextPath()%>/auth/recipe_regist.do"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;&nbsp;레시피 등록<span
								class="sr-only">(current)</span></a></li>
						<li><a href="<%=request.getContextPath()%>/auth/recipe_list.do"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;레시피들</a></li>
						<li><a href="<%=request.getContextPath()%>/awards.do"><span class="glyphicon glyphicon-tower" aria-hidden="true"></span>&nbsp;&nbsp;명예의 전당</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						
							
							<!--  회원가입 버튼   -->
							<li>
								<button type="button" class="modal_button" data-toggle="modal"
								data-target="#signUpModal" id="signUpButton">회원가입</button>
							</li>
							<!--  마이페이지 버튼  -->
							<li class="dropdown" id="myPageButton">
          						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">계정 <span class="caret"></span></a>
					          	<ul class="dropdown-menu" role="menu">
						            <li><a href="<%=request.getContextPath()%>/auth/member_check.do">개인정보 수정</a></li>
						            <li><a href="<%=request.getContextPath()%>/auth/like_recipe_list.do">좋아요한 게시물</a></li>
						            <li><a href="<%=request.getContextPath()%>/auth/my_recipe_list.do">내 레시피</a></li>
					         	 </ul>
					        </li>
							
							
						
							<li>
							<!--  로그인 버튼   -->
								<button type="button" class="modal_button" data-toggle="modal"
									data-target="#signInModal" id="signInButton">로그인</button>
							<!--  로그아웃 버튼   -->
								<button type="button" class="modal_button" id="signOutButton" onclick="javascript:document.logout.submit();">로그아웃</button>
								<form action="<%=request.getContextPath()%>/auth/member_logout.do" name="logout" method="POST">
									<script type="text/javascript">
									$(function(){
										var url = window.location.href;
										$(".url").attr("value",url);
									})
										
									</script>
									<input type="hidden" class="url" name="url" value="">
									
								</form>
							</li>	
						
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>



		<!-- Button trigger modal -->


		<!-- 회원가입 Modal -->
		
		<div class="modal fade" id=signUpModal tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
			<form action="<%=request.getContextPath()%>/member_join.do"  id="signupform" method="post">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">회원가입</h4>
					</div>
					<div class="modal-body">
						<div align="center">
							<img class="signUpImg" src="<%=request.getContextPath()%>/img/chef.png" width="20%" height="auto"> 
						</div>
						<nav align="right" class="member_join_nav">
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">ID</span> <input
									type="text" class="form-control" placeholder="4~10자리 입력"
									aria-describedby="basic-addon1" name="member_id" >
							</div>
							<label class="signUpCheckLabel" id="idCheck"> </label>

							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">PW</span> <input
									type="password" class="form-control" placeholder="8~16자리 입력"
									aria-describedby="basic-addon1" name="password" >
							</div>
							<label class="signUpCheckLabel" id="pwCheck"> </label>
							
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">NICKNAME</span>
								<input type="text" class="form-control" placeholder="2~8자리 입력"
									aria-describedby="basic-addon1" name="nickname" >
							</div>
							<label class="signUpCheckLabel" id="nicknameCheck"> </label>
							
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">EMAIL</span> <input
									type="text" class="form-control" placeholder="이메일 주소"
									aria-describedby="basic-addon1" name="email" >
							</div>
							<label class="signUpCheckLabel" id="emailCheck"> </label>
						</nav>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeButton();">취소</button>
						<input type="button" class="btn btn-primary" value="가입" onclick="signUp();">
						
					</div>
					
				</div>
				</form>
			</div> 
		</div>

		<!-- 로그인 Modal -->
		<div class="modal fade" id=signInModal tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">로그인</h4>
					</div>
					<form action="<%=request.getContextPath()%>/member_login.do" id="signinform" method="post">
					<div class="modal-body">
						<div align="center">
							<img src="<%=request.getContextPath()%>/img/chef.png" width="20%" height="auto" class="signInImg"> 
						</div>
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1">ID</span> <input
								type="text" class="form-control" placeholder=""
								aria-describedby="basic-addon1" name="member_id">
						</div>
						<label>  </label>

						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1">PW</span> <input
								type="password" class="form-control" placeholder=""
								aria-describedby="basic-addon1" name="password">
						</div><br/>
						<div align="center">
						<label id="signInCheckLabel">  </label>
						</div>

					</div>
					</form>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" onclick="LoginCloseButton();">취소</button>
						<input type="button" class="btn btn-primary" value="로그인" onclick="signIn();">
					</div>
				</div>
			</div>
		</div>