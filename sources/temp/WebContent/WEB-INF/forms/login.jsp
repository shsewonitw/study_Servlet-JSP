<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css">

<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>

<title>로그인 페이지</title>

</head>

<body>

  <div class="container">
    <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-success">
            <div class="panel-heading">
                <div class="panel-title">로그인</div>
            </div>
            <div class="panel-body">
                <form id="login_form" action="<%=request.getContextPath() %>/login.do" method="post">
                    <div>
                    	<label>아이디</label>
                        <input type="text" class="form-control" name="member_id" value="${not empty cookie.sava_member_id ? cookie.sava_member_id.value : '' }" placeholder="ID를 입력하세요." autofocus>
                    ${errorMsg_ID }</div>
                    <div>
                    	<label>패스워드</label>
                        <input type="password" class="form-control" name="password" placeholder="Password를 입력하세요.">
                        ${ errorMsg_PASSWORD }
                    </div>
                    <div>
                    	<label>ID 저장
                    		<input type="checkbox" name="save_member_id" ${not empty cookie.save_member_id ? checked : '' } value="true">
                    	</label>
                    </div>
                    <div>
                        <input type="submit" class="form-control btn btn-primary" value="로그인"></input>
                    </div>
                    </form>
            </div>
        </div>
    </div>
</div>


</body>
</html>

