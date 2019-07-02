<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login Check</title>
</head>
<body>
<%
final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
final String jdbc_url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
final String jdbc_id = "root";
final String jdbc_password = "SystemManager304";


String id = request.getParameter("id").trim();
String password = request.getParameter("password").trim();
String checked = request.getParameter("chk");
String name = null;

try {
	Class.forName(jdbc_driver);
} catch (ClassNotFoundException e) {
	System.out.println("드라이버 클래스를 찾을 수 없습니다.");
}

try {
	Connection conn = DriverManager.getConnection(jdbc_url,jdbc_id,jdbc_password);
	String sql = "select name from member where id=? and password=?";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, id);
	pstmt.setString(2, password);
	
	ResultSet rs = pstmt.executeQuery();
	
	if(rs.next()) {
		name = rs.getString("name");
	}
	rs.close();
	pstmt.close();
	conn.close();
} catch (SQLException e) {
	e.printStackTrace();
}


if( name == null ) {
	out.println("<h2>로그인 실패</h2>");
	out.println("<h4>입력한 정보를 확인하세요</h4>");
} else {
	out.println("<h2>로그인 성공</h2>");
	out.println("<h4>" + name + " 님 환영합니다.</h4>");
	out.println("<form action='./main.jsp'><input type='submit' value='메인으로~!'></form>");
	
	if(checked==null) {
		Cookie cookie = new Cookie("id", id);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	} else {
		Cookie cookie = new Cookie("id", id);
		response.addCookie(cookie);
		
	}

	synchronized (application) {
		Integer accessUser = (Integer)application.getAttribute("accessUser");
		if(accessUser == null){
			accessUser = 1;
			System.out.println("로그인체크에서 accessUser ==null");
		}
		accessUser++;
		application.setAttribute("accessUser", accessUser);	
	}
	

	session.setAttribute("login_id", id);
	session.setAttribute("login_name", name);
}

%>
</body>
</html>