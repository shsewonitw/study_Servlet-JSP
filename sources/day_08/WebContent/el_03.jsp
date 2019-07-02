<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "java.util.*" %>
<%@ page import = "tje.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL을 활용한 객체의 접근</title>
</head>
<body>

<%
	HashMap<String,Integer> map = new HashMap<>();
	map.put("One",1);
	map.put("Two",2);
	map.put("Three",3);
	
	// EL 언어를 사용하여 접근하는 모든 객체들은
	// 반드시 특정 영역 내부에 저장되어야만 합니다.
	request.setAttribute("map",map);
	request.setAttribute("test",23);
	request.setAttribute("test",24);
	request.setAttribute("test",99);
	
	ArrayList<String> list = new ArrayList<>();
	list.add("One");
	list.add("Two");
	list.add("Three");
	
	request.setAttribute("list",list);
	
	Member member = new Member();
	member.id = "abc";
	member.password = "def";
	member.name= "아무개";
	request.setAttribute("member",member);
%>

<h3>map1 객체의 One 키 값에 접근 : ${ map1.One }</h3>
<h3>map 객체의 One 키 값에 접근 : ${ map.One }</h3>
<h3>Test : ${ test }</h3>
<!-- 접근할 객체의 타입이 배열, 리스트 타입인 경우 반드시 정수만 사용 가능 

-->
<h3>list 객체의 0에 접근 : ${ list[0] }</h3>
<h3>list 객체의 1에 접근 : ${ list[1] }</h3>
<h3>list 객체의 2에 접근 : ${ list[2] }</h3>

<h3>member 객체의 id 에 접근 : ${ member.id }</h3>

</body>
</html>