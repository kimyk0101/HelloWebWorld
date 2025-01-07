<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERROR PAGE</title>
</head>
<body>
	<h1>뭔가 잘못되었습니다.</h1>
	<p>ERROR CODE: <%= response.getStatus() %></p>
	<p>Exception Type: <%= exception.getClass().getSimpleName() %></p>
	<p>에러 메시지: <%= exception.getMessage() %></p>
</body>
</html>