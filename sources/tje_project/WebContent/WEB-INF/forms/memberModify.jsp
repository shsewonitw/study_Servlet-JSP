<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script type="text/javascript">
var modify_pwCheck = false;
var modify_nicknameCheck = false;
var modify_emailCheck = false;
var modify_wholeCheck = false;

function modifySubmit(){
	$("input[name=modify_password]").blur();
	$("input[name=modify_nickname]").blur();
	$("input[name=modify_email]").blur();
	wholeCheck = modify_pwCheck && modify_nicknameCheck && modify_emailCheck;
	if( !wholeCheck ){
		return;
	}
	$("form[name=memberModifyAccept]").submit();
}

$(function(){

	// 패스워드체크 ajax
	$("input[name=modify_password]").on("focusout",function() {
		if( $("input[name=modify_password]").val().length < 1 ){
			$("#modify_pwCheck").text("패스워드를 입력해주세요")
			modify_pwCheck = false;
			return;
		} else if( $("input[name=modify_password]").val().length > 16 || $("input[name=modify_password]").val().length < 8 ){
			$("#modify_pwCheck").text("패스워드는 8~16자리만 입력 가능합니다")
			modify_pwCheck = false;
			return;
		} else{
			$("#modify_pwCheck").text("")
			modify_pwCheck = true;
			return;
		}
	});
	
	// 닉네임 ajax
	$("input[name=modify_nickname]").on("focusout",function() {
		if( $("input[name=modify_nickname]").val().length < 1 ){
			$("#modify_nicknameCheck").text("닉네임을 입력해주세요")
			modify_nicknameCheck = false;
			return;
		} else if( $("input[name=modify_nickname]").val().length > 8 || $("input[name=modify_nickname]").val().length < 2 ){
			$("#modify_nicknameCheck").text("닉네임은 2~8자리만 입력 가능합니다")
			modify_nicknameCheck = false;
			return;
		}
		
		var nickname = $("input[name=modify_nickname]").val();
		var type = "modify_nickNameCheck";
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
					$("#modify_nicknameCheck").text("이미 존재하는 닉네임입니다.");
					modify_nicknameCheck = false;
				} else {
					$("#modify_nicknameCheck").text("");
					modify_nicknameCheck = true;
				}                                                                                                                           
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$("#modify_nicknameCheck").text("이미 존재하는 닉네임입니다.");
				modify_nicknameCheck = false;
			}
		});
	});
	
	// email ajax
	$("input[name=modify_email]").on("focusout",function() {
		if( $("input[name=modify_email]").val().length < 1 ){
			$("#modify_emailCheck").text("이메일을 입력해주세요")
			modify_emailCheck = false;
			return;
		} else if( $("input[name=modify_email]").val().length > 20 || $("input[name=modify_email]").val().length < 5 ){
			$("#modify_emailCheck").text("이메일을 확인해주세요")
			modify_emailCheck = false;
			return;
		}
		
		var email = $("input[name=modify_email]").val();
		var type = "modify_emailCheck";
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
					$("#modify_emailCheck").text("이미 존재하는 이메일입니다.");
					modify_emailCheck = false;
				} else {
					$("#modify_emailCheck").text("");
					modify_emailCheck = true;
				}                                                                                                                           
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$("#emailCheck").text("이미 존재하는 이메일입니다.");
				modify_emailCheck = false;
			}
		});
	});

});

</script>
	
<br/><br/>

<div class="memberModifyDiv">
<div class="alert alert-info" role="alert" id="memberCheckAlert">회원 정보 변경을 위해 정보를 입력해주세요</div>

<form action="<%=request.getContextPath()%>/auth/member_modify_accept.do" method="post" name="memberModifyAccept">
<div class="input-group">
	<span class="input-group-addon" id="basic-addon1">ID</span> <input
		type="text" class="form-control" value="${login_member.member_id }"
		aria-describedby="basic-addon1" name="modify_member_id" readonly/>
</div>
<label class="signUpCheckLabel" id="idCheck"> </label>

<div class="input-group">
	<span class="input-group-addon" id="basic-addon1">PW</span> <input
		type="password" class="form-control" placeholder="8~16자리 입력"
		aria-describedby="basic-addon1" name="modify_password">
</div>
<label class="signUpCheckLabel" id="modify_pwCheck"> </label>

<div class="input-group">
	<span class="input-group-addon" id="basic-addon1">NICKNAME</span> <input
		type="text" class="form-control" value="${login_member.nickname }" placeholder="2~8자리 입력"
		aria-describedby="basic-addon1" name="modify_nickname">
</div>
<label class="signUpCheckLabel" id="modify_nicknameCheck"> </label>

<div class="input-group">
	<span class="input-group-addon" id="basic-addon1">EMAIL</span> <input
		type="text" class="form-control" value="${login_member.email }" placeholder="이메일 주소"
		aria-describedby="basic-addon1" name="modify_email">
</div>
<label class="signUpCheckLabel" id="modify_emailCheck"> </label>
</form>

<div align="center">
  <button type="button" class="btn btn-primary" onclick="modifySubmit();">
    변경
  </button>
  <button type="button" class="btn btn-danger" onclick="javascript:document.goToMain.submit();">
    취소
  </button>
</div>

<label> </label>
</div>