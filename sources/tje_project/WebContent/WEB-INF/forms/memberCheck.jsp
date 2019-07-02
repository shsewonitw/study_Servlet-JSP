<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">

//로그인 ajax
function memberCheck() {
	// 특정 폼 내부의 모든 데이터를 직렬화하여 변수로 저장		
	var checkPassword = $('input[name=checkPassword]').val();
	var params = "password="+checkPassword;
	$.ajax({
		url:'<%=request.getContextPath()%>/auth/member_check.do',
		type:"post",		
		contentType: 
			"application/x-www-form-urlencoded; charset=utf-8",
		data: params,
		dataType:"text",
		success:function(result){			
			if( eval(result) ) {
				$('#member_modify_submit').submit();
				
			}
	 		else {
	 			$('#memberCheckAlert').attr('class','alert alert-danger');
	 			$('#memberCheckAlert').text('패스워드를 확인해주세요');
	 			$('.memberCheckImg').attr('src','<%=request.getContextPath()%>/img/chef_red.png');
			}
			
		},
		// ajax 호출이 실패한 경우 실행되는 함수
		error: function(jqXHR, textStatus, errorThrown) {
			$('#memberCheckAlert').attr('class','alert alert-danger');
			$('#memberCheckAlert').text('패스워드를 확인해주세요');
			$('.memberCheckImg').attr('src','<%=request.getContextPath()%>/img/chef_red.png');
		}
	});
}

</script>


<div class="memberCheckDiv">
	<div align="center">
		<img src="<%=request.getContextPath()%>/img/chef.png" width="15%" height="auto" class="memberCheckImg">
	</div>
	<div class="alert alert-info" role="alert" id="memberCheckAlert">회원확인을 위해 비밀번호를 입력해주세요</div>

	<div class="input-group">
		<span class="input-group-addon" id="basic-addon1">PW</span>
		<form action="<%=request.getContextPath()%>/auth/member_modify.do" method="POST" id="member_modify_submit">
		<input type="password" class="form-control" placeholder=""
			aria-describedby="basic-addon1" name="checkPassword">
		</form>
	</div>
	<label> </label>

	<div align="center">
		<button type="button" class="btn btn-primary" onclick="memberCheck()">확인</button>
		<button type="button" class="btn btn-danger" onclick="javascript:document.goToMain.submit();">취소</button>
	</div>
	<!-- alert 종류 : success info warning danger -->


</div>