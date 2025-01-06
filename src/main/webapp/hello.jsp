<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello JSP World</title>
</head>
<body>
	<%
	String name = request.getParameter("name");	//	name 파라미터 받아옴
	if (name == null) {	//	파라미터가 전달되지 않았을 때
		name = "Anonymous";
	}
	%>
	<h1>Hello JSP World</h1>
	<h3>Welcome, <%= name %>님</h3>
	<p>이것은 JSP로 만들어진 동적 페이지입니다.</p>
</body>
</html>