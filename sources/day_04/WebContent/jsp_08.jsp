<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>취미 입력</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/result/jsp_09.jsp" method="post">
	<table>
		<caption>취미 선택</caption>
		<tr>
			<th><label><input type="Checkbox" name="id" value="DB">DB</label></th>
			<th><label><input type="Checkbox" name="id" value="Java">Java</label></th>				
			<th><label><input type="Checkbox" name="id" value="Suvlet">Suvlet</label></th>
			<th><label><input type="Checkbox" name="id" value="JSP">JSP</label></th>
			<th><label><input type="Checkbox" name="id" value="JavaScript">JavaScript</label></th>
		</tr>
		
		<tr>
			<th colspan="5"><input type="submit" value="전송">
		</tr>
	</table>
</form>

</body>
</html>






