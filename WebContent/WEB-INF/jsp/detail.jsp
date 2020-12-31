<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 게시물 상세보기 </h1>

	번호 : ${myData2.id}
	<hr>
	제목 : ${myData2.title}
	<hr>
	내용 : ${myData2.body}
	<hr>
	

	<c:if test="${loginedMember.id == myData2.mid }">
		<a href="/JSP_total/article?action=showUpdate&aid=${myData2.id }">수정</a>
		<a href="/JSP_total/article?action=delete&aid=${myData2.id }">삭제</a>
	</c:if>

	<a href="/JSP_total/article?action=list">홈으로</a>
</body>
</html>