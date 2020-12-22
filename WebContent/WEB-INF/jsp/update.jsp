<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 수정 페이지 </h1>
번호 : ${myData2.id }
수정할 제목 : abc 
<hr>
수정할 내용 : def 
<hr>
<a href="/JSP_total/article?action=update&title=abc&body=def&aid=${myData2.id }">완료</a>
</body>
</html>