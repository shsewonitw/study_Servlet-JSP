<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>regist check</title>
</head>
<body>

<%
final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
final String jdbc_url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
final String jdbc_id = "root";
final String jdbc_password = "SystemManager304";

String strId = request.getParameter("id").trim();
String strPassword = request.getParameter("password").trim();
String strName = request.getParameter("name").trim();
String strAge = request.getParameter("age").trim();
Integer nAge = 0;
String strTel = request.getParameter("tel").trim();
String strAddress = request.getParameter("address").trim();
int result = 0;
try{
	Class.forName(jdbc_driver);
	Connection conn = DriverManager.getConnection(jdbc_url, jdbc_id, jdbc_password);
	String sql = "insert into member values(?,?,?,?,?,?)";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, strId);
	pstmt.setString(2, strPassword);
	pstmt.setString(3, strName);
	
	if(nAge != 0)
		pstmt.setInt(4, nAge);
	else
		pstmt.setNull(4,java.sql.Types.NULL);

	if(strTel.length()!=0)
		pstmt.setString(5, strTel);
	else
		pstmt.setNull(5,java.sql.Types.NULL);
	
	if(strAddress.length()!=0)
		pstmt.setString(6, strAddress);
	else
		pstmt.setNull(6,java.sql.Types.NULL);

	result = pstmt.executeUpdate();
	
	pstmt.close();
	conn.close();
	
	if(result==0) {
		out.println("<h2> 회원가입 실패 </h2>");
		out.println("<h4> 입력된 정보를 확인하세요</h4>");
	}else {
		out.println("<h2> 회원가입 성공 </h2>");
		out.println("<form action='./main.jsp'><input type='submit' value='메인으로~!'></form>");
	}
	
} catch(Exception e){
	e.printStackTrace();
}

%>

</body>
</html>