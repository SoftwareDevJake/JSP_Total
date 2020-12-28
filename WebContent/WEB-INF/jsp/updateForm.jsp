<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<h1> 게시물 수정 </h1>
<body>

	번호 : ${myData3.id}

	<form action="/JSP/article">
		수정할 제목 : <input type="text" name="title" value="${myData3.title }"/>
		<hr>
		수정할 내용 : <input type="text" name="body" value="${myData3.body }"/> 
		<hr>
		<input type="hidden" name="action" value="update"/>
		<input type="hidden" name="aid" value="${myData3.id}"/>
		<br>
		<input type="submit"/>
	</form>
</body>
</html>