<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
final String jdbc_url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
final String jdbc_id = "root";
final String jdbc_pw = "SystemManager304";

response.setContentType("text/html;charset=utf-8");

try {
	Connection conn = DriverManager.getConnection(jdbc_url,jdbc_id,jdbc_pw);
	Statement stmt = conn.createStatement();
	ResultSet rs = null;
	rs = stmt.executeQuery("select * from member");
	
	while(rs.next()) {
		String id = rs.getString(1);
		String password = rs.getString(2);
		String name = rs.getString(3);
		int age = rs.getInt(4);
		String tel = rs.getString(5);
		String address = rs.getString(6);
%>
		<h6>ID-> <%=id %>, PW-> <%=password %>, NAME-> <%=name %>, AGE-> <%=age %>, TEL-> <%=tel %>, ADDRESS-> <%=address %></h6>

<%	
	}

	rs.close();
	stmt.close();
	conn.close();
} catch (SQLException e) {
}
%>

</body>
</html>