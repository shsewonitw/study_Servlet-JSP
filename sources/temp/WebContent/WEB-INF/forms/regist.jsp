<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/js/jquery.js'></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.css">
<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>

<script type="text/javascript">
	var isCheckID = false;
	$(function() {
		$("#idCheckBtn").on("click", function () {
			// var member_id = $("#member_form [name='member_id']").val();
			var member_id = $('input[name=member_id]').val();
			// alert(member_id);
			$.ajax({
				url:'<%=request.getContextPath()%>/check_id.do',
				type : "post",
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				data : "member_id=" + member_id,
				dataType : "text",
				success : function(result) {
				var msg = "";
				if (eval(result)) {
					msg = '이미 존재하는 ID입니다.'
				} else {
					msg = '사용할 수 있는 ID입니다.'
					isCheckID = true;
				}
				$("#checkIDArea").text(msg);
				},
				error : function(result) {
					alert('ID체크 과정에서 문제발생');
				}
			});
		});
	});
</script>

</head>
<body>

	<div class="container">
		<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-success">
				<div class="panel-heading">
					<div class="panel-title">회원가입</div>
				</div>
				<div class="panel-body">
					<form id="regist_form"
						action="<%=request.getContextPath()%>/regist.do" method="post">
						<div>
							<label>아이디</label>
							<input type="text" name="member_id" class="form-control" placeholder="ID입력" required>			
							<button id="idCheckBtn" class="btn btn-primary btn-smcolumn 'id' cannot be null">ID중복체크</button>
							<span id="checkIDArea"></span>${errorMsg }
						</div>
						<div>
							<label>패스워드</label>
							<input type="password" class="form-control" name="password"
								placeholder="Password 입력"> ${ errorMsg_PASSWORD }
						</div>
						<div>
							<label>이름</label>
							<input type="text" name="name" value="${param.name }" class="form-control" required>														
						</div>
						<div>
							<label>전화번호</label>
							<input type="text" name="tel" value="${param.tel }" class="form-control">														
						</div>
						<div>
							<label>주소</label>
							<input type="text" name="address" value="${param.address }" class="form-control">														
						</div>
						<div>
							<input type="submit" class="form-control btn btn-primary" value="가입 신청">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


</body>
</html>