<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tje.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

<form action="<%= request.getContextPath() %>/regist.do" method="post">
	<table>
		<caption>회원정보 수정</caption>
		<tr>
			<th>ID</th>
			<td>${login_member.member_id }</td>
		</tr>
		<tr>
			<th>PW</th>
			<td>***********</td>
		</tr>
		<tr>
			<th>NAME</th>
			<td>${login_member.name }</td>
		</tr>
		<tr>
			<th>GENDER</th>
			<td>
			${login_member.genderString }
			</td>
		</tr>
		<tr>
			<th>AGE</th>
			<td><input type="text" name="age" value="${ login_member.age != 0 ? login_member.age : ''}"></td>
		</tr>
		<tr>
			<th>BIRTH</th>
			<td>
			<%
				Member member = (Member)session.getAttribute("login_member");
				java.util.Date birth = member.getBirth();
				java.util.Calendar cal = java.util.Calendar.getInstance();
				int year=0 , month=0, day =0;
				if(birth != null){
				
				cal.setTime(birth);
				
				year = cal.get(cal.YEAR);
				month = cal.get(cal.MONTH);
				day = cal.get(cal.DAY_OF_WEEK);
				}
				
			%>
			년 : <input type="text" size="4" name="year" value="${ year!=0?year:'' }">
			월 : <input type="text" size="2" name="month" value="${ month!=0?year:'' }">
			일 : <input type="text" size="2" name="day" value="${ day!=0?year:'' }">
			</td>
		</tr>

		<tr>
			<th>TEL</th>
			<td><input type="text" name="tel" value="${ login_member.tel }"></td>
		</tr>
		<tr>
			<th>ADDRESS</th>	
			<td><input type="text" name="address" value="${ login_member.address }"></td>		
		</tr>		
		<tr>
			<th colspan="2"><input type="submit" value="가입"></th>
		</tr>
	</table>
</form>

</body>
</html>